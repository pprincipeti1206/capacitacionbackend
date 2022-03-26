package com.incloud.hcp._gproveedor.populate.impl;


import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.HomologacionRespuesta;
import com.incloud.hcp.service._gproveedor.dto.HomologacionRespuestaDto;
import org.springframework.stereotype.Component;

@Component
public class HomologacionRespuestaPopulate implements Populater<HomologacionRespuesta, HomologacionRespuestaDto> {

    @Override
    public HomologacionRespuestaDto toDto(HomologacionRespuesta entity) {
        HomologacionRespuestaDto dto = new HomologacionRespuestaDto();
        dto.setIdHomologacionRespuesta(entity.getIdHomologacionRespuesta());
        dto.setPuntaje(entity.getPuntaje());
        dto.setRespuesta(entity.getRespuesta());
        return dto;
    }

    @Override
    public HomologacionRespuesta toEntity(HomologacionRespuestaDto dto) {
        HomologacionRespuesta entity = new HomologacionRespuesta();
        entity.setIdHomologacionRespuesta(dto.getIdHomologacionRespuesta());
        entity.setPuntaje(dto.getPuntaje());
        entity.setRespuesta(dto.getRespuesta());

        return entity;
    }
}
