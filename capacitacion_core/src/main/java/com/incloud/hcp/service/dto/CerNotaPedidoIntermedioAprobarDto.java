package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerNotaPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CerNotaPedidoIntermedioAprobarDto {

    private CerNotaPedido cerNotaPedido;
    private List<CerCertificado> certificadoCreadasAutomaticaList;

}
