package com.incloud.hcp.common.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CabeceraExcel {

    private String title;
    private Integer fondoColor01;
    private Integer fondoColor02;
    private Integer fondoColor03;
    private Integer letraColor01;
    private Integer letraColor02;
    private Integer letraColor03;
    private String comentario;

    private String id;
    private Integer ancho;


}