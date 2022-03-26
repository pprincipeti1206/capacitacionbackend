package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaAprobacionFirmanteEntradaDto {

    private String motivoAprobacion;
    private Integer facFacturaId;

}
