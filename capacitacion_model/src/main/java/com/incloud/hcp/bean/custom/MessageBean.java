package com.incloud.hcp.bean.custom;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MessageBean {
    private String type;
    private String message;
}
