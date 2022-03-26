package com.incloud.hcp.bean.custom;

import com.incloud.hcp.domain.FacDocumentoAdjunto;
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
public class FacturaCustom implements Serializable {

    private Integer idFactura;
    private String fechaEstimadaPago;
    private List<FacDocumentoAdjunto> facDocumentoAdjuntoFacturaList;

}
