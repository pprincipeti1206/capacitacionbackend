package com.incloud.hcp.service._gproveedor.dto.mapper;


import com.incloud.hcp.domain._gproveedor.domain.ProveedorEvaluacion;
import com.incloud.hcp.service._gproveedor.dto.EvaluacionDesempenioDto;

/**
 * Created by Administrador on 18/09/2017.
 */
public class EvaluacionDTOMapper implements EntityDTOMapper<ProveedorEvaluacion, EvaluacionDesempenioDto> {
    @Override
    public ProveedorEvaluacion toEntity(EvaluacionDesempenioDto dto) {
        ProveedorEvaluacion evaluacion = new ProveedorEvaluacion();
        evaluacion.setEvaluacion(dto.getEvaluacion());
        evaluacion.setAnio(dto.getAnio());
        return evaluacion;
    }

    @Override
    public EvaluacionDesempenioDto toDto(ProveedorEvaluacion entity) {
        EvaluacionDesempenioDto dto = new EvaluacionDesempenioDto();
        dto.setAnio(entity.getAnio());
        dto.setEvaluacion(entity.getEvaluacion());
        return dto;
    }
}
