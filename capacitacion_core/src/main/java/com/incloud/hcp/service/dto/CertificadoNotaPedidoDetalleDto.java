package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerNotaPedido;
import com.incloud.hcp.domain.CerNotaPedidoDetalle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoNotaPedidoDetalleDto {

    private CerNotaPedido cerNotaPedido;

    private List<CerNotaPedidoDetalle> cerNotaPedidoDetallelist;


}
