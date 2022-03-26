package com.incloud.hcp.bean.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class SaveApprovalInput implements Serializable {


    @JsonProperty( value = "Status" )        private String  status       ;
    @JsonProperty( value = "Reason" )     private String  reason       ;
    @JsonProperty( value = "IdProveedor" ) private Integer idProveedor   ;

}