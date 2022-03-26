package com.incloud.hcp.service._gproveedor.impl;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.incloud.hcp._gproveedor.jco.actualizarProveedor.service.JCOActualizarProveedorService;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.proveedor.service.JCOProveedorService;
import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp._gproveedor.wsdl.inside.InSiteResponse;
import com.incloud.hcp._gproveedor.wsdl.inside.InSiteService;
import com.incloud.hcp._security.SystemLoggedUser;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.bean.custom.SaveApprovalInput;
import com.incloud.hcp.bean.custom.SendNotifySupplier;
import com.incloud.hcp.bean.custom.SendNotifyUserDto;
import com.incloud.hcp.cmis.CmisFile;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorCustom;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorFiltro;
import com.incloud.hcp.domain._gproveedor.domain.*;
import com.incloud.hcp.domain.response.SaveInformationResponse;
import com.incloud.hcp.enums.MessageTypeEnum;
import com.incloud.hcp.enums._gproveedor.EstadoAprobacionEnum;
import com.incloud.hcp.enums._gproveedor.EstadoProveedorEnum;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.exception.ServiceException;
import com.incloud.hcp.mapper._gproveedor.*;
import com.incloud.hcp.repository._gproveedor.*;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.repository.delta.MtrProveedorDeltaRepository;
import com.incloud.hcp.service._framework.cmis.CmisService;
import com.incloud.hcp.service._gproveedor.ProveedorService;
import com.incloud.hcp.service._gproveedor.dto.*;
import com.incloud.hcp.service._gproveedor.dto.mapper.*;
import com.incloud.hcp.service._gproveedor.notificacion.NotificacionFlujoAprobacion;
import com.incloud.hcp.service._gproveedor.notificacion.ProveedorDataMaestraNotificacion;
import com.incloud.hcp.service._gproveedor.notificacion.ProveedorRevisionDataMaestraNotificacion;
import com.incloud.hcp.service.notificacion.GestionProveedorMisDatosNotificacion;
import com.incloud.hcp.service.notificacion.GestionProveedorNotificacion;
import com.incloud.hcp.service.notificacion.GestionProveedorRechazoNotificacion;
import com.incloud.hcp.service.notificacion.RegistroSolicitudProveedorRechazoNotificacion;
import com.incloud.hcp.util.Utils;
import com.incloud.hcp.utils.DateUtils;
import com.incloud.hcp.utils.MessageError;
import com.incloud.hcp.utils.UtilInteger;
import com.incloud.hcp.utils.UtilString;
import com.incloud.hcp.utils._gproveedor.Constant;
import com.incloud.hcp.utils._gproveedor.constant.ParametroConstant;
import com.incloud.hcp.utils._gproveedor.constant.ParametroTipoConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrador on 29/08/2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProveedorServiceImpl implements ProveedorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int NRO_EJECUCIONES = 10;

    @Value("${sm.portal.dev}")
    private Boolean isDev;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorCanalContactoRepository proveedorCanalContactoRepository;

    @Autowired
    private ProveedorAdjuntoSunatRepository proveedorAdjuntoSunatRepository;

    @Autowired
    private ProveedorCuentaBancoRepository proveedorCuentaBancoRepository;
    @Autowired
    private ProveedorLineaComercialRepository proveedorLineaComercialRepository;
    @Autowired
    private ProveedorProductoRepository proveedorProductoRepository;

    @Autowired
    private ProveedorEvaluacionRepository proveedorEvaluacionRepository;

    @Autowired
    AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    GestionProveedorNotificacion gestionProveedorNotificacion;

    @Autowired
    GestionProveedorRechazoNotificacion gestionProveedorRechazoNotificacion;

    @Autowired
    RegistroSolicitudProveedorRechazoNotificacion registroSolicitudProveedorRechazoNotificacion;

    @Autowired
    private JCOActualizarProveedorService jcoActualizarProveedorService;
    @Autowired
    PreRegistroProveedorRepository preRegistroProveedorRepository;

