package com.incloud.hcp.enums;

public enum TipoFacturaAdjuntoEnum {

    FAC_FACTU("FAC_FACTU"),
    FAC_GUIA("FAC_GUIA"),
    FAC_CDR("FAC_CDR"),
    FAC_OTROS("FAC_OTROS") ;

    private final String estado;

    TipoFacturaAdjuntoEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
