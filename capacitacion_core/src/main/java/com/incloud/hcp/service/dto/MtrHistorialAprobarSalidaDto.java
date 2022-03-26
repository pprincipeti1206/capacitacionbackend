package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.MtrHistorialApagar;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class MtrHistorialAprobarSalidaDto {

    private Boolean estadoApagado;
    private List<MtrHistorialApagar> mtrHistorialApagarList;

}
