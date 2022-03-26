package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PreRegistroItemBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigoNotaPedidoSap;
    private String codigoCertificado;
    private String posicionSap;
    private String introw;
    private String extrow;
    private String tipo;
    private Integer unidadMedidaId;
    private String unidadTextoItem;
    private BigDecimal cantidadAprobada;
    private BigDecimal precioUnitario;
    private String hes;
    private String hesMaterial;
    private Integer notaPedidoDetalleId;
    private Integer cerCertificadoId;
    private Integer cerCertificadoDetalleId;
    private Integer idPadre;
}
