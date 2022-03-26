package com.incloud.hcp.service._gproveedor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class ProveedorValidacionInfoEntradaDto implements Serializable {

    private Integer idProveedor;
    private String motivoRechazo;

}
