package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ComprobantePagoSap implements Serializable {
    private static final long serialVersionUID = 1L;

    private String land1;
    private String name1;
    private String bukrs;
    private String detrac;
    private String buzei;
    private String augcp_d;//date
    private String augbl_d;//date
    private String belnr;
    private String  gjahr;
    private String  stcd1;
    private String   xblnr;
    private String stceg;
    private String   budat;//date
    private String  bldat;//date
    private String   cpudt;//date
    private String  dzbd1t;//decimal
    private String zlsch;
    private String waers;
    private String wmwst1;//decimal
    private String  wskto;//decimal
    private String mwskz_bnk;
    private String rmwwr;//decimal
    private String  bktxt;
    private String blart;
    private String  fdtag;//date
    private String awkey;
    private String  stblg;
    private String stjah;
    private String  augbl;
    private String bukrs_ref;
    private String  belnr_ref;
    private String gjahr_ref;
    private Date augdt;//date
    private String  zfbdt;//date
    private String  razonsocial;
    private String direccion;
    private String lifnr;
    private String status;
    private String  b2mining;
    private String text1;
    private String  banka;
    //private Date augdt;
}
