package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.CerCertificadoDetalleSap;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DetalleCertificadoHesCustom implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idDetalleCertificado;
    private String posicionSap;
    private String textoPosicion;
    private CerCertificadoDetalleSap detalleSap;
}
