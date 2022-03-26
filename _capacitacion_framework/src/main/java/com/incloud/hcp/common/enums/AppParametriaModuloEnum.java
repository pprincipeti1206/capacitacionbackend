package com.incloud.hcp.common.enums;

public enum AppParametriaModuloEnum {

    EMAIL("EMAIL"),
    PARAMETRIA("PARAMETRIA"),
    SESSION_VERSION("SESSION_VERSION"),
    PROCESO("PROCESO"),
    PLAZO_FACTURA_VENCIMIENTO("PLAZO_FACTURA_VENCIMIENTO"),
    ESTADO_FACTURA_CUPA("FACUPA"),
    FACTURA_CUPA_TYPE("FACUPATYPE"),
    FACTURA_IGV("FACTURA_IGV"),
    ESTADO_FACTURA_REGISTRADO("FAREGISTRADO"),
    ESTADO_FACTURA_PUBLICADO("FAPUBLICADO"),
    CARGA_EXCEL("CARGA_EXCEL");

    private final String estado;

    AppParametriaModuloEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
