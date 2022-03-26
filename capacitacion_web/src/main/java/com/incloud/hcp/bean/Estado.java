package com.incloud.hcp.bean;

/**
 * Created by Hugo Oliveros on 13/01/2019.
 */
public class Estado {

    private int id;
    private int idEstado;
    private String descripcion;

    public Estado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", idEstado='" + idEstado + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
