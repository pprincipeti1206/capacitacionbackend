package com.incloud.hcp.bean.custom;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContabilizacionPreliminarInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nroDocumento;
    private String sociedad;
    private String annio;
    private String flagConta;
    private String flagAnula;
}
