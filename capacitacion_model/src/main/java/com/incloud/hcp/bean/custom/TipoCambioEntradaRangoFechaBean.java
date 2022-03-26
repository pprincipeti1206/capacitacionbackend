package com.incloud.hcp.bean.custom;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class TipoCambioEntradaRangoFechaBean {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaIni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaFin;

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "TipoCambioEntradaRangoFechaBean{" +
                "fechaIni=" + fechaIni +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
