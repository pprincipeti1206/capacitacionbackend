package com.incloud.hcp.service._gproveedor.dto;

public class RechazarValidacionDataMaestraEntradaDto {

    private Integer idProveedor;
    private String motivoRechazo;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    @Override
    public String toString() {
        return "RechazarValidacionDataMaestraEntradaDto{" +
                "idProveedor=" + idProveedor +
                ", motivoRechazo='" + motivoRechazo + '\'' +
                '}';
    }
}
