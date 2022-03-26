package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerNotaPedido;
import com.incloud.hcp.domain.CerNotaPedidoAdjunto;
import com.incloud.hcp.domain.CerNotaPedidoHistorial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerNotaPedidoDto {

    private CerNotaPedido cerNotaPedido;
    private List<CerNotaPedidoDetalleDto> cerNotaPedidoDetalleDtoList;
    private List<CerNotaPedidoAdjunto> cerNotaPedidoAdjuntoList;
    private List<CerNotaPedidoHistorial> cerNotaPedidoHistorialList;

}
