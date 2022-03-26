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
public class PreRegistroOutput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String belnr;
    private String gjahr;
    private String bkpfbelnr;
    private List<PreRegistroRetornoOutput> listaRetorno;
    private List<PreRegistroFacturaOutput> listaFactura;
}
