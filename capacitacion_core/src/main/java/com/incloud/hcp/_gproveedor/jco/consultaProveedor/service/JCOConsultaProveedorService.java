package com.incloud.hcp._gproveedor.jco.consultaProveedor.service;


import com.incloud.hcp._gproveedor.jco.consultaProveedor.dto.ConsultaProveedorRFCResponseDto;

public interface JCOConsultaProveedorService {

    ConsultaProveedorRFCResponseDto listaProveedorByRFC(
            String nroAcreedor, String fechaInicio, String fechaFin, String Email) throws Exception;


}


