package com.incloud.hcp.common.enums;

public enum AppParametriaLabelEnum {

    MAIL_SENDER_NAME("MAIL_SENDER_NAME"),
    MAIL_USERNAME("MAIL_USERNAME"),
    MAIL_HOST("MAIL_HOST"),
    MAIL_PORT("MAIL_PORT"),
    MAIL_PASSWORD("MAIL_PASSWORD"),
    INICIO_REGISTRO_DATA("INICIO_REGISTRO_DATA"),
    PROCESO_BATCH("PROCESO_BATCH")
    ;

    private final String estado;

    AppParametriaLabelEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
