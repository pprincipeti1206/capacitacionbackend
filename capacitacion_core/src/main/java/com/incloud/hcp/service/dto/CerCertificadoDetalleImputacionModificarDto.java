package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerDocumentoAdjunto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoDetalleImputacionModificarDto {

    private List<CerCertificadoDetalleDto> cerCertificadoDetalleDtoList;
    private List<CerDocumentoAdjunto> cerDocumentoAdjuntoList;

}
