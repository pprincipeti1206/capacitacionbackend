package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReporteFacturaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigoCertificado;
    private String linea;
    private String descripcion;
    private String unidad;
    private String cuenta;
    private String tipoImputacion;
    private String tipoPosicion;
    private String ceCo;
    private BigDecimal precioBase;
    private BigDecimal cantidadEntregada;
    private BigDecimal totalLinea;
    private BigDecimal montoTotal;
    private BigDecimal montoTotalAjustado;
    
    

}
