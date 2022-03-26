package com.incloud.hcp.utils;

import java.util.Optional;

public class UtilString {

    public static String coalesceTrim( String object ){

        return Optional.ofNullable( object ).orElse( "" ).trim();

    }

}
