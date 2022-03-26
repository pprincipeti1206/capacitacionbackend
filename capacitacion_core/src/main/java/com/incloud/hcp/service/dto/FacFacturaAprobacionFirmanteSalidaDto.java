package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaAprobacionFirmanteSalidaDto {

    private boolean exito;
    private String mensajeError;
    private boolean exitoEnvioCorreo;
    private String mensajeErrorCorreo;

    private String motivoAprobacion;
    private FacFactura facFactura;
    private FacHistorial facHistorial;
    private FacEstrategiaIteracion facEstrategiaIteracion;
    private FacEstrategiaFirma facEstrategiaFirma;
    private MtrUsuarioFacturacion mtrUsuarioFacturacionFirmante;

    private List<MtrUsuarioFacturacion> mtrUsuarioFacturacionFirmantePosiblesSgtes;
}
