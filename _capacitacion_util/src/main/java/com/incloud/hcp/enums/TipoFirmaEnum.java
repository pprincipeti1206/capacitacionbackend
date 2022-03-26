package com.incloud.hcp.enums;

public enum TipoFirmaEnum {

    FIRMA_A("FA"),
    FIRMA_B("FB");

    private final String estado;
    TipoFirmaEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
