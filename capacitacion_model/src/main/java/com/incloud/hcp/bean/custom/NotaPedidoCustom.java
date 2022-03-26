package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.CerCertificadoDetalle;
import com.incloud.hcp.domain.CerNotaPedidoDetalle;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class NotaPedidoCustom {
    private CerNotaPedidoDetalle parent;
    private List<CerCertificadoDetalle> listChild;
}
