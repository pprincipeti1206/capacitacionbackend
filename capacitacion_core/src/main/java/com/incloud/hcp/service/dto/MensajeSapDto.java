package com.incloud.hcp.service.dto;

/**
 * Created by Administrador on 23/08/2017.
 */
public class MensajeSapDto {
    private String type;
    private String mensaje;



    public MensajeSapDto(){

    }

    public MensajeSapDto(String type, String mensaje) {
        this.type = type;
        this.mensaje = mensaje;


    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }




}
