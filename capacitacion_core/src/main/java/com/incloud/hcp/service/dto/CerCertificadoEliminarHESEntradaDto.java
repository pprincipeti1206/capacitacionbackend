package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoEliminarHESEntradaDto {

    private String motivoEliminacion;
    private String nroDocumentoSAP;

}
