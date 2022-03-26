package com.incloud.hcp.bean.custom;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TipoCambioBean {
    private Date fecha;
    private BigDecimal FactorUnidad;
    private String monedaLocal;
    private String monedaextranjera;
    private BigDecimal tipoCambio;
}
