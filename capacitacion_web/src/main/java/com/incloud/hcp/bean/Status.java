package com.incloud.hcp.bean;

public class Status {

    private int idEstado;

    public Status() {
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Status{" +
                ", idEstado='" + idEstado + '\'' +
                '}';
    }
}
