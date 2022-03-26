package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerFirma;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CerFirmaSinAprobarDto {

    private CerFirma cerFirma;
    private CerCertificado cerCertificado;

}
