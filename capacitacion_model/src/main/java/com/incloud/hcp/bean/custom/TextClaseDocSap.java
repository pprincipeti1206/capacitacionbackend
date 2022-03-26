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
public class TextClaseDocSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String spras;
    private String bsart;
    private String bstyp;
    private String batxt;

    //T_T161T
}
