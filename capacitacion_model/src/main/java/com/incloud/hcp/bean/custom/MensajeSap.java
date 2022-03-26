package com.incloud.hcp.bean.custom;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MensajeSap implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private String code;
    private String texto;

}
