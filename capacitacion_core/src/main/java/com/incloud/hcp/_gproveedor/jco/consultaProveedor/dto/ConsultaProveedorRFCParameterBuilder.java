package com.incloud.hcp._gproveedor.jco.consultaProveedor.dto;


import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultaProveedorRFCParameterBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaProveedorRFCParameterBuilder.class);


    private ConsultaProveedorRFCParameterBuilder() {
    }

    public static void build(JCoFunction jCoFunction, String nroAcreedor,String fechaInicio,String fechaFin) {
        logger.info("Mapeando tabla");
        JCoParameterList input = jCoFunction.getImportParameterList();
        logger.error("INPUT build ConsultaProveedor: " + nroAcreedor);

        input.setValue("PI_LIFNR", nroAcreedor);
        logger.error("INPUT build ConsultaProveedor input " + input.toString());


        JCoStructure jcoEstructura = jCoFunction.getImportParameterList().getStructure("TI_ERDAT");
        jcoEstructura.setValue("LOW", fechaInicio);
        jcoEstructura.setValue("HIGH", fechaFin);


    }

}
