package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerNotaPedido;
import com.incloud.hcp.service.support.PageRequestByExample;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoNotaPedidoFiltroPaginadoDto {


    private PageRequestByExample<CerNotaPedido> notapedido;
    private Date FechaInicio;
    private Date FechaFinal;


}
