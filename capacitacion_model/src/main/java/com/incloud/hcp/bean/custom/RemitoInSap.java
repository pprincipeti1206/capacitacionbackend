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
public class RemitoInSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ebeln;
    private String posicion;
    private String pacno;
    private String introw;
    private String centro;
    private String ocurrencia;
    private String extrow;
    private String extgroup;
    private String rang;
    private String packagex;
    private String subpacno;
    private String srvnbr;
    private String umedida;
    private String qapro;
    private String rmprv;
    private String locdes;
    private String imputacion;
    private String usrsolic;
    private String matprv;
    private String mnd;
    private String punitario;
    private String descuento;
    private String itemad;
    private String txz01;
    private String matkl;
    private String saknr;
    private String kostl;
    private String posid;
    private String usraauto;
    private String dlort;
    private String aufnr;
    private String lifnr;
    private String ekorg;
    private String ekgrp;
    private String lzvon;
    private String lzbis;
    private String bnaman;
    private String budat;
    private String descuentoh;
    private String lgort;
    private String reswk;
    private String prestServ;
    private String guiaRemision;
    private String firma;
    private String idCertificado;
    //private String budat;

}
