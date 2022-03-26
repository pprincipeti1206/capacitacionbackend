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
public class MensajeListaSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private MensajeSap mensaje;
    private List<CentroSap> lista;

}
