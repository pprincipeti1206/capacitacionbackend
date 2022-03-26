package com.incloud.hcp.domain._gproveedor.bean;

/**
 * Created by Administrador on 23/08/2017.
 */
public class Area {
    private String codigoArea;
    private String descripcion;

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Area{" +
                "codigoArea='" + codigoArea + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
