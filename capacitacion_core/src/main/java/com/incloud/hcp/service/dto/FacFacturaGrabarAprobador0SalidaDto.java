package com.incloud.hcp.service.dto;


import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaGrabarAprobador0SalidaDto {

    private Integer facFacturaId;
    private MtrSector mtrSector;
    private List<FacImputacion> facImputacionList;


    private FacFactura facFactura;
    private FacHistorial facHistorial;
    private FacEstrategiaIteracion facEstrategiaIteracion;
    private List<FacEstrategiaFirma> facEstrategiaFirmaList;

    private List<FacDocumentoAdjunto> facDocumentoAdjuntoOtrosList;

    private MtrUsuarioFacturacion mtrAprobador0;
    private List<MtrUsuarioFacturacion> mtrUsuarioFacturacionFirmantePosiblesSgtes;
}


