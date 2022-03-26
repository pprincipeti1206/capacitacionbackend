package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.MtrEstado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class EstadoCertificadoDto {

    private MtrEstado estado;
    private int cerCertificadoId;
    private String descripcion;


}
