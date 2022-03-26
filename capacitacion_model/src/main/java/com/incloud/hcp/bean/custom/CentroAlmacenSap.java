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
public class CentroAlmacenSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String werks;
    private String ort01;
    private String city2;
    private String stras;
    private String lgort;
    private String lgobe;
}
