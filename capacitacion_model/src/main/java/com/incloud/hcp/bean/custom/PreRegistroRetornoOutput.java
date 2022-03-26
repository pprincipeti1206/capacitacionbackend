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
public class PreRegistroRetornoOutput implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private String id;
    private String number;
    private String message;
    private String log_no;
    private String log_msg_no;
    private String message_v1;
    private String message_v2;
    private String message_v3;
    private String message_v4;
    private String parameter;
    private String row;//INT
    private String field;
    private String system;

}
