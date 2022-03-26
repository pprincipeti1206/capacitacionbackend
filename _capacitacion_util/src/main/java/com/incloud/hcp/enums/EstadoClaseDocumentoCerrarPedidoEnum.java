package com.incloud.hcp.enums;

public enum EstadoClaseDocumentoCerrarPedidoEnum {

    BCOG("BCOG"),
    BTGP("BTGP"),
    CCOG("CCOG"),
    CTGP("CTGP"),
    VCOG("VCOG"),
    VTGP("VTGP")
    ;

    private final String estado;

    EstadoClaseDocumentoCerrarPedidoEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
