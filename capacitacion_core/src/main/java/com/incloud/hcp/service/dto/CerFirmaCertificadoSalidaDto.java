package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerCertificadoFirma;
import com.incloud.hcp.domain.CerFirma;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerFirmaCertificadoSalidaDto {

    private CerFirma cerFirma;
    private CerCertificado cerCertificado;
    private List<CerCertificadoFirma> cerCertificadoFirmaList;

}
