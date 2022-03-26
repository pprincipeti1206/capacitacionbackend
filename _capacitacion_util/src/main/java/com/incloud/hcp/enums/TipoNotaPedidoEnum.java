package com.incloud.hcp.enums;

public enum TipoNotaPedidoEnum {

    MATERIAL("M"),
    SERVICIO("S");

    private final String estado;

    TipoNotaPedidoEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
