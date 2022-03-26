package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoDetalleConglomeradoDto {

    private String line;
    private String lineP;
    private String descripcion;
    private String unida;
    private BigDecimal cantida;
    private BigDecimal precio;
    private BigDecimal monto;
    private BigDecimal montoAdjuntado;
    private String moneda;

}
