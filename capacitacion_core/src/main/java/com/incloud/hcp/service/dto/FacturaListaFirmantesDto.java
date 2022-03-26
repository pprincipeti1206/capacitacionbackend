package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.FacEstrategiaFirma;
import com.incloud.hcp.domain.MtrUsuarioFacturacion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class FacturaListaFirmantesDto {

    private Integer ordenFirmante;
    private boolean indAprobacion = false;
    private boolean indRechazo = false;
    private String firma;

    private FacEstrategiaFirma facEstrategiaFirma;
    private MtrUsuarioFacturacion mtrUsuarioFacturacionFirmante;

    private List<MtrUsuarioFacturacion> mtrUsuarioFacturacionPosiblesFirmantes;

}
