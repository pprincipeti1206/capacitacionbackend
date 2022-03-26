package com.incloud.hcp._gproveedor.populate.impl;

import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.TipoComprobante;
import com.incloud.hcp.service._gproveedor.dto.TipoComprobanteDto;
import org.springframework.stereotype.Component;

@Component
public class TipoComprobantePopulate implements Populater<TipoComprobante, TipoComprobanteDto> {
    @Override
    public TipoComprobanteDto toDto(TipoComprobante tipoComprobante) {
        TipoComprobanteDto dto = new TipoComprobanteDto();
        dto.setIdTipoComprobante(tipoComprobante.getIdTipoComprobante());
        dto.setDescripcion(tipoComprobante.getDescripcion());
        return dto;
    }

    @Override
    public TipoComprobante toEntity(TipoComprobanteDto tipoComprobanteDto) {
        TipoComprobante entity = new TipoComprobante();
        entity.setIdTipoComprobante(tipoComprobanteDto.getIdTipoComprobante());
        entity.setDescripcion(tipoComprobanteDto.getDescripcion());
        return entity;
    }
}
