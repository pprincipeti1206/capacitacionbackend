package com.incloud.hcp.enums;

public enum EstadoNotaPedidoEnum {

    CERRADA("NPPE"),
    ACUSE_RECIBO("NPVI"),
    SIN_ACUSE_RECIBO("NPCE"),
    NO_VIGENTE("NPNV"),
    RECHAZADA("NPRE");

    private final String estado;

    EstadoNotaPedidoEnum(String estado)  {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
