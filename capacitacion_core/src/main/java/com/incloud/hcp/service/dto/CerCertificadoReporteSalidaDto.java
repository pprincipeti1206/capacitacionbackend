package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoReporteSalidaDto {

    private String tipoPosicion;
    private String linea;
    private String descripcion;
    private String unidad;
    private String tipoImputacion;
    private String cuenta;
    private String ceCo;
    private BigDecimal precioBase;
    private BigDecimal cantidadEntregada;
    private BigDecimal totalLinea;

}
