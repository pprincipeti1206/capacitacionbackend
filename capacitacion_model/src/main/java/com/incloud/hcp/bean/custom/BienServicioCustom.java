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
public class BienServicioCustom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ebeln;
    private String ebelp;
    private String extrow;
    private String codBienServicio;
    private String tipo;
}
