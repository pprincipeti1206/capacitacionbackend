package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CertificadoExternoInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numerohes;
    private String numeropedido;
    private String numerocertificado;
    private String fechadesde;
    private String fechahasta;

    private String concepto;
    private String aprobadorfirmaa;
    private String aprobadorfirmab;
    private String montototal;
    private String montototalajustado;
    private String fechaaprobacion;
    //private String encargadoInterno;
    //private String encargadoExterno;
    //private String referencia;
    //private String textoCabecera;
    //private BigDecimal valorTotal;
    //private String creadoPor;
    //private String creadoEl;
    private String posicionnotapedido;
    private String lugar;
    //======================
    //private String claseDocumento;
    //private String moneda;
    //private String ruc;
    //private String sociedad;

    private List<PosicionExternoInput> lista;


}
