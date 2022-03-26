package com.incloud.hcp._gproveedor.jco.proveedor.service;

import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorResponseRFC;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;

import java.util.List;

public interface JCOProveedorService {

    ProveedorRFCResponseDto grabarProveedor(Integer idProveedor, String usuarioSap) throws Exception;

    ProveedorResponseRFC grabarListaProveedorSAP(List<Proveedor> listaProveedorPotencial, String usuarioSap) throws Exception;
}


