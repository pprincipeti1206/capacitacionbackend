package com.incloud.hcp._security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrador on 07/11/2017.
 */
@Data
@ToString
@EqualsAndHashCode
public class UserSession {
    private String id;
    private String ruc;
    private String mail;
    private String displayName;
    private String firstName;
    private String lastName;




}
