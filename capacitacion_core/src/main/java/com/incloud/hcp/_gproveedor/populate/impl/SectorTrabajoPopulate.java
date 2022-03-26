package com.incloud.hcp._gproveedor.populate.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.SectorTrabajo;
import com.incloud.hcp.service._gproveedor.dto.SectorTrabajoDto;
import org.springframework.stereotype.Component;

@Component
public class SectorTrabajoPopulate implements Populater<SectorTrabajo, SectorTrabajoDto> {
    @Override
    public SectorTrabajoDto toDto(SectorTrabajo sectorTrabajo) {
        SectorTrabajoDto dto = new SectorTrabajoDto();
        dto.setIdSectorTrabajo(sectorTrabajo.getIdSectorTrabajo());
        dto.setDescripcion(sectorTrabajo.getDescripcion());
        return dto;
    }

    @Override
    public SectorTrabajo toEntity(SectorTrabajoDto sectorTrabajoDto) {
        SectorTrabajo entity = new SectorTrabajo();
        entity.setIdSectorTrabajo(sectorTrabajoDto.getIdSectorTrabajo());
        entity.setDescripcion(sectorTrabajoDto.getDescripcion());
        return entity;
    }
}
