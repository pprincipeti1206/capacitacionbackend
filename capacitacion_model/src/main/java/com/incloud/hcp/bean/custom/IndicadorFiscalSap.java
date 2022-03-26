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
public class IndicadorFiscalSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stcd1;
    private String bukrs;
    private String lifnr;
    private String name1;
    private String name2;

}
