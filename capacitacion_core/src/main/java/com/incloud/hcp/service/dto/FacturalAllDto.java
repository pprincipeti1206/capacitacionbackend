package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacturalAllDto {

    private FacFactura facFactura;
    private List<CerCertificado> certificados;
    private List<FacDocumentoAdjunto> listAdjunto;
    private String descripcionHistorial;



}
