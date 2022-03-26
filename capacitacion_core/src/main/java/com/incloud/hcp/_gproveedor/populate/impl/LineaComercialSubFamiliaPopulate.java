package com.incloud.hcp._gproveedor.populate.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.bean.LineaSubFamilia;
import com.incloud.hcp.domain._gproveedor.domain.LineaComercial;
import org.springframework.stereotype.Component;

@Component
public class LineaComercialSubFamiliaPopulate implements Populater<LineaComercial, LineaSubFamilia> {
    @Override
    public LineaSubFamilia toDto(LineaComercial entity) {
        LineaSubFamilia dto = new LineaSubFamilia();
        dto.setId(entity.getIdLineaComercial());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }

    @Override
    public LineaComercial toEntity(LineaSubFamilia dto) {
        LineaComercial entity = new LineaComercial();
        entity.setIdLineaComercial(dto.getId());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
