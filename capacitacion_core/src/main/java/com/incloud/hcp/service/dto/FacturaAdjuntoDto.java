package com.incloud.hcp.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@EqualsAndHashCode
public class FacturaAdjuntoDto {

    private MultipartFile multipartFile;
    private String TipoAdjunto;


}
