package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.MtrUsuarioFacturacion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class MtrUsuarioFirmanteEstrategiaDto {

    private Integer ordenFirmante;
    private String firma;
    private List<MtrUsuarioFacturacion> mtrUsuarioFacturacionPosiblesFirmantes;

}
