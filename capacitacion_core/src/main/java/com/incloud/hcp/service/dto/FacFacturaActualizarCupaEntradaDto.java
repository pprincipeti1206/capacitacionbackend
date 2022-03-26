package com.incloud.hcp.service.dto;


import com.incloud.hcp.domain.FacDocumentoAdjunto;
import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.MtrTipoContrato;
import com.incloud.hcp.domain.MtrUsuarioFacturacion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaActualizarCupaEntradaDto {

    private FacFactura facFactura;
    private MtrTipoContrato mtrTipoContrato;
    private MtrUsuarioFacturacion mtrAprobador0;
    private String datosGrabadosJson;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoOtrosList;

}


