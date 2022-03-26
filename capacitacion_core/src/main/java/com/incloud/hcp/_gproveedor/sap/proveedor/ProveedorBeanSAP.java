package com.incloud.hcp._gproveedor.sap.proveedor;


import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;

import java.io.Serializable;

/**
 * Created by USER on 27/09/2017.
 */
public class ProveedorBeanSAP implements Serializable {

    private static final long serialVersionUID = 1L;

    private SapLog sapLogProveedor;
    private Proveedor proveedorSAP;

    public SapLog getSapLogProveedor() {
        return sapLogProveedor;
    }

    public void setSapLogProveedor(SapLog sapLogProveedor) {
        this.sapLogProveedor = sapLogProveedor;
    }

    public Proveedor getProveedorSAP() {
        return proveedorSAP;
    }

    public void setProveedorSAP(Proveedor proveedorSAP) {
        this.proveedorSAP = proveedorSAP;
    }
}
