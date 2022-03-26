package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoNotaPedidoDetalleCantidadEntregaPrecioDto {

    private int cerNotaPedidoDetalleID;

    private BigDecimal CantidadEntrega;

    private BigDecimal precio;


}
