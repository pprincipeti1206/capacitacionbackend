package com.incloud.hcp.service._gproveedor.dto;



import com.incloud.hcp.domain._gproveedor.bean.ProveedorCustom;

import java.util.List;

public class ListProveedorHCP {

    List<ProveedorCustom> listaProveedorsinIDHCP;

    public List<ProveedorCustom> getListaProveedorsinIDHCP() {
        return listaProveedorsinIDHCP;
    }

    public void setListaProveedorsinIDHCP(List<ProveedorCustom> listaProveedorsinIDHCP) {
        this.listaProveedorsinIDHCP = listaProveedorsinIDHCP;
    }
}
