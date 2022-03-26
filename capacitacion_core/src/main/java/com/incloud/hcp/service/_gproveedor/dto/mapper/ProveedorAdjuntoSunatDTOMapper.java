package com.incloud.hcp.service._gproveedor.dto.mapper;


import com.incloud.hcp.domain._gproveedor.domain.ProveedorAdjuntoSunat;
import com.incloud.hcp.service._gproveedor.dto.ProveedorAdjuntoSunatDto;

public class ProveedorAdjuntoSunatDTOMapper implements EntityDTOMapper<ProveedorAdjuntoSunat, ProveedorAdjuntoSunatDto> {

    @Override
    public ProveedorAdjuntoSunat toEntity(ProveedorAdjuntoSunatDto dto) {

        ProveedorAdjuntoSunat adjuntoSunat = new ProveedorAdjuntoSunat();
        adjuntoSunat.setId(dto.getId());
        adjuntoSunat.setArchivoId(dto.getArchivoId());
        adjuntoSunat.setRutaAdjunto(dto.getRutaAdjunto());
        adjuntoSunat.setArchivoTipo(dto.getArchivoTipo());
        adjuntoSunat.setArchivoNombre(dto.getArchivoNombre());
        return adjuntoSunat;


    }

    @Override
    public ProveedorAdjuntoSunatDto toDto(ProveedorAdjuntoSunat entity) {
        ProveedorAdjuntoSunatDto dto = new ProveedorAdjuntoSunatDto();
        dto.setId(entity.getId());
        dto.setArchivoId(entity.getArchivoId());
        dto.setArchivoNombre(entity.getArchivoNombre());
        dto.setArchivoTipo(entity.getArchivoTipo());
        dto.setRutaAdjunto(entity.getRutaAdjunto());
        dto.setCodigoTipoDocumento( entity.getCodigoTipoDocumento() );
        return dto;
    }
}
