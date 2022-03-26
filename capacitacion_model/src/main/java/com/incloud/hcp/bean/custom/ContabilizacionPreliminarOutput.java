package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContabilizacionPreliminarOutput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numeroDocumento;
    private String ejercicio;
    private List<MensajeSap> listaMensaje;
}
