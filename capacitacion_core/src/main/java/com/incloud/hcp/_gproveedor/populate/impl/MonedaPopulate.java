package com.incloud.hcp._gproveedor.populate.impl;

import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.Moneda;
import com.incloud.hcp.service._gproveedor.dto.MonedaDto;
import org.springframework.stereotype.Component;

@Component
public class MonedaPopulate implements Populater<Moneda, MonedaDto> {
    @Override
    public MonedaDto toDto(Moneda moneda) {
        MonedaDto dto = new MonedaDto();
        dto.setIdMoneda(moneda.getIdMoneda());
        dto.setDescripcion(moneda.getTextoBreve());
        return dto;
    }

    @Override
    public Moneda toEntity(MonedaDto monedaDto) {
        Moneda entity = new Moneda();
        entity.setIdMoneda(monedaDto.getIdMoneda());
        entity.setTextoBreve(monedaDto.getDescripcion());
        entity.setSigla(monedaDto.getDescripcion());
        return entity;
    }
}
