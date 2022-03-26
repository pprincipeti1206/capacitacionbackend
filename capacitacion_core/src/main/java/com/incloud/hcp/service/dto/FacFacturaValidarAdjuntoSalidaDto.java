package com.incloud.hcp.service.dto;


import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.FacHistorial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaValidarAdjuntoSalidaDto {

    private FacFactura facFactura;
    private FacHistorial facHistorial;

    private FacFacturaValidarAdjuntoEntradaDto beanEntrada;
    private boolean indicadorOk;
    private String mensajeError;

}


