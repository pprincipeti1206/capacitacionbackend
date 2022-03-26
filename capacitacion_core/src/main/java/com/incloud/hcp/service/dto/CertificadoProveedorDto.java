package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoProveedorDto {

    private int idCertificado;
    private int idNotaPedido;
    private String nroCertificado;
    private String nroPedido;
    private LocalDateTime fechaCreacion;
    private BigDecimal Monto;
    private BigDecimal MontoAdjuntado;
    private String tipo;
    private String estado;
    private String estadoCodigo;
    private String moneda;



}
