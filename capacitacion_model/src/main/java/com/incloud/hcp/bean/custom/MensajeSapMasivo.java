package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MensajeSapMasivo implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer idCertificado;
    String codigoCertificado;
    List<MensajeSap> listaMensaje;


}
