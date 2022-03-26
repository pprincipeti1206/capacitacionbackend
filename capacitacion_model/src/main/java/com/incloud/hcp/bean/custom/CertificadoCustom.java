package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.CerCertificado;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CertificadoCustom {
    private CerCertificado certificado;
    //private CerNotaPedido notaPedido;
    private List<NotaPedidoCustom> listaNotaPedidoDetalle;
}
