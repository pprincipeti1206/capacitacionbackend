package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacturaAdjuntosDto {

    private FacFactura facFactura;
    private List<FacDocumentoAdjunto> adjuntos;


}
