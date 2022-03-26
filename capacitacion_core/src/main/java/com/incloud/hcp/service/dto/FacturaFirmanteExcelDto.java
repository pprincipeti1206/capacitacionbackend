package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacturaFirmanteExcelDto {

    private Integer nroOrdenFirmanteTotal;
    private Integer nroOrdenFirmanteActual;
    private String estadoFirmante;
    private String sectorActual;
    private String tipoGerencialActual;
    private String listaUsuariosFirmante;
    private String firmanteActual;

}
