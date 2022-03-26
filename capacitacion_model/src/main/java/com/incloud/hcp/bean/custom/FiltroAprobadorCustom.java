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
public class FiltroAprobadorCustom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String codigoIdp;
    private String idLogin;
    private String email;

}
