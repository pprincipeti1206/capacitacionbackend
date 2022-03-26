package com.incloud.hcp.enums._gproveedor;

public enum EstadoProveedorEnum {

    REGISTRADO("REG"),
    APROBADO_DATA_MAESTRA("ADM"),
    RECHAZADO_DATA_MAESTRA("RDM"),
    APROBADO_POR_TESORERIA("APT"),
    RECHAZADO_POR_TESORERIA("RPT"),
    APROBADO_POR_IMPUESTOS("API"),
    RECHAZADO_POR_IMPUESTOS("RPI"),
    HOMOLOGADO("HOM"),
    MIGRADO_DE_SAP("MIG");

    String codigo;
    EstadoProveedorEnum(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return this.codigo;
    }
}
