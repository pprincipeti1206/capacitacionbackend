package com.incloud.hcp.enums;

public enum EstadoFacturaEnum {

    RECHAZADO_CUPA("FACH"),
    ACTUALIZACION_CUPA("FAAC"),
    INGRESADA("FAIN"),
    EN_REVISION_CUPA("FARE"),
    PRE_APROBACION("FAPA"),
    PENDIENTE_FIRMA("FAPF"),
    PENDIENTE_PAGO("FAAP") ;

    private final String estado;

    EstadoFacturaEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}
