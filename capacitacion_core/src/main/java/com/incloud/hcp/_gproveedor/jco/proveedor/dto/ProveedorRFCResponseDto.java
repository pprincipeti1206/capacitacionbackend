package com.incloud.hcp._gproveedor.jco.proveedor.dto;


import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;

import java.io.Serializable;
import java.util.List;

public class ProveedorRFCResponseDto implements Serializable {

    private List<SapLog> listasapLog;

    private String nroAcreedor;

    private Proveedor proveedorSap;

    public List<SapLog> getListasapLog() {
        return listasapLog;
    }

    public void setListasapLog(List<SapLog> listasapLog) {
        this.listasapLog = listasapLog;
    }

    public String getNroAcreedor() {
        return nroAcreedor;
    }

    public void setNroAcreedor(String nroAcreedor) {
        this.nroAcreedor = nroAcreedor;
    }

    public Proveedor getProveedorSap() {
        return proveedorSap;
    }

    public void setProveedorSap(Proveedor proveedorSap) {
        this.proveedorSap = proveedorSap;
    }

    @Override
    public String toString() {
        return "ProveedorRFCResponseDto{" +
                "listasapLog=" + listasapLog +
                ", nroAcreedor='" + nroAcreedor + '\'' +
                ", proveedorSap=" + proveedorSap +
                '}';
    }
}
