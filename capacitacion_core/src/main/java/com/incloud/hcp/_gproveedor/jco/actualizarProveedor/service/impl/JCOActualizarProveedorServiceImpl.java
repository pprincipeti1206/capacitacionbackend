package com.incloud.hcp._gproveedor.jco.actualizarProveedor.service.impl;

import com.incloud.hcp._gproveedor.jco.actualizarProveedor.dto.ActualizarProveedorRFCParameterBuilder;
import com.incloud.hcp._gproveedor.jco.actualizarProveedor.service.JCOActualizarProveedorService;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.ProveedorCuentaBancoRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.repository.delta.MtrProveedorDeltaRepository;
import com.sap.conn.jco.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JCOActualizarProveedorServiceImpl implements JCOActualizarProveedorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int NRO_EJECUCIONES_RFC = 10;
    private final String FUNCION_RFC = "ZPE_MM_ACTUALIZA_PROVEEDOR";
    //--- private final String NOMBRE_TABLA_RFC = "RETURN";
    private final String NOMBRE_TABLA_RFC = "E_MENSAJES";

    @Value("${destination.rfc.profit}")
    private String destinationProfit;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorCuentaBancoRepository proveedorCuentaBancoRepository;

    @Autowired
    private MtrProveedorDeltaRepository mtrProveedorDeltaRepository;

    public ProveedorRFCResponseDto actualizarProveedor(Integer idProveedor, String usuarioSap) throws Exception {
        ProveedorRFCResponseDto proveedorRFCResponseDto = new ProveedorRFCResponseDto();

        /* Ejecucion invocacion a RFC */
        JCoDestination destination = JCoDestinationManager.getDestination(destinationProfit);
        JCoRepository repo = destination.getRepository();
        logger.error("01A - ACTUALIZAR PROVEEDOR");
        JCoFunction jCoFunction = repo.getFunction(FUNCION_RFC);
        logger.error("01B - ACTUALIZAR PROVEEDOR");

        ///Obtener Bean Proveedor
        Proveedor proveedorParam = this.proveedorRepository.getProveedorByIdProveedor(idProveedor);
        List<ProveedorCuentaBancaria> listaCuentaBancaria= this.
                proveedorCuentaBancoRepository.getListCuentaBancariaByIdProveedor(idProveedor);

        logger.error("parametro ingresado Proveedor" + proveedorParam.toString());
        logger.error("parametro ingresado Cuenta Bancaria" + listaCuentaBancaria.toString());
        ActualizarProveedorRFCParameterBuilder.build(
                jCoFunction,
                proveedorParam,
                listaCuentaBancaria,
                usuarioSap
        );
        logger.error("01C - ACTUALIZAR PROVEEDOR");

        //--- Estructura
        logger.error( "JCO - UPDATE SUPPLIER SAP IMPORT : " + jCoFunction.getImportParameterList().toString() );

        for(int contador=0; contador < NRO_EJECUCIONES_RFC; contador++) {
            try {
                jCoFunction.execute(destination);
                break;
            } catch (Exception e) {
                if (contador == NRO_EJECUCIONES_RFC - 1 ) {
                    logger.error("01Ca - ACTUALIZAR - INI RFC ERROR: "+ e.toString());
                    throw new Exception(e);
                }
            }
        }

        logger.error( "INPUT - UPDATE SUPPLIER SAP IMPORT XML : " + jCoFunction.getImportParameterList().toXML() );

        /* Recorriendo valores obtenidos del RFC */
        JCoParameterList result = jCoFunction.getExportParameterList();

        //--- Retorno
        logger.error( "JCO - UPDATE SUPPLIER SAP EXPORT : " + result.toJSON() );

        JCoTable table = result.getTable(NOMBRE_TABLA_RFC);

        logger.error( " JCO - UPDATE SUPPLIER SAP - TABLE NAME: " + table.toString() );

        List<SapLog> listSapLog = new ArrayList<>();
        if (table != null && !table.isEmpty()) {
            do {
                SapLog sapLog = new SapLog();
                Integer numero = table.getInt("NUMERO");
                sapLog.setCode(numero.toString());
                sapLog.setMesaj(table.getString("TEXTO"));
                sapLog.setClase(table.getString("CLASE"));
                sapLog.setTipo(table.getString("TIPO"));
                listSapLog.add(sapLog);

                /**
                 * Validamos de que no haya mensajes de error
                 * */
                if( sapLog.getTipo().equals( "E" ) )
                    throw new PortalException( sapLog.getMesaj() );

            } while (table.nextRow());
        }
        proveedorRFCResponseDto.setProveedorSap(proveedorParam);
        proveedorRFCResponseDto.setListasapLog(listSapLog);
        return proveedorRFCResponseDto;
    }
}
