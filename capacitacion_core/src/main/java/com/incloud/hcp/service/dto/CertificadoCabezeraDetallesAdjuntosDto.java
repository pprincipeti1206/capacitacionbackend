package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerCertificadoDetalle;
import com.incloud.hcp.domain.CerDocumentoAdjunto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoCabezeraDetallesAdjuntosDto {

    private CerCertificado cerCertificado;
    private String HistorialDescripcion;
    private List<CerCertificadoDetalle> cerCertificadoDetalle;
    private List<CerDocumentoAdjunto> adjuntos;
    private Integer DocumentoAdjuntoID;
    private String typeState;

}
