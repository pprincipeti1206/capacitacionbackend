package com.incloud.hcp._gproveedor.populate.impl;

import com.incloud.hcp._gproveedor.populate.Populater;
import com.incloud.hcp.domain._gproveedor.domain.Homologacion;
import com.incloud.hcp.enums._gproveedor.TipoHomologacionEnum;
import com.incloud.hcp.service._gproveedor.LineaComercialService;
import com.incloud.hcp.service._gproveedor.dto.HomologacionDto;
import com.incloud.hcp.service._gproveedor.dto.TipoHomologacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class HomologacionPopulate implements Populater<Homologacion, HomologacionDto> {

    private LineaComercialService lineaComercialService;

    @Autowired
    public void setLineaComercialService(LineaComercialService lineaComercialService) {
        this.lineaComercialService = lineaComercialService;
    }

    @Override
    public HomologacionDto toDto(Homologacion homologacion) {
        HomologacionDto dto = new HomologacionDto();

        Optional.ofNullable(homologacion.getLineaComercial()).ifPresent(lc -> {
            dto.setIdLineaComercial(lc.getIdLineaComercial());
            dto.setLineaComercial(lc.getDescripcion());
        });

        TipoHomologacionEnum selected = Optional.ofNullable(homologacion.getIndAdjunto())
                .map(indicador -> {
                    switch (homologacion.getIndAdjunto()) {
                        case "1":
                            return TipoHomologacionEnum.Adjunto;
                        default:
                            return TipoHomologacionEnum.Pregunta;
                    }
                }).orElse(TipoHomologacionEnum.Pregunta);


        List<TipoHomologacionDto> tipo = new ArrayList<>();
        Arrays.asList(TipoHomologacionEnum.values()).stream()
                .map(t -> new TipoHomologacionDto(t, t.equals(selected)))
                .forEach(tipo::add);

        dto.setTipo(tipo);
        dto.setEstado(homologacion.getEstado());
        dto.setIdHomologacion(homologacion.getIdHomologacion());
        dto.setPeso(homologacion.getPeso());
        dto.setPregunta(homologacion.getPregunta());
        return dto;
    }

    @Override
    public Homologacion toEntity(HomologacionDto homologacionDto) {
        Homologacion entity = new Homologacion();
        Optional.ofNullable(lineaComercialService)
                .map(lcs -> lcs.getByIdLineaComercial(homologacionDto.getIdLineaComercial()))
                .ifPresent(entity::setLineaComercial);
        entity.setIdHomologacion(homologacionDto.getIdHomologacion());

        TipoHomologacionDto selected = homologacionDto.getTipo().stream()
                .filter(TipoHomologacionDto::isSeleccionado)
                .findFirst()
                .orElse(new TipoHomologacionDto(TipoHomologacionEnum.Pregunta, Boolean.TRUE));

        switch (selected.getDescripcion()) {
            case Adjunto:
                entity.setIndAdjunto("1");
                break;
            default:
                entity.setIndAdjunto("0");
        }

        entity.setPeso(homologacionDto.getPeso());
        entity.setPregunta(homologacionDto.getPregunta());
        return entity;
    }
}
