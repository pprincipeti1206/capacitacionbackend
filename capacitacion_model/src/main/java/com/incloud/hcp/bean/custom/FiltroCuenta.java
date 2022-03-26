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
public class FiltroCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer rangoMenor;
    private Integer rangoMayor;
    private String tipoImputacion;
    private String codigoSap;

}
