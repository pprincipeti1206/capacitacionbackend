package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaRechazoFirmanteSalidaDto {

    private String motivoRechazo;
    private FacFactura facFactura;
    private FacHistorial facHistorial;
    private FacEstrategiaIteracion facEstrategiaIteracion;
    private FacEstrategiaFirma facEstrategiaFirma;
    private MtrUsuarioFacturacion mtrUsuarioFacturacionFirmante;
}
