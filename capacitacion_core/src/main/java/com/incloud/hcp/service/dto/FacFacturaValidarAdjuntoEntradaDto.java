package com.incloud.hcp.service.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaValidarAdjuntoEntradaDto {

    private Integer idFactura;
    private boolean aprobado;
    private String motivo;


}


