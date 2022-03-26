package com.incloud.hcp.bean.custom;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FiltroReporteFacturaCustom {

    private String tipoFactura;
    private String serieFactura;
    private String numeroFactura;
    private String numeroFacturaSap;
    private String ruc;
    private String razonSocial;
    private Integer mtrSociedadId;
    private String fechaFacturaIni;
    private String fechaFacturaFin;
    private String fechaEmisionIni;
    private String fechaEmisionFin;
    private String fechaCreacionIni;
    private String fechaCreacionFin;
    private Integer mtrEstadoId;
    private String indicadorConCertificado;
    private String esProveedorExtranjero;
    private String listaCodigoAcreedor;
    private String listaMtrEstadoId;
}
