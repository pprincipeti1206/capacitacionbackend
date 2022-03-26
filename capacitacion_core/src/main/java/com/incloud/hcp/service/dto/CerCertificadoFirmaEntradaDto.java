package com.incloud.hcp.service.dto;

import com.incloud.hcp._security.UserSession;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CerCertificadoFirmaEntradaDto {

    //@NotEmpty(message = "Código Agrupado Estado del Certificado es requerido")
    private String codigoAgrupadoEstado;

    //@NotEmpty(message = "Código Estado del Certificado es requerido")
    private String codigoEstado;

    //@NotEmpty(message = "Código Tipo de Firma es requerido")
    private String codigoTipoFirma;

    private UserSession userSession;
}
