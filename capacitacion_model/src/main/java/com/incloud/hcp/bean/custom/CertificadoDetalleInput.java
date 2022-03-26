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
public class CertificadoDetalleInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idCertificado;
    private Integer idDetalle;
    private String numeroHes;
}
