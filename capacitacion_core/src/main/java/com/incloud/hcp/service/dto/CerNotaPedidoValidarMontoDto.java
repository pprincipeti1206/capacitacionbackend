package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class CerNotaPedidoValidarMontoDto {

    private String tipoAbiertoCerrada;
    private String tipoMaterialServicio;
    private BigDecimal montoPendiente;

}
