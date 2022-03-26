package com.incloud.hcp.service.dto;


import com.incloud.hcp.bean.custom.MensajeHistorialSap;
import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaActualizarCupaSalidaDto {

    private FacFactura facFactura;
    private MtrTipoContrato mtrTipoContrato;
    private MtrUsuarioFacturacion mtrAprobador0;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoFacturaList;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoGuiaList;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoCdrList;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoOtrosList;

    //Datos de salida
    private List<FacHistorial> facHistorialGeneradoList;
    private FacEstrategiaIteracion facEstrategiaIteracion;
    private List<MensajeHistorialSap> mensajeSapList;


}


