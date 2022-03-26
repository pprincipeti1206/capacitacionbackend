package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaAndAdjuntoFacFactuDto {

    private FacFactura facFactura;
    private FacDocumentoAdjunto facDocumentoAdjunto;


}