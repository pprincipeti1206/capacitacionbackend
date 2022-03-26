package com.incloud.hcp.domain.response;

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
public class SaveInformationResponse implements Serializable {

    @JsonProperty( value = "Save" )        private Boolean save       ;
    @JsonProperty( value = "Message" )     private String message     ;
    @JsonProperty( value = "MessageType" ) private String messageType  ;

}