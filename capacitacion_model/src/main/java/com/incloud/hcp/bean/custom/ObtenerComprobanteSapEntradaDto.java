package com.incloud.hcp.bean.custom;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ObtenerComprobanteSapEntradaDto {

    private String fechaIni;
    private String fechaFin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "ObtenerComprobanteSapEntradaDto{" +
                "fechaIni='" + fechaIni + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }
}
