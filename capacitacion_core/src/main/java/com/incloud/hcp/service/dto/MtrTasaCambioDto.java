package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.MtrTasaCambio;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class MtrTasaCambioDto {

    private Integer montoObtenido;
    private MtrTasaCambio mtrTasaCambio;

}
