package com.incloud.hcp.bean.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SendNotifySupplier implements Serializable {


    @JsonProperty( value = "Supplier" )       private Proveedor  supplier      ;
    @JsonProperty( value = "Reason" )         private String     reason        ;
    @JsonProperty( value = "StatusSupplier" ) private String     statusSupplier;

}