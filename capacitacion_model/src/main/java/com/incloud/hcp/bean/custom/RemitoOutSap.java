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
public class RemitoOutSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ebeln;
    private String posicion;
    private String pacno;
    private String introw;
    private String centro;
    private String ocurrencia;
    private String entrega;
    private String error;
    private String rmprv;
    private String state;
    private String importe;
    private String mnd;
    private String type;
}
