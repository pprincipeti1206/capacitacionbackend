package com.incloud.hcp._gproveedor.populate.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.CondicionPago;
import com.incloud.hcp.service._gproveedor.dto.CondicionPagoDto;
import org.springframework.stereotype.Component;

@Component
public class CondicionPagoPopulate implements Populater<CondicionPago, CondicionPagoDto> {

    @Override
    public CondicionPagoDto toDto(CondicionPago condicionPago) {
        CondicionPagoDto dto = new CondicionPagoDto();
        dto.setIdCondicionPago(condicionPago.getIdCondicionPago());
        dto.setDescripcion(condicionPago.getDescripcion());
        return dto;
    }

    @Override
    public CondicionPago toEntity(CondicionPagoDto condicionPagoDto) {
        CondicionPago entity = new CondicionPago();
        entity.setIdCondicionPago(condicionPagoDto.getIdCondicionPago());
        entity.setDescripcion(condicionPagoDto.getDescripcion());

        return entity;
    }
}
