package com.incloud.hcp.service.dto;

import com.incloud.hcp.bean.custom.MensajeSap;
import com.incloud.hcp.bean.custom.OperacionCertificado;
import com.incloud.hcp.domain.CerCertificado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoAprobacionFirmaBDto {

    private CerCertificado cerCertificado;
    private CerCertificadoDetalleImputacionModificarDto beanImputacion;
    private OperacionCertificado operacionCertificado;
    private List<MensajeSap> mensajeSapList;

}


