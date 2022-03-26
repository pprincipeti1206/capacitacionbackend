package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerNotaPedidoAdjunto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerNotaPedidoGrabarAdjuntoDto {

    private Integer cerNotaPedidoId;
    private List<CerNotaPedidoAdjunto> cerNotaPedidoAdjuntoList;

}
