package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoCabeceraDto implements Serializable {

    private static final long serialVersionUID = -1;

    private String proveedor;
    private String razonsocial;
    private String notapedido;
    private String fechaAprobacion;
    private String nroHojaEntrada;
    private String socieda;
    private String usuarioSolicitante;
    private BigDecimal monto;
    private BigDecimal montoAdjuntado;
    private String estado;
    private String moneda;
    private String fechaDesde;
    private String fechaHasta;



}
