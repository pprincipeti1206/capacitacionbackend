package com.incloud.hcp.enums;

public enum NotaPedidoTipoPosicionEnum {

    POSICION("P"),
    SUBPOSICION("S");

    private final String estado;
    NotaPedidoTipoPosicionEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
