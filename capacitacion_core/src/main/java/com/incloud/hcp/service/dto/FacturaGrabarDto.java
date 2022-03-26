package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.FacFacturaCertificado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacturaGrabarDto {

    private FacFactura facFactura;
    private List<FacFacturaCertificado> facFacturaCertificadoList;

    private FacDocumentoAdjunto adjuntoFactura;
    private FacDocumentoAdjunto adjuntoDocumentoCDR;
    private FacDocumentoAdjunto adjuntoDocumentoGuia;
    private List<FacDocumentoAdjunto> listaAdjuntoOtros;
    private String codigoCorrelativoAdjunto;
}
