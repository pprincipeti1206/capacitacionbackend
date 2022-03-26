package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class CertificadoConglomeradoDto {

    private MtrCentroAlmacen centroNotaPedido1erRegistro;

    private CerCertificado certificadoCabeceraDto;
    private List<CerCertificadoDetalleDto> cerCertificadoDetalleDto;
    private List<CertificadoHistorialConglomeradoDto> cerHistorialConglomeradoDto;
    private List<CerFirma> certificadoFirmaConglomeradoDto;
    private List<CerCertificadoDetalleSap> cerCertificadoDetalleSapList;
    private List<CerHistorial> cerHistorialList;

}
