package com.incloud.hcp._gproveedor.jco.actualizarProveedor.service;

import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;

public interface JCOActualizarProveedorService {

    ProveedorRFCResponseDto actualizarProveedor(Integer idProveedor, String usuarioSap) throws Exception;

}
