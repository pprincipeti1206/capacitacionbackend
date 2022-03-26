package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.MtrAprobador;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerFirmaSinAprobarCorreoDto {

    private MtrAprobador mtrAprobador;
    private List<CerFirmaSinAprobarDto> cerCertificadoList;


}
