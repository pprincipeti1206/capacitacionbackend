package com.incloud.hcp.service.dto;


import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacImputacion;
import com.incloud.hcp.domain.MtrSector;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaGrabarAprobador0EntradaDto {

    private Integer facFacturaId;
    private MtrSector mtrSector;
    private List<FacImputacion> facImputacionList;

    private List<FacDocumentoAdjunto> facDocumentoAdjuntoOtrosList;

}


