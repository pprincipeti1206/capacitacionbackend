package com.incloud.hcp._gproveedor.jco.proveedor.service.impl;


import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCParameterBuilder;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorResponseRFC;
import com.incloud.hcp._gproveedor.jco.proveedor.service.JCOProveedorService;
import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain.MtrProveedor;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.PreRegistroProveedorRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorCuentaBancoRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrProveedorDeltaRepository;
import com.incloud.hcp.service._gproveedor.notificacion.NotificacionFlujoAprobacion;
import com.incloud.hcp.service.notificacion.GestionProveedorNotificacion;
import com.incloud.hcp.utils.UtilString;
import com.incloud.hcp.utils._gproveedor.constant.WebServiceConstant;
import com.sap.conn.jco.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JCOProveedorServiceImpl implements JCOProveedorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int NRO_EJECUCIONES_RFC = 10;
    private final String FUNCION_RFC = "ZMM_CREA_PROVEEDOR";
    private final String NOMBRE_TABLA_RFC = "E_MENSAJES";

    @Value("${destination.rfc.profit}")
    private String destinationProfit;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorCuentaBancoRepository proveedorCuentaBancoRepository;

    @Autowired
    private MtrProveedorDeltaRepository mtrProveedorDeltaRepository;

    @Autowired
    AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    GestionProveedorNotificacion gestionProveedorNotificacion;

    @Autowired
    PreRegistroProveedorRepository preRegistroProveedorRepository;

    @Autowired NotificacionFlujoAprobacion notificacionFlujoAprobacion;


    @Override
    public ProveedorRFCResponseDto  grabarProveedor(Integer idProveedor,String usuarioSap) throws Exception {
        ProveedorRFCResponseDto proveedorRFCResponseDto = new ProveedorRFCResponseDto();


        /* Ejecucion invocacion a RFC */
        JCoDestination destination = JCoDestinationManager.getDestination(destinationProfit);
        JCoRepository repo = destination.getRepository();
        logger.error("01A - GRABAR PROVEEDOR");
        JCoFunction jCoFunction = repo.getFunction(FUNCION_RFC);
        logger.error("01B - GET PROVEEDOR");

        ///Obtener Bean Proveedor
        Proveedor proveedorParam = this.proveedorRepository.getProveedorByIdProveedor(idProveedor);
        List<ProveedorCuentaBancaria> listaCuentaBancaria= this.
                proveedorCuentaBancoRepository.getListCuentaBancariaByIdProveedor(idProveedor);

        logger.error("parametro ingresado Proveedor" + proveedorParam.toString());
        logger.error("parametro ingresado Cuenta Bancaria" + listaCuentaBancaria.toString());
        ProveedorRFCParameterBuilder.build(
                jCoFunction,
                proveedorParam,
                listaCuentaBancaria,
                usuarioSap
        );
        logger.error("01C - GET PROVEEDOR");
        for(int contador=0; contador < NRO_EJECUCIONES_RFC; contador++) {
            try {
                jCoFunction.execute(destination);
                break;
            } catch (Exception e) {
                if (contador == NRO_EJECUCIONES_RFC - 1 ) {
                    logger.error("01Ca - PROVEEDOR - INI RFC ERROR: "+ e.toString());
                    throw new Exception(e);
                }
            }
        }

        /* Obteniendo los valores obtenidos del RFC */
        logger.error("02 - GET PROVEEDOR - FIN RFC");
        JCoParameterList result = jCoFunction.getExportParameterList();


        List<SapLog> listSapLog = new ArrayList<>();
        String acreedorSap = result.getString("E_COD_PROVEEDOR");

        logger.error("02b - GET PROVEEDOR - ACREEDOR SAP: " + acreedorSap);
        /* Recorriendo valores obtenidos del RFC */
        JCoTable table = result.getTable(NOMBRE_TABLA_RFC);
        if (table != null && !table.isEmpty()) {

            do {

                SapLog sapLog = new SapLog();
                sapLog.setCode(table.getString("NUMERO"));
                sapLog.setMesaj(table.getString("TEXTO"));
                sapLog.setClase(table.getString("CLASE"));
                sapLog.setTipo(table.getString("TIPO"));
                listSapLog.add(sapLog);


            } while (table.nextRow());
        }
        proveedorRFCResponseDto.setNroAcreedor(acreedorSap);
        proveedorRFCResponseDto.setListasapLog(listSapLog);

        /**
         * Validamos de que no haya mensajes de error
         * */

        List<SapLog> erroresEnSap = listSapLog.stream()
                .filter( o -> UtilString.coalesceTrim( o.getTipo() ).equals( "E" ) )
                .collect (Collectors.toList() );

        if( !erroresEnSap.isEmpty() ){

            proveedorRFCResponseDto.setProveedorSap( proveedorParam );

            //--- Notificamos a los usuarios de soporte configurados
            notificacionFlujoAprobacion.sendNotifyTeamSuport( proveedorRFCResponseDto );

            String erroresSAP = erroresEnSap.stream().map( SapLog::getMesaj ).collect(Collectors.joining( "," ));

            //--- Accionamos una excepción para no continuar el procesamiento
            throw new PortalException( erroresSAP );

        }

        //Actuaizar numero de acreeddor
        MtrProveedor mtrProveedor = this.mtrProveedorDeltaRepository.findByRuc(proveedorParam.getRuc());
        if (Optional.ofNullable(mtrProveedor).isPresent()) {
            logger.error("proveedorParam___ existe_xz ::: ");
            MtrProveedor mtrProveedorBuscar = new MtrProveedor();
            mtrProveedorBuscar.setId(mtrProveedor.getId());
            proveedorParam.setMtrProveedor(mtrProveedorBuscar);
        }
        proveedorParam.setAcreedorCodigoSap(acreedorSap);
        logger.error("numero de acreeddor ::: " + acreedorSap);
        this.proveedorRepository.save(proveedorParam);

        if (!Optional.ofNullable(mtrProveedor).isPresent()) {
            logger.error("proveedorParam___no existe_xz ::: ");
            MtrProveedor mtrProveedorBuscar = new MtrProveedor();
            mtrProveedorBuscar.setCodigoIdp(proveedorParam.getIdHcp());
            mtrProveedorBuscar.setDireccion(proveedorParam.getDireccionFiscal());
            mtrProveedorBuscar.setEmailContacto(proveedorParam.getEmail());
            mtrProveedorBuscar.setRuc(proveedorParam.getRuc());
            mtrProveedorBuscar.setLifnr(proveedorParam.getAcreedorCodigoSap());
            mtrProveedorBuscar.setRazonSocial(proveedorParam.getRazonSocial());
            mtrProveedorBuscar = this.mtrProveedorDeltaRepository.save(mtrProveedorBuscar);
            proveedorParam.setMtrProveedor(mtrProveedorBuscar);
            this.proveedorRepository.save(proveedorParam);
        }

        logger.error("proveedorParam___ ::: " + proveedorParam);
        AppParametria paramDest =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_1", "1");
        AppParametria paramDest2 =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_2", "1");
        AppParametria paramDest3 =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_3", "1");
        String correoDestinatario = "";
        String nombreDestinatarios = "";
        if (paramDest3 != null){
            correoDestinatario = paramDest3.getValue1();
            nombreDestinatarios = paramDest3.getValue2();
        }

        if (paramDest2 != null) {
            correoDestinatario = correoDestinatario + "," + paramDest2.getValue1();
            nombreDestinatarios = nombreDestinatarios + "," + paramDest2.getValue2();
        }

        if (paramDest != null) {
            correoDestinatario = correoDestinatario + "," + paramDest.getValue1();
            nombreDestinatarios = nombreDestinatarios + "," + paramDest.getValue2();
        }

        if(StringUtils.isNotBlank(proveedorParam.getEmail())) {
            correoDestinatario = correoDestinatario + "," + proveedorParam.getEmail();
            nombreDestinatarios = nombreDestinatarios + "," + proveedorParam.getRazonSocial();
        }


        //PreRegistroProveedor pre = this.preRegistroProveedorRepository.getByRuc(proveedorParam.getRuc());


        /*if(paramDest != null || paramDest2 != null || paramDest3 != null) {
            String [] arrEmails = correoDestinatario.split(",");
            String [] nombres = nombreDestinatarios.split(",");
            for (int i = 0; i < arrEmails.length ; i++) {
                String correo = "";
                String nombre = "";
                if(StringUtils.isNotBlank(arrEmails[i]))
                    correo = arrEmails[i];
                if(StringUtils.isNotBlank(nombres[i]))
                    nombre = nombres[i];

                this.gestionProveedorNotificacion.enviar(correo, nombre , proveedorParam.getRazonSocial());
            }
        }*/
        return proveedorRFCResponseDto;
    }

    @Override
    public ProveedorResponseRFC grabarListaProveedorSAP(List<Proveedor> listaProveedorPotencial, String usuarioSap) throws Exception {

        ProveedorResponseRFC beanRetorno = new ProveedorResponseRFC();
        beanRetorno.setTieneError(false);
        SapLog sapLogRetorno = new SapLog();
        List<ProveedorRFCResponseDto> listaProveedorSAPResult = new ArrayList<ProveedorRFCResponseDto>();

        /////GRABAR
        sapLogRetorno.setCode(WebServiceConstant.RESPUESTA_OK);
        sapLogRetorno.setMesaj(WebServiceConstant.MENSAJE_VACIO);
        for (Proveedor beanProveedor : listaProveedorPotencial) {

            Optional<String> oCodigoSap = Optional.ofNullable(beanProveedor.getAcreedorCodigoSap())
                    .filter(codigo -> !codigo.isEmpty());
            if (!oCodigoSap.isPresent()) {
                logger.error("INGRESANDO A CREAR");
                ProveedorRFCResponseDto proveedorRFCResponseDtoResult = null;
                try {
                    proveedorRFCResponseDtoResult = this.grabarProveedor(beanProveedor.getIdProveedor(),usuarioSap);
                    logger.error("PROVEEDOR ACREEDOR"+proveedorRFCResponseDtoResult.getNroAcreedor());

                    /* Respuesta SAP */
                    if (StringUtils.isNotBlank(proveedorRFCResponseDtoResult.getNroAcreedor())) {
                        logger.error("CREANDO");
                        beanProveedor.setAcreedorCodigoSap(proveedorRFCResponseDtoResult.getNroAcreedor());
                          this.proveedorRepository.updateAcreedorCodigoSAP(
                                    proveedorRFCResponseDtoResult.getNroAcreedor(),
                                   beanProveedor.getIdProveedor());
                        logger.error("GrabarSAP PROVEEDOR EXITO CODIGO SAP: " + proveedorRFCResponseDtoResult.getNroAcreedor());

                    } else {

                        beanRetorno.setTieneError(true);
                        logger.error("GrabarSAP PROVEEDOR ERROR: "  );
                    }
                } catch (Exception e) {
                    logger.error("Ingresando grabarProveedorSAP  error: " );
                    logger.error("GrabarSAP PROVEEDOR Exception: " + e.getMessage());
                    beanRetorno.setTieneError(true);
                }
                logger.error("BEAN PROVEEDOR SAP" + beanProveedor.getAcreedorCodigoSap());
                proveedorRFCResponseDtoResult.setProveedorSap(beanProveedor);
                listaProveedorSAPResult.add(proveedorRFCResponseDtoResult);

            }

        }
        beanRetorno.setSapLog(sapLogRetorno);
        beanRetorno.setListaProveedorSAPResult(listaProveedorSAPResult);
        logger.error("Ingresando grabarProveedorSAP DEV FIN: ");
        return beanRetorno;



    }


}
