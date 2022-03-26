package com.incloud.hcp.service.dto;

import com.incloud.hcp.domain.FacEstrategiaIteracion;
import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.FacHistorial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FacFacturaRechazoAprobador0SalidaDto {

    private String motivoRechazo;
    private FacFactura facFactura;
    private FacHistorial facHistorial;
    private FacEstrategiaIteracion facEstrategiaIteracion;
}
