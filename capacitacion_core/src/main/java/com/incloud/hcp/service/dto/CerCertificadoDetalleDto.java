package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificadoDetalle;
import com.incloud.hcp.domain.CerNotaPedidoDetalle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoDetalleDto {

    private String tipoPosicion;
    private CerNotaPedidoDetalle cerNotaPedidoDetalle;
    private CerCertificadoDetalle cerCertificadoDetalle;


}
