package com.incloud.hcp.enums;

public enum EstadoCertificadoEnum {

    ELIMINADO("CEEL"),
    RECHAZADO("CERE"),
    APROBADO("CEAP"),
    BORRADOR("CEBO"),
    PUBLICADO("CECO"),
    PENDIENTE_FIRMA_A("CEFA"),
    PENDIENTE_FIRMA_B("CEFB"),
    FACTURADO("CEFF")
    ;

    private final String estado;

    EstadoCertificadoEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
