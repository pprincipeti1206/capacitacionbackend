package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.CerCertificadoDetalle;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MensajeHes {

    private CerCertificadoDetalle certDetalle;
    private MensajeSap msg;
    private String importe;
}
