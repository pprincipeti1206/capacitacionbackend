package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PosicionExternoInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String extrow;
    private String texto;
    private String cantidad;
    //private BigDecimal cantidadPendiente;
    //private BigDecimal cantidadTotal;
    //private BigDecimal totalLinea;
    //private BigDecimal totalLineaAjustado;
    private String preciobase;
    private String codigoimputacion;
    private String tipoimputacion;
    private String codigocuentamayor;
    //private BigDecimal porcentaje;
    //private BigDecimal valorImputacion;
    //private String cuentaMayor;
    //private String sociedad;
    //private String ceco;
    //private String orden;
    //private String afe;
    //private String intRow;

}
