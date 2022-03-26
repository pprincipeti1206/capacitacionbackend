package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ActualizaHesInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idCertificado;
    private BigDecimal montoAjustado;
    private String numeroHes;
    private List<CertificadoDetalleInput> lista;

}
