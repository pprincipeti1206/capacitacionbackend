package com.incloud.hcp.bean.custom;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ImputacionCustom {
  private String codigoImputacion;
  private String tipoImputacion;
  private BigDecimal valorImputacion;
  private  String moneda;
  private String cuentaMayor;
  private String sociedad;


}
