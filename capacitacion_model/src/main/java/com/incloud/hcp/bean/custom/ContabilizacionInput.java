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
public class ContabilizacionInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idFactura;
    private String userLogin;
    private String usRes;
    private String fechaAuxiliar;
}
