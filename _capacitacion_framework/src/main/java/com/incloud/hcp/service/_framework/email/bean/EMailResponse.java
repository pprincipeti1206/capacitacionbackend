package com.incloud.hcp.service._framework.email.bean;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EMailResponse {

    private String message;
    private boolean status;
}
