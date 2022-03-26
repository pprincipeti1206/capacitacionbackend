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
public class OperacionCertificado  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String operacion;
    private String descripcion;
    private String tipoFirma;
    Integer idCertificado;
    private String  loginName;
}