//    @Autowired
//    private JCOConsultaProveedorService jcoConsultaProveedorService;

    @Autowired
    private ContactoMapper contactoMapper;

    @Autowired
    private CuentaBancariaMapper cuentaBancariaMapper;

    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private MonedaRepository monedaRepository;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    private UbigeoMapper ubigeoMapper;

    @Autowired
    private ProveedorMapper proveedorMapperMybatis;
    @Autowired
    private CondicionPagoReposity condicionPagoReposity;
    @Autowired
    private SectorTrabajoRepository sectorTrabajoRepository;
    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;
    @Autowired
    private TipoProveedorRepository tipoProveedorRepository;
    @Autowired
    private LineaComercialRepository lineaComercialRepository;
    @Autowired
    private ProveedorCatalogoRepository proveedorCatalogoRepository;
    @Autowired
    private UbigeoRepository ubigeoRepository;



    @Autowired
    private HomologacionMapper homologacionMapper;


    @Autowired
    private ParametroMapper parametroMapper;

    @Autowired
    private ProveedorSectorTrabajoRepository proveedorSectorTrabajoRepository;

    @Autowired
    private EstadoProveedorRepository estadoProveedorRepository;

    @Autowired
    private ProveedorInstalacionRepository proveedorInstalacionRepository;

    @Autowired
    private ProveedorFuncionarioRepository proveedorFuncionarioRepository;

    @Autowired
    private ProveedorPreguntaInformacionRepository proveedorPreguntaInformacionRepository;

    @Autowired
    private ProveedorClienteRepository proveedorClienteRepository;

    @Autowired
    private ProveedorPermisoRepository proveedorPermisoRepository;

    @Autowired
    private PreguntaInformacionRepository preguntaInformacionRepository;

    @Autowired
    private ProveedorHomologacionRepository proveedorHomologacionRepository;

    @Autowired
    private ProveedorInstalacionMapper proveedorInstalacionMapper;

    @Autowired
    private ProveedorPermisoMapper proveedorPermisoMapper;

    @Autowired
    private ProveedorPreguntaInformacionMapper proveedorPreguntaInformacionMapper;

    @Autowired
    private ProveedorClienteMapper proveedorClienteMapper;

    @Autowired
    private ProveedorFuncionarioMapper proveedorFuncionarioMapper;

    @Autowired
    private ProveedorDataMaestraNotificacion proveedorDataMaestraNotificacion;

    @Autowired
    private NotificacionFlujoAprobacion notificacionFlujoAprobacion;

    @Autowired
    private CmisService cmisService;

    @Autowired
    private MtrProveedorDeltaRepository mtrProveedorDeltaRepository;

    @Autowired
    private InSiteService inSiteService;

    @Autowired
    private SystemLoggedUser systemLoggedUser;

    @Autowired
    private ProveedorRevisionDataMaestraNotificacion proveedorRevisionDataMaestraNotificacion;

    @Autowired
    private ProveedorCuentaBancariaExtranjRepository proveedorCuentaBancariaExtranjRepository;

    @Autowired
    private ProveedorCuentaBancariaExtranjMapper proveedorCuentaBancariaExtranjMapper;

    @Autowired
    private ProveedorPorValidarInfoRepository proveedorPorValidarInfoRepository;

    @Autowired
    private ProveedorValidacionInfoRepository proveedorValidacionInfoRepository;

    @Autowired
    private ProveedorValidacionInfoDetalleRepository proveedorValidacionInfoDetalleRepository;

    @Autowired
    private GestionProveedorMisDatosNotificacion gestionProveedorMisDatosNotificacion;

    private Populater<SectorTrabajo, SectorTrabajoDto> sectorTrabajoPopulater;

    public ProveedorServiceImpl() {
    }

    @Autowired
    @Qualifier(value = "sectorTrabajoPopulate")
    public void setSectorTrabajoPopulater(Populater<SectorTrabajo, SectorTrabajoDto> sectorTrabajoPopulater) {
        this.sectorTrabajoPopulater = sectorTrabajoPopulater;
    }

    @Autowired
    public void setProveedorSectorTrabajoRepository(ProveedorSectorTrabajoRepository proveedorSectorTrabajoRepository) {
        this.proveedorSectorTrabajoRepository = proveedorSectorTrabajoRepository;
    }

    @Autowired private JCOProveedorService jcoProveedorService;

    private Proveedor getOne(Integer idProveedor) {
        Proveedor proveedor = this.proveedorRepository.getOne(idProveedor);
        if (Optional.ofNullable(proveedor).isPresent()) {
            if (proveedor.getTipoComprobante().getCodigoTipoComprobante().equalsIgnoreCase(Constant.SAP_TIPO_PROVEEDOR_NACIONAL)) {
                if (Optional.ofNullable(proveedor.getRuc()).isPresent()) {

                    InSiteResponse response = null;
                    for (int contador = 0; contador < NRO_EJECUCIONES; contador++) {
                        try {
                            response = inSiteService.getConsultaRuc(proveedor.getRuc());
                            break;
                        } catch (Exception e) {
                            if (contador == NRO_EJECUCIONES - 1) {
                                throw new PortalException(e.getMessage());
                            }
                        }
                    }
                    if (Optional.ofNullable(response).isPresent()) {
                        BeanUtils.copyProperties(response, proveedor);
                        Optional.ofNullable(response.isEstado()).map(v -> v ? "1" : "0").ifPresent(proveedor::setIndActivoSunat);
                        Optional.ofNullable(response.isCondicion()).map(v -> v ? "1" : "0").ifPresent(proveedor::setIndHabidoSunat);
                    }

//                try {
//                    InSiteResponse response = inSiteService.getConsultaRuc(proveedor.getRuc());
//                    if (response != null) {
//                        BeanUtils.copyProperties(response, proveedor);
////                        proveedor.setFechaInicioActiSunat(response.getFechaInicioActiSunat());
////                        proveedor.setCodigoComprobantePago(response.getCodigoComprobantePago());
////                        proveedor.setCodigoSistemaEmisionElect(response.getCodigoSistemaEmisionElect());
////                        proveedor.setCodigoPadron(response.getCodigoPadron());
//                        Optional.ofNullable(response.isEstado()).map(v -> v ? "1" : "0").ifPresent(proveedor::setIndActivoSunat);
//                        Optional.ofNullable(response.isCondicion()).map(v -> v ? "1" : "0").ifPresent(proveedor::setIndHabidoSunat);
//                    }
//                } catch (InSiteException ex) {
//
//                }
                }
            }
        }
        return proveedor;
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDto getProveedorDtoById(Integer idProveedor) throws PortalException {
        logger.error("Ingresando getProveedorDtoById 00");
        return Optional.ofNullable(this)
                .map(r -> r.getOne(idProveedor))
                .map(this::toDto)
                .orElse(new ProveedorDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor getProveedorById(Integer idProveedor) throws PortalException {
        return Optional.ofNullable(proveedorRepository)
                .map(r -> r.getOne(idProveedor))
                .orElse(null);
    }

    public void eliminarDatosProveedor(Integer idProveedor) {
        Proveedor p = this.getProveedorById(idProveedor);
        if (!Optional.ofNullable(p).isPresent()) {
            return ;
        }
        this.proveedorHomologacionRepository.deleteRespuestaByIdProveedor(p.getIdProveedor());
        this.proveedorCanalContactoRepository.deleteCanalContactoByIdProveedor(p.getIdProveedor());
        this.proveedorCuentaBancoRepository.deleteContactoByIdProveedor(p.getIdProveedor());
        this.proveedorLineaComercialRepository.deleteLineaComercialByIdProveedor(p.getIdProveedor());
        this.proveedorProductoRepository.deleteProductoByIdProveedor(p.getIdProveedor());
        this.proveedorSectorTrabajoRepository.deleteSectorTrabajoByIdProveedor(p.getIdProveedor());

        this.proveedorInstalacionRepository.deleteInstalacionByIdProveedor(p.getIdProveedor());
        this.proveedorFuncionarioRepository.deleteFuncionarioByIdProveedor(p.getIdProveedor());
        this.proveedorPreguntaInformacionRepository.deletePreguntaInformacionByIdProveedor(p.getIdProveedor());
        this.proveedorClienteRepository.deleteClienteByIdProveedor(p.getIdProveedor());
        this.proveedorPermisoRepository.deletePermisoByIdProveedor(p.getIdProveedor());
        this.proveedorRepository.deleteById(p.getIdProveedor());
        return ;

    }


    @Override
    public Proveedor save(Proveedor documento) {
        return proveedorRepository.save(documento);
    }

    private List<ProveedorPorValidarInfo> obtenerListaPorValidarInfo(ProveedorDto dto, Proveedor proveedorActual) throws Exception {
        List<ProveedorPorValidarInfo> proveedorPorValidarInfoList = new ArrayList<>();
        if (!Optional.ofNullable(proveedorActual.getAcreedorCodigoSap()).isPresent()) {
            return proveedorPorValidarInfoList;
        }
        Proveedor proveedorBuscar = new Proveedor();
        proveedorBuscar.setIdProveedor(proveedorActual.getIdProveedor());

        if (!proveedorActual.getRazonSocial().equals(dto.getRazonSocial())) {
            ProveedorPorValidarInfo proveedorPorValidarInfo =
                    this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "RAZON_SOCIAL");
            if (!Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                proveedorPorValidarInfo = new ProveedorPorValidarInfo();
                proveedorPorValidarInfo.setIdProveedor(proveedorBuscar);
                proveedorPorValidarInfo.setIndEsLista(Constant.N);
                proveedorPorValidarInfo.setNombreCampoBd("RAZON_SOCIAL");
            }
            proveedorPorValidarInfo.setValorActual(proveedorActual.getRazonSocial());
            proveedorPorValidarInfo.setValorNuevo(dto.getRazonSocial());
            proveedorPorValidarInfoList.add(proveedorPorValidarInfo);
        }

        if (!proveedorActual.getDireccionFiscal().equals(dto.getDireccionFiscal())) {
            ProveedorPorValidarInfo proveedorPorValidarInfo =
                    this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "DIRECCION_FISCAL");
            if (!Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                proveedorPorValidarInfo = new ProveedorPorValidarInfo();
                proveedorPorValidarInfo.setIdProveedor(proveedorBuscar);
                proveedorPorValidarInfo.setIndEsLista(Constant.N);
                proveedorPorValidarInfo.setNombreCampoBd("DIRECCION_FISCAL");
            }
            proveedorPorValidarInfo.setValorActual(proveedorActual.getDireccionFiscal());
            proveedorPorValidarInfo.setValorNuevo(dto.getDireccionFiscal());
            proveedorPorValidarInfoList.add(proveedorPorValidarInfo);
        }

        if (!proveedorActual.getEmail().equals(dto.getEmail())) {
            ProveedorPorValidarInfo proveedorPorValidarInfo =
                    this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "EMAIL");
            if (!Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                proveedorPorValidarInfo = new ProveedorPorValidarInfo();
                proveedorPorValidarInfo.setIdProveedor(proveedorBuscar);
                proveedorPorValidarInfo.setIndEsLista(Constant.N);
                proveedorPorValidarInfo.setNombreCampoBd("EMAIL");
            }
            proveedorPorValidarInfo.setValorActual(proveedorActual.getEmail());
            proveedorPorValidarInfo.setValorNuevo(dto.getEmail());
            proveedorPorValidarInfoList.add(proveedorPorValidarInfo);
        }

        /* Lista Cuentas Bancarias */
        boolean cambioCuentaBancaria = false;
        List<ProveedorCuentaBancaria> proveedorCuentaBancariaListActual = this.proveedorCuentaBancoRepository
                .getListCuentaBancariaByIdProveedor(proveedorActual.getIdProveedor());
        Integer tamActual = 0;
        if (proveedorCuentaBancariaListActual != null && proveedorCuentaBancariaListActual.size() > 0) {
            tamActual = proveedorCuentaBancariaListActual.size();
        }
        List<CuentaBancariaDto> proveedorCuentaBancariaListNuevo = dto.getCuentasBanco();
        Integer tamNuevo = 0;
        if (proveedorCuentaBancariaListNuevo != null && proveedorCuentaBancariaListNuevo.size() > 0) {
            tamNuevo = proveedorCuentaBancariaListNuevo.size();
        }
        if (tamActual.intValue() != tamNuevo.intValue()) {
            cambioCuentaBancaria = true;
        }
        else {
            for (CuentaBancariaDto beanCuenta : proveedorCuentaBancariaListNuevo) {
                List<ProveedorCuentaBancaria> proveedorCuentaBancariaList = this.proveedorCuentaBancoRepository
                        .getListCuentaBancariaByIdProveedorCuentaBancaria(
                                dto.getIdProveedor(),
                                beanCuenta.getIdBanco(),
                                beanCuenta.getIdMoneda(),
                                beanCuenta.getNumeroCuenta()
                        );
                if (proveedorCuentaBancariaList != null && proveedorCuentaBancariaList.size() > 0 ) {
                    ProveedorCuentaBancaria proveedorCuentaBancaria = proveedorCuentaBancariaList.get(0);
                    String numeroCCI = proveedorCuentaBancaria.getNumeroCuentaCci();
                    String contacto = proveedorCuentaBancaria.getContacto();
                    if (!numeroCCI.equals(beanCuenta.getNumeroCuentaCci())) {
                        cambioCuentaBancaria = true;
                        break;
                    }
                    if (Optional.ofNullable(contacto).isPresent()) {
                        if (!contacto.equals(beanCuenta.getContacto())) {
                            cambioCuentaBancaria = true;
                            break;
                        }
                    }
                }
                else {
                    cambioCuentaBancaria = true;
                    break;
                }
            }
        }
        if (cambioCuentaBancaria) {
            ProveedorPorValidarInfo proveedorPorValidarInfo =
                    this.proveedorPorValidarInfoRepository
                            .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA");
            if (!Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                proveedorPorValidarInfo = new ProveedorPorValidarInfo();
                proveedorPorValidarInfo.setIdProveedor(proveedorBuscar);
                proveedorPorValidarInfo.setIndEsLista(Constant.S);
                proveedorPorValidarInfo.setNombreCampoBd("LISTA_CUENTA_BANCARIA");
            }

            ObjectMapper mapperSalidaActual = new ObjectMapper();
            mapperSalidaActual.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperSalidaActual.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String resultadoSalidaActual = null;
            if (proveedorCuentaBancariaListActual != null && proveedorCuentaBancariaListActual.size() > 0 ) {
                List<ProveedorCuentaBancaria> listaActual = this.cuentaBancariaMapper.getListProveedorCuentaBancariaByIdProveedor(
                                ParametroConstant.CUENTA_BANCO,
                                ParametroTipoConstant.TIPO,
                        proveedorBuscar.getIdProveedor());


                resultadoSalidaActual = mapperSalidaActual.writerWithDefaultPrettyPrinter().writeValueAsString(listaActual);
                proveedorPorValidarInfo.setValorListaActual(resultadoSalidaActual);
            }
            logger.error("Ingresando obtenerListaPorValidarInfo CUENTA BANCARIA actual: " + resultadoSalidaActual);

            ObjectMapper mapperSalidaNuevo = new ObjectMapper();
            mapperSalidaNuevo.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperSalidaActual.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String resultadoSalidaNuevo = null;
            if (proveedorCuentaBancariaListNuevo != null && proveedorCuentaBancariaListNuevo.size() > 0 ) {
                resultadoSalidaNuevo = mapperSalidaNuevo.writerWithDefaultPrettyPrinter().writeValueAsString(proveedorCuentaBancariaListNuevo);
                proveedorPorValidarInfo.setValorListaNuevo(resultadoSalidaNuevo);
            }
            logger.error("Ingresando obtenerListaPorValidarInfo CUENTA BANCARIA nuevo: " + resultadoSalidaNuevo);
            proveedorPorValidarInfoList.add(proveedorPorValidarInfo);
        }


        /* Lista Cuentas Bancarias Extranjeras */
        boolean cambioCuentaBancariaExtran = false;
        List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjListActual = this.proveedorCuentaBancariaExtranjRepository
                .getListaByIdProveedor(proveedorActual.getIdProveedor());
        Integer tamActualExtran = 0;
        if (proveedorCuentaBancariaExtranjListActual != null && proveedorCuentaBancariaExtranjListActual.size() > 0) {
            tamActualExtran = proveedorCuentaBancariaListActual.size();
        }
        List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjListNuevo = dto.getProveedorCuentaBancariaExtranjList();
        Integer tamNuevoExtranj = 0;
        if (proveedorCuentaBancariaExtranjListNuevo != null && proveedorCuentaBancariaExtranjListNuevo.size() > 0) {
            tamNuevoExtranj = proveedorCuentaBancariaExtranjListNuevo.size();
        }
        if (tamActualExtran.intValue() != tamNuevoExtranj.intValue()) {
            cambioCuentaBancariaExtran = true;
        }
        else {
            for (ProveedorCuentaBancariaExtranj beanCuenta : proveedorCuentaBancariaExtranjListNuevo) {
                List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjList = this.proveedorCuentaBancariaExtranjMapper
                        .getProveedorCuentaBancariaExtranj(beanCuenta);
                if (proveedorCuentaBancariaExtranjList != null && proveedorCuentaBancariaExtranjList.size() > 0 ) {
                }
                else {
                    cambioCuentaBancariaExtran = true;
                    break;
                }
            }
        }
        if (cambioCuentaBancariaExtran) {
            ProveedorPorValidarInfo proveedorPorValidarInfo =
                    this.proveedorPorValidarInfoRepository
                            .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA_EXTRANJERA");
            if (!Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                proveedorPorValidarInfo = new ProveedorPorValidarInfo();
                proveedorPorValidarInfo.setIdProveedor(proveedorBuscar);
                proveedorPorValidarInfo.setIndEsLista(Constant.S);
                proveedorPorValidarInfo.setNombreCampoBd("LISTA_CUENTA_BANCARIA_EXTRANJERA");
            }

            ObjectMapper mapperSalidaActual = new ObjectMapper();
            mapperSalidaActual.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperSalidaActual.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String resultadoSalidaActual = null;
            if (proveedorCuentaBancariaExtranjListActual != null && proveedorCuentaBancariaExtranjListActual.size() > 0 ) {

                List<ProveedorCuentaBancariaExtranj> listaActual =
                        this.proveedorCuentaBancariaExtranjMapper.getProveedorCuentaBancariaExtranjPorProveedor(proveedorBuscar);

                resultadoSalidaActual = mapperSalidaActual.writerWithDefaultPrettyPrinter().writeValueAsString(listaActual);
                proveedorPorValidarInfo.setValorListaActual(resultadoSalidaActual);
            }
            logger.error("Ingresando obtenerListaPorValidarInfo CUENTA BANCARIA extranjera actual: " + resultadoSalidaActual);

            ObjectMapper mapperSalidaNuevo = new ObjectMapper();
            mapperSalidaNuevo.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperSalidaActual.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String resultadoSalidaNuevo = null;
            if (proveedorCuentaBancariaExtranjListNuevo != null && proveedorCuentaBancariaExtranjListNuevo.size() > 0 ) {
                resultadoSalidaNuevo = mapperSalidaNuevo.writerWithDefaultPrettyPrinter().writeValueAsString(proveedorCuentaBancariaExtranjListNuevo);
                proveedorPorValidarInfo.setValorListaNuevo(resultadoSalidaNuevo);
            }
            logger.error("Ingresando obtenerListaPorValidarInfo CUENTA BANCARIA nuevo: " + resultadoSalidaNuevo);
            proveedorPorValidarInfoList.add(proveedorPorValidarInfo);
        }

        return proveedorPorValidarInfoList;
    }

    @Override
    public ProveedorDto saveDto(ProveedorDto dto) throws Exception {
        if (dto == null) {
            throw new Exception("ProveedorDto ingresado es NULL");
        }
        if (Optional.ofNullable(dto.getRuc()).isPresent()) {
            String ruc = dto.getRuc().trim();
            dto.setRuc(ruc);
        }
        if (!Optional.ofNullable(dto.getFechaCreacion()).isPresent()) {
            dto.setFechaCreacion(new Date());
        }
        if (dto.getIdProveedor() == null || dto.getIdProveedor() == 0) {
            Proveedor proveedorRuc = this.proveedorRepository.getProveedorByRuc(dto.getRuc());
            if (proveedorRuc != null && proveedorRuc.getIdProveedor() != null) {
                dto.setIdProveedor(proveedorRuc.getIdProveedor());
            }
        }

        logger.error("Ingresando GRABAR PROVEEDOR Service 1");
        ProveedorDTOMapper proveedorDTOMapper = new ProveedorDTOMapper(
                this.condicionPagoReposity,
                this.ubigeoMapper,
                this.monedaRepository,
                this.tipoComprobanteRepository,
                this.tipoProveedorRepository);
        Optional<Proveedor> encontrado = Optional.ofNullable(dto.getIdProveedor())
                .map(this.proveedorRepository::getOne);
        Proveedor p;
        Proveedor data = proveedorDTOMapper.toEntity(dto);
        logger.error("Ingresando GRABAR PROVEEDOR Service 4 data: " + data.toString());
        EstadoProveedor estadoProveedor = this.estadoProveedorRepository.
                getByCodigoEstadoProveedor(EstadoProveedorEnum.REGISTRADO.getCodigo());

        boolean esNuevo = true;
        List<ProveedorPorValidarInfo> proveedorPorValidarInfoList = new ArrayList<>();
        Integer contadorPorValidar = 0;
        if (encontrado.isPresent()) {
            Proveedor proveedorBuscar = new Proveedor();
            proveedorBuscar.setIdProveedor(dto.getIdProveedor() );
            contadorPorValidar =
                    this.proveedorPorValidarInfoRepository.countByIdProveedor(proveedorBuscar);

            proveedorPorValidarInfoList = this.obtenerListaPorValidarInfo(dto, encontrado.get());

            esNuevo = false;
            logger.error("Ingresando GRABAR PROVEEDOR Service 5");
            p = encontrado.get();
            BeanUtils.copyProperties(data, p);
            p.setUsuarioCreacion(0);
            p.setFechaModificacion(new Date());
            //p.setIndHomologado("0");
            //p.setEvaluacionHomologacion(new BigDecimal(0));

            this.proveedorCanalContactoRepository.deleteCanalContactoByIdProveedor(p.getIdProveedor());
            this.proveedorCuentaBancoRepository.deleteContactoByIdProveedor(p.getIdProveedor());
            this.proveedorLineaComercialRepository.deleteLineaComercialByIdProveedor(p.getIdProveedor());
            this.proveedorProductoRepository.deleteProductoByIdProveedor(p.getIdProveedor());
            this.proveedorSectorTrabajoRepository.deleteSectorTrabajoByIdProveedor(p.getIdProveedor());

            this.proveedorInstalacionRepository.deleteInstalacionByIdProveedor(p.getIdProveedor());
            this.proveedorFuncionarioRepository.deleteFuncionarioByIdProveedor(p.getIdProveedor());
            this.proveedorPreguntaInformacionRepository.deletePreguntaInformacionByIdProveedor(p.getIdProveedor());
            this.proveedorClienteRepository.deleteClienteByIdProveedor(p.getIdProveedor());
            this.proveedorPermisoRepository.deletePermisoByIdProveedor(p.getIdProveedor());
            this.proveedorCuentaBancariaExtranjRepository.deleteByIdProveedor(p.getIdProveedor());

            logger.error("Ingresando GRABAR PROVEEDOR Service 6");

            String codigoMaximoNivelAprobado = UtilString.coalesceTrim( p.getCodigoMaximoEstadoAprobado() );
            boolean algunNivelAprobado = !UtilString.coalesceTrim( codigoMaximoNivelAprobado ).isEmpty();

            /**
             * Para el caso de proveedores que pasan por una actualización de información validamos que tenga el codigo sap y el maximo estado aprobado en aprobado por impuestos
             * */
            if ( !UtilString.coalesceTrim( p.getAcreedorCodigoSap() ).isEmpty() && codigoMaximoNivelAprobado.equals( EstadoProveedorEnum.APROBADO_POR_TESORERIA.getCodigo() ) ){

                //--- Obtenemos el estado de aprobado por compras
                EstadoProveedor aprobadoPorCompras = estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() );

                p.setIdEstadoProveedor( aprobadoPorCompras );
                p.setCodigoMaximoEstadoAprobado( aprobadoPorCompras.getCodigoEstadoProveedor() );

            }else if( algunNivelAprobado ){

                /**
                 * Si el proveedor tiene un nivel aprobado le asignamos el mencionado dato de lo contrario el maximo aprobado
                 * */

                //--- Obtenemos el maximo estado aprobado
                EstadoProveedor maximoEstadoAprobado = estadoProveedorRepository.getByCodigoEstadoProveedor( codigoMaximoNivelAprobado );
                p.setIdEstadoProveedor( maximoEstadoAprobado );

            }else{

                p.setIdEstadoProveedor( estadoProveedor );

            }

        } else {
            logger.error("Ingresando GRABAR PROVEEDOR Service 7");

            p = new Proveedor();
            BeanUtils.copyProperties(data, p);
            p.setIdEstadoProveedor(estadoProveedor);
            p.setFechaCreacion(new Date());
            p.setIndProveedorComunidad("0");
            p.setEvaluacionDesempeno(new BigDecimal(0));
            p.setEvaluacionHomologacion(new BigDecimal(0));
            p.setIndBlackList("0");
            p.setIndBloqueadoSap("0");
            p.setIndHomologado("0");
            p.setIndSujetoRetencion("0");
            p.setUsuarioCreacion(0);
            p.setFechaCreacion(new Date());
            p.setCantidadFlujosCompletados( 0 );

            //--- Si el proveedor migró de sap se le setea el estado de aprobado por compras
            if( !UtilString.coalesceTrim( dto.getAcreedorCodigoSap() ).isEmpty() ){

                //--- Obtenemos el estado de Aprobado por compras
                EstadoProveedor aprobadoPorCompras = estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() );

                p.setIdEstadoProveedor( aprobadoPorCompras );
                p.setCodigoMaximoEstadoAprobado( aprobadoPorCompras.getCodigoEstadoProveedor() );

            }

            logger.error("Ingresando GRABAR PROVEEDOR Service 8");
        }

        logger.error("Ingresando GRABAR PROVEEDOR Service 9 p: " + p.toString());
        if (contadorPorValidar > 0) {
            p.setIndPorValidarInfoAcreedor(Constant.S);
        }
        if (proveedorPorValidarInfoList != null && proveedorPorValidarInfoList.size() > 0) {
            p.setIndPorValidarInfoAcreedor(Constant.S);
        }

        /**
         * CC. Nuevo Flujo Aprobación. En cualquier caso, cuando el proveedor guarde su información se le bloqueará la opción de guardar su información en el app mis Datos
         * */
        p.setEnFlujoAprobacion( "X" );

        final Proveedor proveedor = this.proveedorRepository.save(p);
        logger.error("Ingresando GRABAR PROVEEDOR Service 10");

        /**
         * Contactos por canales de distribución
         */
        CanalContactoDTOMapper canalContactoDTOMapper = new CanalContactoDTOMapper(this.ubigeoMapper);
        Optional.ofNullable(dto.getCanales())
                .ifPresent(l -> l.stream()
                        .map(canalContactoDTOMapper::toEntity)
                        .forEach(c -> {
                            c.setProveedor(proveedor);
                            this.proveedorCanalContactoRepository.save(c);
                        }));

        /**
         * Cuentas de Banco
         */
        CuentaBancoDTOMapper bancoDTOMapper = new CuentaBancoDTOMapper(this.bancoRepository, this.monedaRepository);
        Optional.ofNullable(dto.getCuentasBanco())
                .ifPresent(l -> l.stream()
                        .map(bancoDTOMapper::toEntity)
                        .forEach(b -> {
                            ProveedorCuentaBancaria pcb = new ProveedorCuentaBancaria();
                            pcb.setIdCuenta(null);
                            Optional.ofNullable(b.getTipoCuenta())
                                    .map(t -> t.getCodigo()).ifPresent(pcb::setClaveControlBanco);
                            pcb.setContacto(b.getContacto());
                            pcb.setBanco(b.getBanco());
                            pcb.setMoneda(b.getMoneda());
                            pcb.setNumeroCuenta(b.getNumeroCuenta());
                            pcb.setNumeroCuentaCci(b.getNumeroCuentaCci());
                            pcb.setProveedor(proveedor);
                            this.proveedorCuentaBancoRepository.save(pcb);
                        }));
        /**
         * Lineas Comerciales
         */
        ProveedorLineaComercialDTOMapper lineaDtoMapper = new ProveedorLineaComercialDTOMapper(this.lineaComercialRepository);
        Optional.ofNullable(dto.getLineasComercial())
                .ifPresent(l -> l.stream()
                        .map(lineaDtoMapper::toEntity)
                        .forEach(n -> {
                            n.setProveedor(proveedor);
                            this.proveedorLineaComercialRepository.save(n);
                        }));
        /**
         * Productos
         */
        ProveedorProductoDTOMapper productoDTOMapper = new ProveedorProductoDTOMapper();
        Optional.ofNullable(dto.getProductos())
                .ifPresent(l -> l.stream()
                        .map(productoDTOMapper::toEntity)
                        .forEach(pr -> {
                            pr.setProveedor(proveedor);
                            this.proveedorProductoRepository.save(pr);
                        }));

        /**
         * Instalaciones
         */
        Proveedor proveedorPadre = new Proveedor();
        proveedorPadre.setIdProveedor(proveedor.getIdProveedor());
        List<ProveedorInstalacion> proveedorInstalacionList = dto.getInstalaciones();
        if (Optional.ofNullable(proveedorInstalacionList).isPresent()) {
            for (ProveedorInstalacion bean : proveedorInstalacionList) {
                bean.setIdProveedor(proveedorPadre);
                logger.error("Instalaciones bean instalacion: " + bean.toString());
            }
            logger.error("Instalaciones proveedorInstalacionList: " + proveedorInstalacionList.toString());
            this.proveedorInstalacionRepository.saveAll(proveedorInstalacionList);
        }

        /**
         * Permisos
         */
        List<ProveedorPermiso> proveedorPermisoList = dto.getPermisos();
        if (Optional.ofNullable(proveedorPermisoList).isPresent()) {
            for (ProveedorPermiso bean : proveedorPermisoList) {
                bean.setIdProveedor(proveedorPadre);
            }
            this.proveedorPermisoRepository.saveAll(proveedorPermisoList);
        }

        /**
         * Principales
         */
        List<ProveedorCliente> proveedorClienteList = dto.getPrincipales();
        if (Optional.ofNullable(proveedorClienteList).isPresent()) {
            for (ProveedorCliente bean : proveedorClienteList) {
                bean.setIdProveedor(proveedorPadre);
            }
            this.proveedorClienteRepository.saveAll(proveedorClienteList);
        }

        /**
         * Adicionales
         */
        List<ProveedorFuncionario> proveedorFuncionarioList = dto.getAdicionales();
        if (Optional.ofNullable(proveedorFuncionarioList).isPresent()) {
            for (ProveedorFuncionario bean : proveedorFuncionarioList) {
                bean.setIdProveedor(proveedorPadre);
            }
            this.proveedorFuncionarioRepository.saveAll(proveedorFuncionarioList);
        }

        /**
         * Pregunta Informacion
         */
        List<ProveedorPreguntaInformacion> proveedorPreguntaInformacionList = dto.getPreguntaInformacion();
        if (Optional.ofNullable(proveedorPreguntaInformacionList).isPresent()) {
            for (ProveedorPreguntaInformacion bean : proveedorPreguntaInformacionList) {
                bean.setIdProveedor(proveedorPadre);
                PreguntaInformacion preguntaInformacion = bean.getIdPreguntaInformacion();
                if (preguntaInformacion.isRespuestaSiNo()) {
                    bean.setRespuesta(Constant.N);
                    if (bean.isRespuestaSiNo()) {
                        bean.setRespuesta(Constant.S);
                    }
                }
            }
            this.proveedorPreguntaInformacionRepository.saveAll(proveedorPreguntaInformacionList);
        }

        /**
         *  Sector de trabajo
         */
        Optional.ofNullable(dto.getSectorTrabajos())
                .ifPresent(list -> list.stream()
                        .map(SectorTrabajoDto::getIdSectorTrabajo)
                        .map(id -> this.sectorTrabajoRepository.getOne(id))
                        .forEach(st -> {
                            final ProveedorSectorTrabajo pst = new ProveedorSectorTrabajo();
                            pst.setSectorTrabajo(st);
                            pst.setProveedor(proveedor);
                            this.proveedorSectorTrabajoRepository.save(pst);
                        }));

        /**
         * Cuenta Bancarios Extranjeros
         */
        List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjList = dto.getProveedorCuentaBancariaExtranjList();
        if (Optional.ofNullable(proveedorCuentaBancariaExtranjList).isPresent()) {
            for (ProveedorCuentaBancariaExtranj bean : proveedorCuentaBancariaExtranjList) {
                bean.setIdProveedor(proveedorPadre);
                this.proveedorCuentaBancariaExtranjRepository.save(bean);
            }

        }


        logger.error("Finalizando GRABAR Sector trabajo");
        /////guardar AdjuntoSunat

        List<ProveedorAdjuntoSunat> listAdjuntosSunat = this.guardarAdjuntoSunat(proveedor, dto.getAdjuntosSunat(), null);
        logger.error("Finalizando GRABAR AdjuntoSunat");


        ////guardar Catalogos
        List<ProveedorCatalogo> listCatalogo=this.guardarAdjuntoCatalogo(proveedor,dto.getCatalogos(),null);
        logger.error("Finalizando GRABAR catalogos");


        dto.setIdProveedor(proveedor.getIdProveedor());
        //dto.setAdjuntosSunat(listAdjuntosSunat);
        logger.error("Finalizando GRABAR PROVEEDOR Service");


        /**
         * Notificamos a los usuarios dependiento al estado en el que se encuentra el proveedor
         * */
        SendNotifyUserDto sendNotifyUser = SendNotifyUserDto.builder()
                .supplier( proveedor )
                .statusSupplier( proveedor.getIdEstadoProveedor().getCodigoEstadoProveedor() )
                .build();

        notificacionFlujoAprobacion.sendNotifyUser( sendNotifyUser );

       /* // Enviando correo a los usuarios de Data Maestra
        if (esNuevo) {

            logger.error("Finalizando GRABAR PROVEEDOR envio correo Data Maestra");

            List<UsuarioSCPDataMaestraDto> usuarioSCPDataMaestraDtoList = dto.getUsuarioSCPDataMaestraDtoList();
            if (usuarioSCPDataMaestraDtoList != null && usuarioSCPDataMaestraDtoList.size() > 0) {
                Proveedor proveedorCorreo = new Proveedor();
                proveedorCorreo.setRazonSocial(dto.getRazonSocial());
                proveedorCorreo.setRuc(dto.getRuc());
                logger.error("Finalizando GRABAR PROVEEDOR envio correo Data Maestra 01 " + proveedorCorreo.toString());
                for (UsuarioSCPDataMaestraDto beanScp : usuarioSCPDataMaestraDtoList) {
                    *//*this.proveedorRevisionDataMaestraNotificacion.enviar(
                            proveedorCorreo,
                            beanScp
                    );*//*


                }
            }

        }

        AppParametria paramDest =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_2", "1");
        logger.error("Logger_saveDto:: paramDest " + paramDest);
        if(paramDest != null) {
            this.gestionProveedorMisDatosNotificacion.enviar(paramDest.getValue1(), paramDest.getValue2(),  dto.getRazonSocial(), dto.getRuc());
            //this.gestionProveedorNotificacion.enviar(paramDest.getValue1(), paramDest.getValue2(),  dto.getRazonSocial());
            //registroSolicitudProveedorNotificacion.enviar(preRegistro, paramDest.getValue1(), paramDest.getValue2());
        }*/

        dto.setEsNuevo(esNuevo);
        logger.error("Finalizando GRABAR PROVEEDOR proveedorPorValidarInfoList size:  " + proveedorPorValidarInfoList.size());
        if (proveedorPorValidarInfoList != null && proveedorPorValidarInfoList.size() > 0) {
            logger.error("Finalizando GRABAR PROVEEDOR proveedorPorValidarInfoList:  " + proveedorPorValidarInfoList.toString());
            for(ProveedorPorValidarInfo beanInfo : proveedorPorValidarInfoList) {
                this.proveedorPorValidarInfoRepository.save(beanInfo);
            }

        }
        logger.error("Finalizando GRABAR PROVEEDOR FIN");
        return dto;
    }

    @Override
    public List<Proveedor> getListProveedor() {
        return proveedorRepository.findAll();
    }

    @Override
    public List<ProveedorCustom> getListProveedorByFiltro(ProveedorFiltro proveedorFiltro) {

        return proveedorMapperMybatis.getListProveedorByFiltro(
                proveedorFiltro.getIdsPais(),
                proveedorFiltro.getRazonSocial(),
                proveedorFiltro.getIdsRegion(),
                proveedorFiltro.getIdsProvincia(),
                proveedorFiltro.getNroRuc(),
                proveedorFiltro.getTipoProveedor(),
                proveedorFiltro.getTipoPersona(),
                proveedorFiltro.getIndHomologado(),
                proveedorFiltro.getIndPorValidarInfoAcreedor(),
                proveedorFiltro.getMarca(),
                proveedorFiltro.getProducto(),
                proveedorFiltro.getDescripcionAdicional(),
                proveedorFiltro.getIdsLinea(),
                proveedorFiltro.getIdsFamilia(),
                proveedorFiltro.getIdsSubFamilia(),
                proveedorFiltro.getEstadoProveedor()
        );
    }

    @Override
    public List<ProveedorCustom> getListProveedorByFiltroPaginado(ProveedorFiltro proveedorFiltro) {
        Integer paginaMostrar = new Integer((proveedorFiltro.getPaginaMostrar().intValue() - 1) * proveedorFiltro.getNroRegistros());
        proveedorFiltro.setPaginaMostrar(paginaMostrar);
        return proveedorMapperMybatis.getListProveedorByFiltroPaginado(
                proveedorFiltro.getIdsPais(),
                proveedorFiltro.getRazonSocial(),
                proveedorFiltro.getIdsRegion(),
                proveedorFiltro.getIdsProvincia(),
                proveedorFiltro.getNroRuc(),
                proveedorFiltro.getTipoProveedor(),
                proveedorFiltro.getTipoPersona(),
                proveedorFiltro.getIndHomologado(),
                proveedorFiltro.getIndPorValidarInfoAcreedor(),
                proveedorFiltro.getMarca(),
                proveedorFiltro.getProducto(),
                proveedorFiltro.getDescripcionAdicional(),
                proveedorFiltro.getIdsLinea(),
                proveedorFiltro.getIdsFamilia(),
                proveedorFiltro.getIdsSubFamilia(),
                proveedorFiltro.getEstadoProveedor(),
                proveedorFiltro.getNroRegistros(),
                proveedorFiltro.getPaginaMostrar()
        );
    }

    @Override
    public List<ProveedorCustom> getListProveedorByFiltroValidacion(UserSession userSession, ProveedorFiltro proveedorFiltro) {


        return proveedorMapperMybatis.getListProveedorByFiltroValidacion(
                proveedorFiltro.getIdsPais(),
                proveedorFiltro.getIdsRegion(),
                proveedorFiltro.getIdsProvincia(),
                proveedorFiltro.getNroRuc(),
                proveedorFiltro.getRazonSocial(),
                proveedorFiltro.getTipoProveedor(),
                proveedorFiltro.getTipoPersona(),
                proveedorFiltro.getIndHomologado(),
                proveedorFiltro.getIndPorValidarInfoAcreedor(),
                proveedorFiltro.getMarca(),
                proveedorFiltro.getProducto(),
                proveedorFiltro.getDescripcionAdicional(),
                proveedorFiltro.getIdsLinea(),
                proveedorFiltro.getIdsFamilia(),
                proveedorFiltro.getIdsSubFamilia()
        );
    }

    @Override
    public List<ProveedorCustom> getListProveedorByFiltroLicitacion(ProveedorFiltro proveedorFiltro) {
        return proveedorMapperMybatis.getListProveedorByFiltroLicitacion(
                proveedorFiltro.getIdsPais(),
                proveedorFiltro.getRazonSocial(),
                proveedorFiltro.getIdsRegion(),
                proveedorFiltro.getIdsProvincia(),
                proveedorFiltro.getNroRuc(),
                proveedorFiltro.getTipoProveedor(),
                proveedorFiltro.getTipoPersona(),
                proveedorFiltro.getIndHomologado(),
                proveedorFiltro.getIndPorValidarInfoAcreedor(),
                proveedorFiltro.getMarca(),
                proveedorFiltro.getProducto(),
                proveedorFiltro.getDescripcionAdicional(),
                proveedorFiltro.getIdsLinea(),
                proveedorFiltro.getIdsFamilia(),
                proveedorFiltro.getIdsSubFamilia()
        );
    }

    @Override
    public List<ProveedorCustom> getListProveedorByFiltroLicitacionPaginado(ProveedorFiltro proveedorFiltro) {
        Integer paginaMostrar = new Integer((proveedorFiltro.getPaginaMostrar().intValue() - 1) * proveedorFiltro.getNroRegistros());
        proveedorFiltro.setPaginaMostrar(paginaMostrar);
        return proveedorMapperMybatis.getListProveedorByFiltroLicitacionPaginado(
                proveedorFiltro.getIdsPais(),
                proveedorFiltro.getRazonSocial(),
                proveedorFiltro.getIdsRegion(),
                proveedorFiltro.getIdsProvincia(),
                proveedorFiltro.getNroRuc(),
                proveedorFiltro.getTipoProveedor(),
                proveedorFiltro.getTipoPersona(),
                proveedorFiltro.getIndHomologado(),
                proveedorFiltro.getIndPorValidarInfoAcreedor(),
                proveedorFiltro.getMarca(),
                proveedorFiltro.getProducto(),
                proveedorFiltro.getDescripcionAdicional(),
                proveedorFiltro.getIdsLinea(),
                proveedorFiltro.getIdsFamilia(),
                proveedorFiltro.getIdsSubFamilia(),
                proveedorFiltro.getNroRegistros(),
                proveedorFiltro.getPaginaMostrar()
        );
    }

    @Override
    public List<ProveedorCustom> getListProveedorByRuc(String ruc) {
        return proveedorMapperMybatis.getListProveedorByRuc(ruc);
}

    @Override
    public List<ProveedorCustom> getListProveedorSinHcpID() {
        return proveedorMapperMybatis.getListProveedorMigrados();
    }

    @Override
    public List<LineaComercialDto> getListLineaComercialByIdProveedor(Integer idProveedor) {
        return Optional.ofNullable(homologacionMapper.getListHomologacionByIdProveedor(idProveedor))
                .map(l -> {
                    List<LineaComercialDto> list = new ArrayList<>();
                    l.stream().map(linea -> {
                        LineaComercialDto dto = new LineaComercialDto();
                        dto.setIdLinea(linea.getIdLinea());
                        dto.setLinea(linea.getLinea());
                        return dto;
                    }).forEach(list::add);
                    return list;
                })
                .orElse(new ArrayList<>());
    }

    @Override
    public List<ProveedorCatalogoDto> getListCatalogosByIdProveedor(Integer idProveedor) {
        ProveedorCatalogoDTOMapper catalogoMapper = new ProveedorCatalogoDTOMapper();

        return Optional.ofNullable(proveedorCatalogoRepository)
                .map(r -> r.getProveedorCatalogoByIdProveedor(idProveedor))
                .map(l -> {
                    List<ProveedorCatalogoDto> list = new ArrayList<>();
                    l.stream().map(catalogoMapper::toDto).forEach(list::add);
                    return list;
                })
                .orElse(new ArrayList<>());
    }

    public ProveedorDto toDtoResponder(Proveedor proveedor) {
        /**
         * Información del proveedor
         */
        ProveedorDTOMapper proveedorDtoMapper = new ProveedorDTOMapper(this.condicionPagoReposity,
                this.ubigeoMapper,
                this.monedaRepository,
                this.tipoComprobanteRepository,
                this.tipoProveedorRepository);
        Optional<ProveedorDto> oDto = Optional.ofNullable(proveedor)
                .map(proveedorDtoMapper::toDto)
                .map(dto -> {
                    if (dto.getIdProveedor() == null) {
                        return dto;
                    }

                    int idProveedor = dto.getIdProveedor();

                    /**
                     *  Contactos por canal de distribución
                     */
                    Optional.ofNullable(this.contactoMapper)
                            .map(r -> {
                                List<CanalContactoDto> list = r.getListContactoByIdProveedor(idProveedor);
                                return list;
                            }).ifPresent(l -> l.stream().forEach(dto::addCanalContacto));

                    /**
                     *  Cuentas de Banco
                     */

                    CuentaBancoDTOMapper bancoDTOMapper = new CuentaBancoDTOMapper(this.bancoRepository,
                            this.monedaRepository);
                    Optional.ofNullable(this.cuentaBancariaMapper)
                            .map(r -> r.getListCuentaByIdProveedor(ParametroConstant.CUENTA_BANCO,
                                    ParametroTipoConstant.TIPO,
                                    dto.getIdProveedor()))
                            .ifPresent(l -> l.stream()
                                    .map(bancoDTOMapper::toDto)
                                    .forEach(dto::addCuentaBanco));
                    /**
                     * Líneas comerciales
                     */
                    ProveedorLineaComercialDTOMapper lineaDtoMapper = new ProveedorLineaComercialDTOMapper(this.lineaComercialRepository);
                    Optional.ofNullable(this.proveedorLineaComercialRepository)
                            .map(r -> r.getListLineaComercialByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(lineaDtoMapper::toDto)
                                    .forEach(dto::addLineaComercial));

                    /**
                     * Evaluacion de Homologación
                     */

                    Optional.ofNullable(homologacionMapper)
                            .map(r -> r.getListHomologacionByIdProveedorResponder(idProveedor))
                            .ifPresent(l -> {
                                l.stream().forEach(lch -> {
                                    lch.getPreguntas().forEach(p -> {
                                        final ProveedorHomologacionDto data = new ProveedorHomologacionDto();
                                        data.setIdLineaComercial(lch.getIdLinea());
                                        data.setLineaComercial(lch.getLinea());
                                        data.setIdHomologacion(p.getIdHomologacion());
                                        data.setPregunta(p.getPregunta());
                                        data.setIntAdjunto(p.getIndicadorAdjunto());
                                        data.setEstado(p.getEstado());
                                        data.setIndEstado(false);
                                        data.setValorRespuestaLibre(p.getValorRespuestaLibre());
                                        if (data.getEstado().equals("1")) {
                                            data.setIndEstado(true);
                                        }
                                        Optional.ofNullable(p.getRespuestaProveedor())
                                                .ifPresent(resp -> {
                                                    data.setRutaAdjunto(resp.getRutaAdjunto());
                                                    data.setArchivoId(resp.getArchivoId());
                                                    data.setArchivoNombre(resp.getNombreArchivo());
                                                    p.getOpciones().stream()
                                                            .filter(opt -> opt.getIdHomologacionRespuesta().equals(resp.getIdHomologacionRespuesta()))
                                                            .findFirst()
                                                            .map(f -> f.getRespuesta())
                                                            .ifPresent(data::setRespuesta);
                                                });
                                        dto.addRespuestaHomologacion(data);
                                    });
                                });
                            });

                    /**
                     * Productos
                     */
                    ProveedorProductoDTOMapper productoDtoMapper = new ProveedorProductoDTOMapper();
                    Optional.ofNullable(proveedorProductoRepository)
                            .map(r -> r.getListProductoByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(productoDtoMapper::toDto)
                                    .forEach(dto::addProducto));

                    /**
                     * Evaluación de desempeño
                     */
                    EvaluacionDTOMapper evaluacionDtoMapper = new EvaluacionDTOMapper();
                    Optional.ofNullable(proveedorEvaluacionRepository)
                            .map(r -> r.getProveedorEvaluacionByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(evaluacionDtoMapper::toDto)
                                    .forEach(dto::addEvaluacionDesempenio));


                    /**
                     * Catálogos
                     */

                    ProveedorCatalogoDTOMapper catalogoMapper = new ProveedorCatalogoDTOMapper();
                    Optional.ofNullable(proveedorCatalogoRepository)
                            .map(r -> r.getProveedorCatalogoByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream().map(catalogoMapper::toDto)
                                    .forEach(dto::addCatalogo));


                    ///adjuntoSunat
                    ProveedorAdjuntoSunatDTOMapper adjuntoSunatDTOMapper = new ProveedorAdjuntoSunatDTOMapper();
                    Optional.ofNullable(proveedorAdjuntoSunatRepository)
                            .map(r -> r.getProveedorAdjuntoSunatByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream().map(adjuntoSunatDTOMapper::toDto)
                                    .forEach(dto::addAdjuntoSunat));

                  /*  List<ProveedorAdjuntoSunat> proveedorAdjuntoSunatList =this.proveedorAdjuntoSunatRepository.getProveedorAdjuntoSunatByIdProveedor(idProveedor);
                    dto.setAdjuntosSunat(proveedorAdjuntoSunatList);*/
                    /**



                     * Instalacion
                     */
                    ProveedorInstalacion proveedorInstalacion = new ProveedorInstalacion();
                    proveedorInstalacion.setIdBuscarProveedor(idProveedor);
                    List<ProveedorInstalacion> proveedorInstalacionList =
                            this.proveedorInstalacionMapper.getProveedorInstalacion(proveedorInstalacion);

                    dto.setInstalaciones(proveedorInstalacionList);

                    /**
                     * Cuenta Bancaria Extranjeras
                     */
                    Proveedor proveedorBuscar = new Proveedor();
                    proveedorBuscar.setIdProveedor(idProveedor);
                    List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjList =
                            this.proveedorCuentaBancariaExtranjMapper.getProveedorCuentaBancariaExtranjPorProveedor(proveedorBuscar);

                    dto.setProveedorCuentaBancariaExtranjList(proveedorCuentaBancariaExtranjList);

                    /**
                     * Permisos
                     */
                    ProveedorPermiso proveedorPermiso = new ProveedorPermiso();
                    proveedorPermiso.setIdBuscarProveedor(idProveedor);
                    List<ProveedorPermiso> proveedorPermisoList =
                            this.proveedorPermisoMapper.getProveedorPermiso(proveedorPermiso);
                    dto.setPermisos(proveedorPermisoList);

                    /**
                     * Principales
                     */
                    ProveedorCliente proveedorCliente = new ProveedorCliente();
                    proveedorCliente.setIdBuscarProveedor(idProveedor);
                    List<ProveedorCliente> proveedorClienteList =
                            this.proveedorClienteMapper.getProveedorCliente(proveedorCliente);
                    dto.setPrincipales(proveedorClienteList);

                    /**
                     * Adicionales
                     */
                    ProveedorFuncionario proveedorFuncionario = new ProveedorFuncionario();
                    proveedorFuncionario.setIdBuscarProveedor(idProveedor);
                    List<ProveedorFuncionario> proveedorFuncionarioList =
                            this.proveedorFuncionarioMapper.getProveedorFuncionario(proveedorFuncionario);
                    dto.setAdicionales(proveedorFuncionarioList);
                    logger.error("toDto 05 Adicionales " + proveedorFuncionarioList.toString());

                    /**
                     * Pregunta Informacion
                     */
                    List<PreguntaInformacion> preguntaInformacionList =
                            this.preguntaInformacionRepository.findAll(Sort.by("orden"));
                    List<ProveedorPreguntaInformacion> proveedorPreguntaInformacionList = new ArrayList<ProveedorPreguntaInformacion>();
                    for (PreguntaInformacion bean : preguntaInformacionList) {
                        ProveedorPreguntaInformacion beanBuscar = new ProveedorPreguntaInformacion();
                        beanBuscar.setIdBuscarProveedor(idProveedor);
                        beanBuscar.setIdBuscarPreguntaInformacion(bean.getId());
                        List<ProveedorPreguntaInformacion> lista =
                                this.proveedorPreguntaInformacionMapper.getProveedorPreguntaInformacion(beanBuscar);

                        ProveedorPreguntaInformacion proveedorPreguntaInformacion = new ProveedorPreguntaInformacion();
                        proveedorPreguntaInformacion.setRespuestaSiNo(false);
                        if (lista != null && lista.size() > 0) {
                            proveedorPreguntaInformacion = lista.get(0);
                        }
                        if (Optional.ofNullable(proveedorPreguntaInformacion.getRespuesta()).isPresent()) {
                            if (proveedorPreguntaInformacion.getRespuesta().equals(Constant.S)) {
                                proveedorPreguntaInformacion.setRespuestaSiNo(true);
                            }
                        }

                        proveedorPreguntaInformacion.setIdPreguntaInformacion(bean);
                        proveedorPreguntaInformacionList.add(proveedorPreguntaInformacion);
                    }
                    dto.setPreguntaInformacion(proveedorPreguntaInformacionList);

                    /**
                     * Sector de trabajo
                     */
                    Optional.ofNullable(proveedorSectorTrabajoRepository)
                            .map(r -> r.getListSectorTrabajoByIdProveedor(idProveedor))
                            .ifPresent(l -> {
                                List<SectorTrabajoDto> listDto = new ArrayList<>();
                                l.stream()
                                        .map(ProveedorSectorTrabajo::getSectorTrabajo)
                                        .map(sectorTrabajo -> sectorTrabajoPopulater.toDto(sectorTrabajo))
                                        .forEach(listDto::add);
                                dto.setSectorTrabajos(listDto);
                            });


                    /* Obteniendo los valores antiguos del proveedor */
                    ProveedorPorValidarInfo proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "RAZON_SOCIAL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setRazonSocialAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    logger.error("toDto 08 dto: " + dto.toString());

                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "DIRECCION_FISCAL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setDireccionFiscalAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "EMAIL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setEmailAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository
                                    .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                            TypeReference<List<ProveedorCuentaBancaria>> mapType = new TypeReference<List<ProveedorCuentaBancaria>>() {};
                            List<ProveedorCuentaBancaria> jsonToCuentaList = objectMapper.readValue(proveedorPorValidarInfo.getValorListaActual(), mapType);
                            dto.setProveedorCuentaBancariaListAnterior(jsonToCuentaList);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    logger.error("toDto 09 dto: " + dto.toString());
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository
                                    .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA_EXTRANJERA");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                            TypeReference<List<ProveedorCuentaBancariaExtranj>> mapType = new TypeReference<List<ProveedorCuentaBancariaExtranj>>() {};
                            List<ProveedorCuentaBancariaExtranj> jsonToCuentaList = objectMapper.readValue(proveedorPorValidarInfo.getValorListaActual(), mapType);
                            dto.setProveedorCuentaBancariaExtranjListAnterior(jsonToCuentaList);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    logger.error("toDto 10 dto: " + dto.toString());
                    return dto;
                });

        return oDto.isPresent() ? oDto.get() : null;
    }

    public ProveedorDto toDto(Proveedor proveedor) {
        /**
         * Información del proveedor
         */
        ProveedorDTOMapper proveedorDtoMapper = new ProveedorDTOMapper(this.condicionPagoReposity,
                this.ubigeoMapper,
                this.monedaRepository,
                this.tipoComprobanteRepository,
                this.tipoProveedorRepository);
        Optional<ProveedorDto> oDto = Optional.ofNullable(proveedor)
                .map(proveedorDtoMapper::toDto)
                .map(dto -> {
                    logger.error("toDto 00a dto: " + dto.toString());
                    if (dto.getIdProveedor() == null) {
                        return dto;
                    }

                    int idProveedor = dto.getIdProveedor();
                    logger.error("toDto 01 idProveedor: " + idProveedor);

                    /**
                     *  Contactos por canal de distribución
                     */
                    Optional.ofNullable(this.contactoMapper)
                            .map(r -> {
                                List<CanalContactoDto> list = r.getListContactoByIdProveedor(idProveedor);
                                return list;
                            }).ifPresent(l -> l.stream().forEach(dto::addCanalContacto));

                    /**
                     *  Cuentas de Banco
                     */

                    CuentaBancoDTOMapper bancoDTOMapper = new CuentaBancoDTOMapper(this.bancoRepository,
                            this.monedaRepository);
                    Optional.ofNullable(this.cuentaBancariaMapper)
                            .map(r -> r.getListCuentaByIdProveedor(ParametroConstant.CUENTA_BANCO,
                                    ParametroTipoConstant.TIPO,
                                    dto.getIdProveedor()))
                            .ifPresent(l -> l.stream()
                                    .map(bancoDTOMapper::toDto)
                                    .forEach(dto::addCuentaBanco));
                    /**
                     * Líneas comerciales
                     */
                    ProveedorLineaComercialDTOMapper lineaDtoMapper = new ProveedorLineaComercialDTOMapper(this.lineaComercialRepository);
                    Optional.ofNullable(this.proveedorLineaComercialRepository)
                            .map(r -> r.getListLineaComercialByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(lineaDtoMapper::toDto)
                                    .forEach(dto::addLineaComercial));

                    /**
                     * Evaluacion de Homologación
                     */
                    Optional.ofNullable(homologacionMapper)
                            .map(r -> r.getListHomologacionByIdProveedor(idProveedor))
                            .ifPresent(l -> {
                                l.stream().forEach(lch -> {
                                    lch.getPreguntas().forEach(p -> {
                                        final ProveedorHomologacionDto data = new ProveedorHomologacionDto();
                                        data.setIdLineaComercial(lch.getIdLinea());
                                        data.setLineaComercial(lch.getLinea());
                                        data.setIdHomologacion(p.getIdHomologacion());
                                        data.setPregunta(p.getPregunta());
                                        data.setPeso(p.getPeso());
                                        data.setIntAdjunto(p.getIndicadorAdjunto());
                                        data.setEstado(p.getEstado());
                                        data.setIndEstado(false);
                                        data.setValorRespuestaLibre(p.getValorRespuestaLibre());
                                        if (data.getEstado().equals("1")) {
                                            data.setIndEstado(true);
                                        }
                                        Optional.ofNullable(p.getRespuestaProveedor())
                                                .ifPresent(resp -> {
                                                    data.setRutaAdjunto(resp.getRutaAdjunto());
                                                    data.setArchivoId(resp.getArchivoId());
                                                    data.setArchivoNombre(resp.getNombreArchivo());
                                                    p.getOpciones().stream()
                                                            .filter(opt -> opt.getIdHomologacionRespuesta().equals(resp.getIdHomologacionRespuesta()))
                                                            .findFirst()
                                                            .map(f -> f.getRespuesta())
                                                            .ifPresent(data::setRespuesta);
                                                });
                                        dto.addRespuestaHomologacion(data);
                                    });
                                });
                            });
                    /**
                     * Productos
                     */
                    ProveedorProductoDTOMapper productoDtoMapper = new ProveedorProductoDTOMapper();
                    Optional.ofNullable(proveedorProductoRepository)
                            .map(r -> r.getListProductoByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(productoDtoMapper::toDto)
                                    .forEach(dto::addProducto));

                    /**
                     * Evaluación de desempeño
                     */
                    EvaluacionDTOMapper evaluacionDtoMapper = new EvaluacionDTOMapper();
                    Optional.ofNullable(proveedorEvaluacionRepository)
                            .map(r -> r.getProveedorEvaluacionByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream()
                                    .map(evaluacionDtoMapper::toDto)
                                    .forEach(dto::addEvaluacionDesempenio));


                    /**
                     * Catálogos
                     */

                    ProveedorCatalogoDTOMapper catalogoMapper = new ProveedorCatalogoDTOMapper();
                    Optional.ofNullable(proveedorCatalogoRepository)
                            .map(r -> r.getProveedorCatalogoByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream().map(catalogoMapper::toDto)
                                    .forEach(dto::addCatalogo));

                    logger.error("toDto 02 ");

                    //adjuntoSunat

                    ProveedorAdjuntoSunatDTOMapper adjuntoSunatDTOMapper = new ProveedorAdjuntoSunatDTOMapper();
                    Optional.ofNullable(proveedorAdjuntoSunatRepository)
                            .map(r -> r.getProveedorAdjuntoSunatByIdProveedor(idProveedor))
                            .ifPresent(l -> l.stream().map(adjuntoSunatDTOMapper::toDto)
                                    .forEach(dto::addAdjuntoSunat));

                    /*
                    List<ProveedorAdjuntoSunat> adjuntoSunatList= this.proveedorAdjuntoSunatRepository.getProveedorAdjuntoSunatByIdProveedor(idProveedor);
                    dto.setAdjuntosSunat(adjuntoSunatList);*/
                    /**
                     * Instalacion
                     */
                    ProveedorInstalacion proveedorInstalacion = new ProveedorInstalacion();
                    proveedorInstalacion.setIdBuscarProveedor(idProveedor);
                    List<ProveedorInstalacion> proveedorInstalacionList =
                            this.proveedorInstalacionMapper.getProveedorInstalacion(proveedorInstalacion);

                    dto.setInstalaciones(proveedorInstalacionList);
                    logger.error("toDto 03 Instalacion " + proveedorInstalacionList.toString());

                    /**
                     * Cuenta Bancaria Extranjeras
                     */
                    Proveedor proveedorBuscar = new Proveedor();
                    proveedorBuscar.setIdProveedor(idProveedor);
                    List<ProveedorCuentaBancariaExtranj> proveedorCuentaBancariaExtranjList =
                            this.proveedorCuentaBancariaExtranjMapper.getProveedorCuentaBancariaExtranjPorProveedor(proveedorBuscar);

                    dto.setProveedorCuentaBancariaExtranjList(proveedorCuentaBancariaExtranjList);


                    /**
                     * Permisos
                     */
                    ProveedorPermiso proveedorPermiso = new ProveedorPermiso();
                    proveedorPermiso.setIdBuscarProveedor(idProveedor);
                    List<ProveedorPermiso> proveedorPermisoList =
                            this.proveedorPermisoMapper.getProveedorPermiso(proveedorPermiso);
                    dto.setPermisos(proveedorPermisoList);
                    logger.error("toDto 03 Permisos " + proveedorPermisoList.toString());

                    /**
                     * Principales
                     */
                    ProveedorCliente proveedorCliente = new ProveedorCliente();
                    proveedorCliente.setIdBuscarProveedor(idProveedor);
                    List<ProveedorCliente> proveedorClienteList =
                            this.proveedorClienteMapper.getProveedorCliente(proveedorCliente);
                    dto.setPrincipales(proveedorClienteList);
                    logger.error("toDto 04 Principales " + proveedorClienteList.toString());

                    /**
                     * Adicionales
                     */
                    ProveedorFuncionario proveedorFuncionario = new ProveedorFuncionario();
                    proveedorFuncionario.setIdBuscarProveedor(idProveedor);
                    List<ProveedorFuncionario> proveedorFuncionarioList =
                            this.proveedorFuncionarioMapper.getProveedorFuncionario(proveedorFuncionario);
                    dto.setAdicionales(proveedorFuncionarioList);
                    logger.error("toDto 05 Adicionales " + proveedorFuncionarioList.toString());

                    /**
                     * Pregunta Informacion
                     */
                    List<PreguntaInformacion> preguntaInformacionList =
                            this.preguntaInformacionRepository.findAll(Sort.by("orden"));
                    List<ProveedorPreguntaInformacion> proveedorPreguntaInformacionList = new ArrayList<ProveedorPreguntaInformacion>();
                    for (PreguntaInformacion bean : preguntaInformacionList) {
                        ProveedorPreguntaInformacion beanBuscar = new ProveedorPreguntaInformacion();
                        beanBuscar.setIdBuscarProveedor(idProveedor);
                        beanBuscar.setIdBuscarPreguntaInformacion(bean.getId());
                        List<ProveedorPreguntaInformacion> lista =
                                this.proveedorPreguntaInformacionMapper.getProveedorPreguntaInformacion(beanBuscar);

                        ProveedorPreguntaInformacion proveedorPreguntaInformacion = new ProveedorPreguntaInformacion();
                        proveedorPreguntaInformacion.setRespuestaSiNo(false);
                        if (lista != null && lista.size() > 0) {
                            proveedorPreguntaInformacion = lista.get(0);
                        }
                        if (Optional.ofNullable(proveedorPreguntaInformacion.getRespuesta()).isPresent()) {
                            if (proveedorPreguntaInformacion.getRespuesta().equals(Constant.S)) {
                                proveedorPreguntaInformacion.setRespuestaSiNo(true);
                            }
                        }

                        proveedorPreguntaInformacion.setIdPreguntaInformacion(bean);
                        proveedorPreguntaInformacionList.add(proveedorPreguntaInformacion);
                    }
                    dto.setPreguntaInformacion(proveedorPreguntaInformacionList);
                    logger.error("toDto 06 Pregunta " + proveedorPreguntaInformacionList.toString());

                    /**
                     * Sector de trabajo
                     */
                    Optional.ofNullable(proveedorSectorTrabajoRepository)
                            .map(r -> r.getListSectorTrabajoByIdProveedor(idProveedor))
                            .ifPresent(l -> {
                                List<SectorTrabajoDto> listDto = new ArrayList<>();
                                l.stream()
                                        .map(ProveedorSectorTrabajo::getSectorTrabajo)
                                        .map(sectorTrabajo -> sectorTrabajoPopulater.toDto(sectorTrabajo))
                                        .forEach(listDto::add);
                                dto.setSectorTrabajos(listDto);
                            });

                    logger.error("toDto 07 dto: " + dto.toString());

                    /* Obteniendo los valores antiguos del proveedor */
                    ProveedorPorValidarInfo proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "RAZON_SOCIAL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setRazonSocialAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    logger.error("toDto 08 dto: " + dto.toString());

                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "DIRECCION_FISCAL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setDireccionFiscalAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository.getByIdProveedorAndNombreCampoBd(proveedorBuscar, "EMAIL");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        dto.setEmailAnterior(proveedorPorValidarInfo.getValorActual());
                    }
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository
                                    .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                            TypeReference<List<ProveedorCuentaBancaria>> mapType = new TypeReference<List<ProveedorCuentaBancaria>>() {};
                            List<ProveedorCuentaBancaria> jsonToCuentaList = objectMapper.readValue(proveedorPorValidarInfo.getValorListaActual(), mapType);
                            dto.setProveedorCuentaBancariaListAnterior(jsonToCuentaList);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    logger.error("toDto 09 dto: " + dto.toString());
                    proveedorPorValidarInfo =
                            this.proveedorPorValidarInfoRepository
                                    .getByIdProveedorAndNombreCampoBd(proveedorBuscar, "LISTA_CUENTA_BANCARIA_EXTRANJERA");
                    if (Optional.ofNullable(proveedorPorValidarInfo).isPresent()) {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                            TypeReference<List<ProveedorCuentaBancariaExtranj>> mapType = new TypeReference<List<ProveedorCuentaBancariaExtranj>>() {};
                            List<ProveedorCuentaBancariaExtranj> jsonToCuentaList = objectMapper.readValue(proveedorPorValidarInfo.getValorListaActual(), mapType);
                            dto.setProveedorCuentaBancariaExtranjListAnterior(jsonToCuentaList);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    /**
                     * CC. Nuevo Flujo Aprobaciones
                     * */
                    dto.setEnFlujoAprobacion( UtilString.coalesceTrim( proveedor.getEnFlujoAprobacion() ).equals( "X" ) );
                    dto.setCodigoMaximoEstadoAprobado( UtilString.coalesceTrim( proveedor.getCodigoMaximoEstadoAprobado() ) );

                    logger.error("toDto 10 dto: " + dto.toString());
                    return dto;
                });
        if (oDto.isPresent()) {
            logger.error("toDto FINa dto: " + oDto.get().toString());
        }
        return oDto.isPresent() ? oDto.get() : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor getProveedorByRuc(String ruc) {
        return this.proveedorRepository.getProveedorByRuc(ruc);
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDto getProveedorDtoByIdHcp(String idHcp) throws PortalException {
        return Optional.ofNullable(proveedorRepository)
                .map(r -> r.getProveedorByIdHcp(idHcp))
                .map(this::toDto)
                .orElse(new ProveedorDto());
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor getProveedorByIdHcp(String idHcp) throws PortalException {
        return Optional.ofNullable(proveedorRepository)
                .map(r -> r.getProveedorByIdHcp(idHcp))
                .orElse(new Proveedor());
    }

    @Override
    public ProveedorDto getProveedorDtoByRuc(String ruc) {
        return Optional.ofNullable(proveedorRepository)
                .map(r -> r.getProveedorByRuc(ruc))
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public ProveedorDto getProveedorDtoByRucResponder(String ruc) {
        return Optional.ofNullable(proveedorRepository)
                .map(r -> r.getProveedorByRuc(ruc))
                .map(this::toDtoResponder)
                .orElse(null);
    }


    public List<ProveedorDatosGeneralesDTO> getProveedorDatosGenerales(
            String fechaCreacionIni, String fechaCreacionFin) throws PortalException {
        Date dFechaCreacionIni = new Date();
        Date dFechaCreacionFin = new Date();
        int indicador = 0;

        List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        if (StringUtils.isNotBlank(fechaCreacionIni)) {
            indicador = 1;
            if (StringUtils.isNotBlank(fechaCreacionFin))
                indicador = 2;
        }

        try {
            switch (indicador) {
                case 1:
                    dFechaCreacionIni = DateUtils.convertStringToDate(fechaCreacionIni);
                    break;
                case 2:
                    dFechaCreacionIni = DateUtils.convertStringToDate(fechaCreacionIni);
                    dFechaCreacionFin = DateUtils.convertStringToDate(fechaCreacionFin);
                    break;
            }
        } catch (ParseException e) {
            indicador = 0;
        }


        switch (indicador) {
            case 0:
                listaProveedor = this.proveedorRepository.findAllByOrderByRuc();
                break;
            case 1:
                listaProveedor = this.proveedorRepository.findByFechaCreacionGreaterThanEqualOrderByRuc(dFechaCreacionIni);
                break;
            case 2:
                listaProveedor = this.proveedorRepository.findByFechaCreacionBetweenOrderByRuc(dFechaCreacionIni, dFechaCreacionFin);
                break;
        }

        List<ProveedorDatosGeneralesDTO> listaProveedorDatosGenerales = new ArrayList<ProveedorDatosGeneralesDTO>();
        for (Proveedor item : listaProveedor) {
            ProveedorDatosGeneralesDTO bean = new ProveedorDatosGeneralesDTO();
            bean.setProveedor(item);

//            Integer licitacionesParticipo = this.licitacionProveedorRepository.
//                    countByProveedorAndEstadoLicitacionLicitada(item, LicitacionConstant.ESTADO_ADJUDICADA, LicitacionConstant.ESTADO_ENVIADO_SAP);
//            Integer licitacionesGanadas = this.licitacionProveedorMapper.countByLicitacionesGanadas(item.getIdProveedor());
//            Integer licitacionesPerdidas = licitacionesParticipo.intValue() - licitacionesGanadas.intValue();
//            Integer noConformesRegistrados = this.blackListMapper.countByBlackListRegistrados(
//                    item.getIdProveedor(), Constant.CODIGO_TIPO_SOLICITUD_NO_CONFORME);
//            bean.setLicitacionesParticipo(licitacionesParticipo);
//            bean.setLicitacionesGanadas(licitacionesGanadas);
//            bean.setLicitacionesPerdidas(licitacionesPerdidas);
//            bean.setNoConformesRegistrados(noConformesRegistrados);
            listaProveedorDatosGenerales.add(bean);
        }

        return listaProveedorDatosGenerales;
    }


    public void evaluarDataMaestra(Integer idProveedor) throws PortalException {
        Optional<Proveedor> oProveedor = Optional.ofNullable(proveedorRepository)
                .map(r -> r.getOne(idProveedor));

        if (!oProveedor.isPresent()) {
            throw new PortalException("El proveedor no existe");
        }
        Proveedor proveedorActual = oProveedor.get();
        EstadoProveedor estadoProveedorActual = proveedorActual.getIdEstadoProveedor();
        if (estadoProveedorActual.getCodigoEstadoProveedor().equals(EstadoProveedorEnum.RECHAZADO_DATA_MAESTRA.getCodigo())) {
            throw new PortalException("No es posible Aprobar Data Maestra, debido que dicho Proveedor actualmente esta RECHAZADO x DATA MAESTRA");
        }

        EstadoProveedor estadoProveedor = this.estadoProveedorRepository.
                getByCodigoEstadoProveedor(EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo());

        this.proveedorMapperMybatis.updateEstadoProveedor(
                oProveedor.get().getIdProveedor(),
                estadoProveedor.getId());

        //enviar correo

        proveedorDataMaestraNotificacion.enviar(this.parametroMapper.getMailSetting(), oProveedor.get(),"APR", "");


    }

    @Transactional
    @Override
    public SaveInformationResponse aprobacionCompras( Integer idProveedor, String usuarioSap ) {

        StringBuilder message = new StringBuilder();

        SaveInformationResponse response = SaveInformationResponse.builder()
                .save( false )
                .messageType( "Information" )
                .build();

        UserSession userSession = Optional.ofNullable( systemLoggedUser.getUserSession() )
                .orElseThrow( () -> new PortalException("No se encontro los datos del Usuario Logueado en el IDP") );

        Optional<Proveedor> oProveedor = Optional.ofNullable(proveedorRepository)
                .map(r -> r.getOne(idProveedor));

        if (!oProveedor.isPresent()) {
            throw new PortalException("El proveedor no existe");
        }
        Proveedor proveedorActual = oProveedor.get();
        EstadoProveedor estadoProveedorActual = proveedorActual.getIdEstadoProveedor();
        if ( !estadoProveedorActual.getCodigoEstadoProveedor().equals( EstadoProveedorEnum.REGISTRADO.getCodigo()) ) {
            throw new PortalException("No es posible Aprobar la Data Maestra del proveedor, debido que dicho Proveedor actualmente se encuentra con estado " + estadoProveedorActual.getDescripcionEstadoProveedor() );
        }

        //--- Obtenemos la cantidad de flujos completados por el proveedor
        Integer cantidadFlujosCompletados = UtilInteger.coalesce( proveedorActual.getCantidadFlujosCompletados(), 0 );

        String indHomologado = UtilString.coalesceTrim( proveedorActual.getIndHomologado() );

        EstadoProveedor estadoProveedor = this.estadoProveedorRepository.
                getByCodigoEstadoProveedor(EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo());

        ProveedorRFCResponseDto proveedorRFCResponse = null;

        //--- Crear el proveedor en SAP
        if( StringUtils.isEmpty( proveedorActual.getAcreedorCodigoSap() ) ){

            try {

                proveedorRFCResponse = jcoProveedorService.grabarProveedor( idProveedor, usuarioSap );
                message.append( " El proveedor fue creado en SAP y se le asigno el código " )
                        .append( proveedorRFCResponse.getNroAcreedor() )
                        .append( " Detalle Transacción SAP: " )
                        .append( proveedorRFCResponse.getListasapLog().stream().map( SapLog::getMesaj ).collect(Collectors.toList()) );
            } catch (Exception e) {

                throw new PortalException( "No fue posible crear el proveedor en SAP. Detalle del problema: " + e.getMessage() );

            }

        }

        /**
         * CC. Modulo Aprobaciones - Almacenamos el maximo estado aprobado
         * */
        proveedorRepository.saveStatusSupplierInfo( proveedorActual.getIdProveedor(), estadoProveedor, estadoProveedor.getCodigoEstadoProveedor(), indHomologado, cantidadFlujosCompletados, "X" );

        message.append( " El proveedor fue correctamente aprobado." );

        //--- Notificación
        proveedorDataMaestraNotificacion.enviar( null, oProveedor.get(),"APR", "");

        //--- Notificamos a los siguientes aprobadores siempre y cuando el proveedor fue aprobado en la bandeja actual
        SendNotifyUserDto sendNotifyUser = SendNotifyUserDto.builder()
                .supplier( proveedorActual )
                .statusSupplier( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() )
                .build();

        notificacionFlujoAprobacion.sendNotifyUser(sendNotifyUser);


        response.setMessage( message.toString() );
        response.setMessageType( "Sucess" );
        response.setSave( true );

        return response;

    }

    @Transactional
    public void rechazarDataMaestra(Integer idProveedor, String motivoRechazo) throws PortalException {

        Proveedor proveedorActual = proveedorRepository.findById( idProveedor )
                .orElseThrow( () -> new PortalException("El proveedor no existe.") );

        EstadoProveedor estadoProveedorActual = proveedorActual.getIdEstadoProveedor();

        if ( !estadoProveedorActual.getCodigoEstadoProveedor().equals(EstadoProveedorEnum.REGISTRADO.getCodigo()) )
            throw new PortalException("No es posible Rechazar al proveedor, debido que dicho Proveedor actualmente tiene el estado de " + estadoProveedorActual.getDescripcionEstadoProveedor() );

        EstadoProveedor estadoProveedor = estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.RECHAZADO_DATA_MAESTRA.getCodigo() );

        proveedorMapperMybatis.saveStatusSupplier( proveedorActual.getIdProveedor(), estadoProveedor.getId(), "" );

        proveedorDataMaestraNotificacion.enviar( null, proveedorActual,"REC", motivoRechazo );

    }

    public void evaluarDataMaestra02(Integer idProveedor) throws Exception {
        UserSession userSession = this.systemLoggedUser.getUserSession();
        if (!Optional.ofNullable(userSession).isPresent()) {
            throw new Exception("No se encontro los datos del Usuario Logueado en el IDP");
        }
        this.evaluarDataMaestra(idProveedor);
        Optional<Proveedor> oProveedor = Optional.ofNullable(proveedorRepository)
                .map(r -> r.getOne(idProveedor));

        if (!oProveedor.isPresent()) {
            throw new PortalException("El proveedor no existe");
        }
        //proveedorDataMaestraNotificacion.enviarComprador(oProveedor.get(),"APR", userSession, "");

        AppParametria paramDest =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_3", "1");
        if(paramDest != null) {
            this.gestionProveedorNotificacion.enviar(paramDest.getValue1(), paramDest.getValue2(), oProveedor.get().getRazonSocial()) ;
        }


    }

    public void rechazarDataMaestra02(RechazarValidacionDataMaestraEntradaDto bean) throws Exception  {
        UserSession userSession = this.systemLoggedUser.getUserSession();
        if (!Optional.ofNullable(userSession).isPresent()) {
            throw new Exception("No se encontro los datos del Usuario Logueado en el IDP");
        }
        Integer idProveedor = bean.getIdProveedor();
        this.rechazarDataMaestra(idProveedor, bean.getMotivoRechazo());
        Optional<Proveedor> oProveedor = Optional.ofNullable(proveedorRepository)
                .map(r -> r.getOne(idProveedor));

        if (!oProveedor.isPresent()) {
            throw new PortalException("El proveedor no existe");
        }
        //proveedorDataMaestraNotificacion.enviarComprador(oProveedor.get(),"REC", userSession, bean.getMotivoRechazo());
        PreRegistroProveedor pre = this.preRegistroProveedorRepository.getByRuc(oProveedor.get().getRuc());
        this.registroSolicitudProveedorRechazoNotificacion.enviar(pre, "Faltan Datos");
    }

    @Transactional
    @Override
    public SaveInformationResponse saveTreasuryApproval( SaveApprovalInput input, String usuarioSap ) {

        //--- Validaciones - Configuración IAS
        UserSession usuarioSession = systemLoggedUser.getUserSession();

        //--- Validaciones - Errores de programación
        MessageError.validaCampoObligatorio( input, "SaveApprovalInput" );
        MessageError.validaCampoObligatorio( input.getIdProveedor(), "IdProveedor" );
        MessageError.validaCampoObligatorioNotEmpty( input.getStatus(), "Status" );
        MessageError.validaCampoObligatorioMessage( usuarioSession, "El usuario de sesión no está correctamente configurado en el IDP. Comuníquese con el administrador del sistema." );

        //--- Response
        SaveInformationResponse response = SaveInformationResponse.builder()
                .save( false )
                .build();

        //--- Validaciones - Campos Requeridos
        if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) && StringUtils.isEmpty( input.getReason() )  ){

            response.setMessage( "Para poder rechazar al proveedor es necesario especificar el motivo del rechazo." );
            response.setMessageType( MessageTypeEnum.WARNING.name() );
            return response;

        }

        //--- Obtenemos al proveedor
        Proveedor proveedor = proveedorRepository.findById( input.getIdProveedor() )
                .orElseThrow( () -> new PortalException( "El Proveedor no se encontró en la base de datos. Comuníquese con el administrador del sistema" ) );

        //--- Obtenemos la cantidad de flujos por las pasó el proveedor con exito
        Integer cantidadFlujosCompletados = UtilInteger.coalesce( proveedor.getCantidadFlujosCompletados(), 0 );

        //--- Obtenemos el estado actual del proveedor
        EstadoProveedor estadoActual = proveedor.getIdEstadoProveedor();

        //--- Obtenemos el estado siguiente
        EstadoProveedor siguienteEstado;

        //--- Definimos el mensaje
        StringBuilder message = new StringBuilder();

        if( !estadoActual.getCodigoEstadoProveedor().equals( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() )  ){

            response.setMessage( "No es posible aprobar al proveedor debido a que este no se encuentra en el estado necesario para realizar la transacción. Estado actual del proveedor: " + estadoActual.getDescripcionEstadoProveedor() );
            response.setMessageType( MessageTypeEnum.INFORMATION.name() );
            return response;

        }

        //--- Si se está aprobando validamos que el proveedor no esté rechazado por tesorería
        if( input.getStatus().equals( EstadoAprobacionEnum.APROBADO.name() ) ){

            siguienteEstado = Optional.ofNullable( estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.APROBADO_POR_TESORERIA.getCodigo() ) )
                    .orElseThrow( () -> new PortalException( "No se encontró el estado " + EstadoProveedorEnum.APROBADO_POR_TESORERIA.name() + ". Comuníquese con el administrador del sistema." ) );

            cantidadFlujosCompletados++;

        }else if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) ){

            siguienteEstado = Optional.ofNullable( estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.RECHAZADO_POR_TESORERIA.getCodigo() ) )
                    .orElseThrow( () -> new PortalException( "No se encontró el estado " + EstadoProveedorEnum.RECHAZADO_POR_TESORERIA.name() + ". Comuníquese con el administrador del sistema." ) );

        }else
            throw new PortalException( "Ocurrió un problema. El estado de aprobación enviado en el servicio rest no está contemplado en el flujo de aprobación. Comuníquese con el administrador del sistema." );


        //--- Actualizamos la información en la base de datos
        if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) ) {
            proveedorMapperMybatis.saveStatusSupplier(proveedor.getIdProveedor(), siguienteEstado.getId(), "");
        }else{

            //--- Actualizamos la información en SAP si el proveedor completo más de un flujo
            if( cantidadFlujosCompletados > 1 ){

                try {

                    ProveedorRFCResponseDto responseRFC = jcoActualizarProveedorService.actualizarProveedor( proveedor.getIdProveedor(), usuarioSap );
                    message.append( responseRFC.getListasapLog().stream().map( SapLog::getMesaj ).collect(Collectors.toList()) );

                } catch (Exception e) {
                    throw new PortalException( "Ocurrió un problema al actualizar la información del proveedor en SAP. " + e.getMessage() );
                }

            }


            proveedorRepository.saveStatusSupplierInfo( proveedor.getIdProveedor(), siguienteEstado, siguienteEstado.getCodigoEstadoProveedor(), "1", cantidadFlujosCompletados, "" );


        }

        //--- Armamos la trama para notificar
        SendNotifySupplier sendNotifySupplier = SendNotifySupplier.builder()
                .supplier( proveedor )
                .statusSupplier( siguienteEstado.getCodigoEstadoProveedor() )
                .reason( input.getReason() )
                .build();

        //--- Notificamos al proveedor sobre el estado de su aprobación
        notificacionFlujoAprobacion.sendNotifySupplier( sendNotifySupplier );

        message.append( "El proveedor fue " )
                .append( input.getStatus()  )
                .append( " correctamente."  );

        response.setSave( true );
        response.setMessage( message.toString() );
        response.setMessageType( MessageTypeEnum.SUCCESS.name() );

        return response;

    }

    @Override
    public SaveInformationResponse saveTaxApproval(SaveApprovalInput input) {

        //--- Validaciones - Configuración IAS
        UserSession usuarioSession = systemLoggedUser.getUserSession();

        //--- Message
        StringBuilder message = new StringBuilder();

        //--- Validaciones - Errores de programación
        MessageError.validaCampoObligatorio( input, "SaveApprovalInput" );
        MessageError.validaCampoObligatorio( input.getIdProveedor(), "IdProveedor" );
        MessageError.validaCampoObligatorioNotEmpty( input.getStatus(), "Status" );
        MessageError.validaCampoObligatorioMessage( usuarioSession, "El usuario de sesión no está correctamente configurado en el IDP. Comuníquese con el administrador del sistema." );

        //--- Response
        SaveInformationResponse response = SaveInformationResponse.builder()
                .save( false )
                .build();

        //--- Validaciones - Campos Requeridos
        if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) && StringUtils.isEmpty( input.getReason() )  ){

            response.setMessage( "Para poder rechazar al proveedor es necesario especificar el motivo del rechazo." );
            response.setMessageType( MessageTypeEnum.WARNING.name() );
            return response;

        }

        //--- Obtenemos al proveedor
        Proveedor proveedor = proveedorRepository.findById( input.getIdProveedor() )
                .orElseThrow( () -> new PortalException( "El Proveedor no se encontró en la base de datos. Comuníquese con el administrador del sistema" ) );

        String indHomologado = UtilString.coalesceTrim( proveedor.getIndHomologado() );

        //--- Obtenemos la cantidad de flujos por las pasó el proveedor con exito
        Integer cantidadFlujosCompletados = UtilInteger.coalesce( proveedor.getCantidadFlujosCompletados(), 0 );

        //--- Obtenemos el estado actual del proveedor
        EstadoProveedor estadoActual = proveedor.getIdEstadoProveedor();

        //--- Obtenemos el estado siguiente
        EstadoProveedor siguienteEstado;

        //--- Validamos que el proveedor se encuentre en el estado necesario
        if( !estadoActual.getCodigoEstadoProveedor().equals( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() )  ){

            response.setMessage( "No es posible aprobar al proveedor debido a que este no se encuentra en el estado necesario para realizar la transacción. Estado actual del proveedor: " + estadoActual.getDescripcionEstadoProveedor() );
            response.setMessageType( MessageTypeEnum.INFORMATION.name() );
            return response;

        }

        //--- Si se está aprobando validamos que el proveedor no esté rechazado por tesorería
        if( input.getStatus().equals( EstadoAprobacionEnum.APROBADO.name() ) ){

            siguienteEstado = Optional.ofNullable( estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() ) )
                    .orElseThrow( () -> new PortalException( "No se encontró el estado " + EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.name() + ". Comuníquese con el administrador del sistema." ) );

        }else if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) ){

            siguienteEstado = Optional.ofNullable( estadoProveedorRepository.getByCodigoEstadoProveedor( EstadoProveedorEnum.RECHAZADO_POR_IMPUESTOS.getCodigo() ) )
                    .orElseThrow( () -> new PortalException( "No se encontró el estado " + EstadoProveedorEnum.RECHAZADO_POR_IMPUESTOS.name() + ". Comuníquese con el administrador del sistema." ) );

        }else
            throw new PortalException( "Ocurrió un problema. El estado de aprobación enviado en el servicio rest no está contemplado en el flujo de aprobación. Comuníquese con el administrador del sistema." );

        //--- Actualizamos la información en la base de datos
        if( input.getStatus().equals( EstadoAprobacionEnum.RECHAZADO.name() ) )
            proveedorMapperMybatis.saveStatusSupplier(proveedor.getIdProveedor(), siguienteEstado.getId(), "");
        else
            proveedorRepository.saveStatusSupplierInfo( proveedor.getIdProveedor(), siguienteEstado, siguienteEstado.getCodigoEstadoProveedor(), indHomologado, cantidadFlujosCompletados, "X" );


        //--- Armamos la trama para notificar
        SendNotifySupplier sendNotifySupplier = SendNotifySupplier.builder()
                .supplier( proveedor )
                .statusSupplier( siguienteEstado.getCodigoEstadoProveedor() )
                .reason( input.getReason() )
                .build();

        //--- Notificamos al proveedor sobre el estado de su aprobación
        notificacionFlujoAprobacion.sendNotifySupplier( sendNotifySupplier );

        //--- Notificamos a los siguientes aprobadores siempre y cuando el proveedor fue aprobado en la bandeja actual
        if( input.getStatus().equals( EstadoAprobacionEnum.APROBADO.name() ) ){

            SendNotifyUserDto sendNotifyUser = SendNotifyUserDto.builder()
                    .supplier( proveedor )
                    .statusSupplier( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() )
                    .build();

            notificacionFlujoAprobacion.sendNotifyUser(sendNotifyUser);

        }

        message.append("El proveedor fue ")
                .append( input.getStatus() )
                .append(" correctamente.");

        response.setSave( true );
        response.setMessage( message.toString() );
        response.setMessageType( MessageTypeEnum.SUCCESS.name() );

        return response;

    }

    @Override
    public Integer updateProveedorIDHCP(ListProveedorHCP listProveedorHCP) {
        List<ProveedorCustom> listaProveedoresSinHCP=listProveedorHCP.getListaProveedorsinIDHCP();
        Integer nroProveedor=listaProveedoresSinHCP.size();
        if(listaProveedoresSinHCP.size()>0){
            for (ProveedorCustom obj : listaProveedoresSinHCP){
               this.proveedorRepository.updateIdHCP(obj.getIdHCP(),obj.getRuc());
            }
        }
        return nroProveedor;
    }
    protected boolean depurarUploadExcel(ProveedorDto dto) {
        if (StringUtils.isBlank(dto.getAcreedorCodigoSap())) {
            return false;
        }
        if(StringUtils.isBlank(dto.getEmail())){
            return false;
        }
        return true;
    }

    protected String validacionesPrevias(ProveedorDto dto) {
        String mensaje = "";

        if (!Optional.ofNullable(dto.getAcreedorCodigoSap()).isPresent()) {
            String msj = this.messageSource.getMessage("message.AcreedorSap.noIngresado", null,
                    LocaleContextHolder.getLocale());
            mensaje += "* " + msj + " ";
        }
        if (!Optional.ofNullable(dto.getEmail()).isPresent()) {
            String msj = this.messageSource.getMessage("message.Email.noIngresado", null,
                    LocaleContextHolder.getLocale());
            mensaje += "* " + msj + " ";
        }

        return mensaje;
    }

    protected ProveedorDto setUploadExcel(Cell currentCell, ProveedorDto proveedorDtoParam, int contador) {
        Double valorNumerico = new Double(0);
        BigDecimal valorDecimal = new BigDecimal(0);
        String valorCadena = "";
        switch (contador) {
            case 1:
                try {
                    valorCadena = currentCell.getStringCellValue();
                    if (valorCadena.length() > 10) {
                        throw new ServiceException("Valor Campo Acreedor contiene mas de 10 caracter(es)");
                    }
                    proveedorDtoParam.setAcreedorCodigoSap(valorCadena);
                } catch (Exception e) {
                    throw new ServiceException("Valor Campo Acreedor está en formato incorrecto");
                }
                break;
            case 2:
                try {
                    valorCadena = currentCell.getStringCellValue();

                    if (!Utils.validarEmail(valorCadena)){
                        throw new ServiceException("Valor Campo Email tiene formato incorrecto");
                    }
                    proveedorDtoParam.setEmail(valorCadena);
                } catch (Exception e) {
                    throw new ServiceException("Valor Campo Email está en formato incorrecto");
                }
                break;
            default:
                break;
        }
        return proveedorDtoParam;
    }
    public List<ProveedorXLSXDTO> uploadExcelData(InputStream in) {
        logger.debug("Ingresando uploadExcel");
        List<ProveedorXLSXDTO> mapaProveedorParamMasivoDTOArrayList = new ArrayList<ProveedorXLSXDTO>();
        int inicioRegistroData = 2;
        /*AppParametria appParametriaData = this.appParametriaDeltaRepository.getByModuloAndLabelAndStatus(AppParametriaModuloEnum.CARGA_EXCEL.getEstado(),
                AppParametriaLabelEnum.INICIO_REGISTRO_DATA.getEstado(), Constant.UNO);
        if (Optional.of(appParametriaData).isPresent()) {
            inicioRegistroData = new Integer(appParametriaData.getValue1()).intValue();
        }*/
        try {
            Workbook workbook = new XSSFWorkbook(in);
            try {
                Sheet datatypeSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = datatypeSheet.iterator();
                int contadorRegistro = 1;
                while (iterator.hasNext()) {
                    if (contadorRegistro < inicioRegistroData) {
                        contadorRegistro++;
                        Row currentRow = iterator.next();
                        continue;
                    }
                    ProveedorXLSXDTO proveedorXLSXDTO = new ProveedorXLSXDTO();
                    ProveedorDto proveedorParam = new ProveedorDto();
                    proveedorParam.setIdProveedor(null);
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    boolean error = false;
                    try {
                        while (cellIterator.hasNext()) {
                            Cell currentCell = cellIterator.next();
                            proveedorParam = this.setUploadExcel(currentCell, proveedorParam, currentCell.getColumnIndex() + 1);
                        }
                        String mensaje = this.validacionesPrevias(proveedorParam);
                        if (StringUtils.isNotBlank(mensaje)) {
                            throw new ServiceException(mensaje);
                        }
                        if (this.depurarUploadExcel(proveedorParam)) {
                            proveedorXLSXDTO.setProveedor(proveedorParam);
                            proveedorXLSXDTO.setMensaje("");
                            proveedorXLSXDTO.setError(false);
                        }
                    } catch (Exception e) {
                        proveedorXLSXDTO.setProveedor(proveedorParam);
                        proveedorXLSXDTO.setMensaje(Utils.obtieneMensajeErrorException(e));
                        proveedorXLSXDTO.setError(true);
                    }
                    mapaProveedorParamMasivoDTOArrayList.add(proveedorXLSXDTO);
                }
            } catch (Exception exw) {

            } finally {
                workbook.close();
            }
        } catch (Exception ex) {

        }
        return mapaProveedorParamMasivoDTOArrayList;
    }
    @Override
    public List<ProveedorXLSXDTO> uploadExcel(InputStream in) {
        logger.error("Ingresando uploadExcelDelta ");
        List<ProveedorXLSXDTO> listaUpload = this.uploadExcelData(in);
        logger.error("Ingresando after uploadExcel: ");
        if (listaUpload == null || listaUpload.size() <= 0) {
            return null;
        }


        logger.error("Fin uploadExcelDelta ");
        return listaUpload;
    }

    public List<ProveedorAdjuntoSunat> guardarAdjuntoSunat(Proveedor proveedor, List<ProveedorAdjuntoSunatDto> listAdjunto, String operacion){

        if (isDev) {
            return null;
        }
        logger.error("iniciando GRABAR AdjuntoSunat");
        //Creo una segunda lista con los adjuntos no guardados
        List<CmisFile> listAdjuntoNew = new ArrayList<CmisFile>();
        if (listAdjunto.size() > 0) {
            listAdjunto.forEach(item -> {
                if (item.getId() == null) {

                    CmisFile cmisFile = new CmisFile();
                    cmisFile.setId( item.getArchivoId() );
                    cmisFile.setName( item.getArchivoNombre() );
                    cmisFile.setUrl( item.getRutaAdjunto() );
                    cmisFile.setType( item.getArchivoTipo() );
                    cmisFile.setCodigoTipoDocumento( item.getCodigoTipoDocumento() );

                    listAdjuntoNew.add( cmisFile );
                    logger.error("Creo una segunda lista con los adjuntos no guardados");



                }
            });
        }

        //Se crea folder destino -> Nro de licitacion
        String newFolder = proveedor.getRuc();

        String folderId = cmisService.createFolder(newFolder);
        logger.debug("FOLDER_DESTINO: " + folderId);

        //Se mueven los adjuntos al folder destino y se obtiene la lista de los mismos con su nuevo URL
        Optional<List<CmisFile>> listAdjuntoMove = Optional.ofNullable(listAdjuntoNew)
                .map(list -> {
                    logger.debug("Actualizando la version de los archivos adjuntos");
                    return cmisService.updateFileAndMoveVerificar(listAdjuntoNew, folderId);
                });

        //Se guardan en la base de datos los adjuntos movidos
        if(listAdjuntoMove.isPresent()){
            List<CmisFile> list = listAdjuntoMove.get();
            list.forEach(file->{


                ProveedorAdjuntoSunat adjuntoProveedor = new ProveedorAdjuntoSunat();
                adjuntoProveedor.setIdProveedor( proveedor );
                adjuntoProveedor.setRutaAdjunto( file.getUrl() );
                adjuntoProveedor.setArchivoId( file.getId() );
                adjuntoProveedor.setArchivoNombre( file.getName() );
                adjuntoProveedor.setArchivoTipo( file.getType() );
                adjuntoProveedor.setCodigoTipoDocumento( file.getCodigoTipoDocumento() );


                ProveedorAdjuntoSunat obj = this.proveedorAdjuntoSunatRepository.save( adjuntoProveedor );
            });
            logger.debug("Se guardan en la base de datos los adjuntos movidos");
        }

        //Eliminando adjuntos ausentes en el request
        List<ProveedorAdjuntoSunat> listaAdjuntosActual = this.proveedorAdjuntoSunatRepository.getProveedorAdjuntoSunatByIdProveedor(proveedor.getIdProveedor());
        for (ProveedorAdjuntoSunat obj1 : listaAdjuntosActual){
            Boolean encontrado = false;
            for (ProveedorAdjuntoSunatDto obj2 : listAdjunto){
                if ((obj1.getArchivoId()).equals(obj2.getArchivoId())){
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado){
                this.proveedorAdjuntoSunatRepository.deleteProveedorAdjuntoSunatByLicitacionandAndArchivoId(proveedor.getIdProveedor(), obj1.getArchivoId());
                logger.debug("Eliminando adjuntos ausentes en el request");
                continue;
            }
        }

        //Se obtiene lista de adjuntos final
        List<ProveedorAdjuntoSunat> listaAdjuntosSunatResult = this.proveedorAdjuntoSunatRepository.getProveedorAdjuntoSunatByIdProveedor(proveedor.getIdProveedor());

        return listaAdjuntosSunatResult;

    }

    public List<ProveedorCatalogo> guardarAdjuntoCatalogo(Proveedor proveedor, List<ProveedorCatalogoDto> catalogosList, String operacion){

        if (isDev) {
            return null;
        }
        logger.error("iniciando GRABAR catalogos");
        //Creo una segunda lista con los adjuntos no guardados
        List<CmisFile> listAdjuntoNew = new ArrayList<CmisFile>();
        if (catalogosList.size() > 0) {
            catalogosList.forEach(item -> {
                if (item.getId() == null) {
                    listAdjuntoNew.add(new CmisFile(item.getArchivoId(), item.getArchivoNombre(), item.getRutaCatalogo(), item.getArchivoTipo()));

                    logger.error("Creo una segunda lista con los adjuntos no catalogos");
                }
            });
        }


        String newFolder = proveedor.getRuc() + "-catalogos";

        String folderId = cmisService.createFolder(newFolder);
        logger.error("FOLDER_DESTINO: " + folderId);

        //Se mueven los adjuntos al folder destino y se obtiene la lista de los mismos con su nuevo URL
        Optional<List<CmisFile>> listAdjuntoMove = Optional.ofNullable(listAdjuntoNew)
                .map(list -> {
                    logger.error("Actualizando la version de los archivos catalogos");
                    return cmisService.updateFileAndMoveVerificar(listAdjuntoNew, folderId);
                });

        //Se guardan en la base de datos los adjuntos movidos
        if(listAdjuntoMove.isPresent()){
            List<CmisFile> list = listAdjuntoMove.get();
            list.forEach(file->{
                ProveedorCatalogo obj = this.proveedorCatalogoRepository.save(new ProveedorCatalogo(proveedor, file.getUrl(), file.getId(), file.getName(), file.getType()));
            });
            logger.error("Se guardan en la base de datos los catalogos movidos");
        }

        //Eliminando adjuntos ausentes en el request
        logger.error(proveedor.getIdProveedor().toString());
        List<ProveedorCatalogo> listaAdjuntosActual = this.proveedorCatalogoRepository.getProveedorCatalogoByIdProveedor(proveedor.getIdProveedor());
        for (ProveedorCatalogo obj1 : listaAdjuntosActual){
            Boolean encontrado = false;
            for (ProveedorCatalogoDto obj2 : catalogosList){
                if ((obj1.getArchivoId()).equals(obj2.getArchivoId())){
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado){
                this.proveedorCatalogoRepository.deleteCatalogoByIdProveedorCatalogoById(proveedor.getIdProveedor(), obj1.getArchivoId());
                logger.debug("Eliminando adjuntos ausentes en el request");
                continue;
            }
        }

        //Se obtiene lista de adjuntos final
        List<ProveedorCatalogo> listaCatalogos = this.proveedorCatalogoRepository.getProveedorCatalogoByIdProveedor(proveedor.getIdProveedor());

        return listaCatalogos;

    }

    public ProveedorValidacionInfoSalidaDto aprobarRechazarActualizacion(ProveedorValidacionInfoEntradaDto bean) throws Exception {
        ProveedorValidacionInfoSalidaDto proveedorValidacionInfoSalidaDto = new ProveedorValidacionInfoSalidaDto();
        Integer idProveedor = bean.getIdProveedor();
        if (!Optional.ofNullable(idProveedor).isPresent()) {
            throw new Exception("Debe ingresar ID del Proveedor");
        }
        Proveedor proveedor = this.proveedorRepository.getOne(idProveedor);
        if (!Optional.ofNullable(proveedor).isPresent()) {
            throw new Exception("No se encontró Proveedor con ID de Proveedor: "+ idProveedor);
        }
        if (!Optional.ofNullable(proveedor.getAcreedorCodigoSap()).isPresent()) {
            throw new Exception("Esta opción es solo para Proveedores con Código de Acreedor");
        }
        UserSession userSession = this.systemLoggedUser.getUserSession();
        Date fechaActual = DateUtils.obtenerFechaHoraActual();
        Proveedor proveedorBuscar = new Proveedor();
        proveedorBuscar.setIdProveedor(idProveedor);

        List<ProveedorPorValidarInfo> proveedorPorValidarInfoList = this.proveedorPorValidarInfoRepository.findByIdProveedor(proveedorBuscar);
        if (proveedorPorValidarInfoList == null || proveedorPorValidarInfoList.size() <= 0) {
            throw new Exception("No se encontró Datos que hayan sido modificados con ID de Proveedor: "+ idProveedor);
        }
        proveedorValidacionInfoSalidaDto.setProveedor(proveedor);
        proveedorValidacionInfoSalidaDto.setMotivoRechazo(null);
        ProveedorValidacionInfo proveedorValidacionInfo = new ProveedorValidacionInfo();
        proveedorValidacionInfo.setIdProveedor(proveedorBuscar);
        proveedorValidacionInfo.setFechaAprobacionRechazo(fechaActual);
        proveedorValidacionInfo.setUsuarioAprobacionRechazo(userSession.getId());
        if (Optional.ofNullable(bean.getMotivoRechazo()).isPresent()) {
            proveedor.setIndPorValidarInfoAcreedor(Constant.RECHAZADO);
            proveedor = this.proveedorRepository.save(proveedor);

            proveedorValidacionInfo.setIndAprobacionRechazo(Constant.RECHAZADO);
            proveedorValidacionInfo.setMotivoRechazo(bean.getMotivoRechazo());
            proveedorValidacionInfo = this.proveedorValidacionInfoRepository.save(proveedorValidacionInfo);

            proveedorValidacionInfoSalidaDto.setProveedorValidacionInfo(proveedorValidacionInfo);
            proveedorValidacionInfoSalidaDto.setIndicadorRechazado(true);
            proveedorValidacionInfoSalidaDto.setMotivoRechazo(bean.getMotivoRechazo());
            return proveedorValidacionInfoSalidaDto;
        }

        //Invocando a ERP SAP
        ProveedorRFCResponseDto proveedorRFCResponseDto = this.jcoActualizarProveedorService.actualizarProveedor(idProveedor, userSession.getId());
        List<SapLog> listasapLog = proveedorRFCResponseDto.getListasapLog();
        if (listasapLog != null && listasapLog.size() > 0) {
            String mensaje = "";
            boolean error = false;
            for(SapLog sapLog : listasapLog) {
                String tipo = sapLog.getTipo();
                if (tipo.equals(Constant.ERROR) || tipo.equals(Constant.ABORT)) {
                    mensaje = mensaje + "/" + sapLog.getMesaj();
                    error = true;
                }
            }
            if (error) {
                throw new Exception(mensaje);
            }
        }
        proveedorValidacionInfoSalidaDto.setListSapLog(listasapLog);

        //Grabando en SCP
        proveedorValidacionInfo.setIndAprobacionRechazo(Constant.APROBADO);
        proveedorValidacionInfo = this.proveedorValidacionInfoRepository.save(proveedorValidacionInfo);
        proveedorValidacionInfoSalidaDto.setProveedorValidacionInfo(proveedorValidacionInfo);
        proveedorValidacionInfoSalidaDto.setIndicadorRechazado(false);

        ProveedorValidacionInfo proveedorValidacionInfoBuscar = new ProveedorValidacionInfo();
        proveedorValidacionInfoBuscar.setId(proveedorValidacionInfo.getId());

        List<ProveedorValidacionInfoDetalle> proveedorValidacionInfoDetalleList = new ArrayList<>();
        for (ProveedorPorValidarInfo beanInfo : proveedorPorValidarInfoList) {
            ProveedorValidacionInfoDetalle proveedorValidacionInfoDetalle = new ProveedorValidacionInfoDetalle();
            proveedorValidacionInfoDetalle.setIdProveedorPorValidarInfo(proveedorValidacionInfoBuscar);
            proveedorValidacionInfoDetalle.setNombreCampoBd(beanInfo.getNombreCampoBd());
            proveedorValidacionInfoDetalle.setValorActual(beanInfo.getValorActual());
            proveedorValidacionInfoDetalle.setValorListaActual(beanInfo.getValorListaActual());
            proveedorValidacionInfoDetalle.setValorNuevo(beanInfo.getValorNuevo());
            proveedorValidacionInfoDetalle.setValorListaNuevo(beanInfo.getValorListaNuevo());
            proveedorValidacionInfoDetalle.setIndEsLista(beanInfo.getIndEsLista());
            proveedorValidacionInfoDetalle = this.proveedorValidacionInfoDetalleRepository.save(proveedorValidacionInfoDetalle);
            proveedorValidacionInfoDetalleList.add(proveedorValidacionInfoDetalle);
        }
        this.proveedorPorValidarInfoRepository.deleteByIdProveedor(idProveedor);
        proveedorValidacionInfoSalidaDto.setProveedorValidacionInfoDetalleList(proveedorValidacionInfoDetalleList);

        return proveedorValidacionInfoSalidaDto;
    }
}
