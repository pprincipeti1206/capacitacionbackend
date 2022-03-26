package com.incloud.hcp._gproveedor.populate.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.bean.LineaFamilia;
import com.incloud.hcp.domain._gproveedor.domain.LineaComercial;
import org.springframework.stereotype.Component;

@Component
public class LineaComercialFamiliaPopulate implements Populater<LineaComercial, LineaFamilia> {
    @Override
    public LineaFamilia toDto(LineaComercial entity) {
        LineaFamilia dto = new LineaFamilia();
        dto.setId(entity.getIdLineaComercial());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    @Override
    public LineaComercial toEntity(LineaFamilia dto) {
        LineaComercial entity = new LineaComercial();
        entity.setIdLineaComercial(dto.getId());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
