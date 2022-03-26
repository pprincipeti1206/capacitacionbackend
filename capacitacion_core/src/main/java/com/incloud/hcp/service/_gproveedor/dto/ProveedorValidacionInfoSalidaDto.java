package com.incloud.hcp.service._gproveedor.dto;

import com.incloud.hcp._gproveedor.sap.SapLog;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfo;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorValidacionInfoDetalle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class ProveedorValidacionInfoSalidaDto implements Serializable {

    ProveedorValidacionInfo proveedorValidacionInfo;
    List<ProveedorValidacionInfoDetalle> proveedorValidacionInfoDetalleList;
    Boolean indicadorRechazado = false;
    List<SapLog> listSapLog = new ArrayList<>();
    Proveedor proveedor;
    String motivoRechazo;


}
