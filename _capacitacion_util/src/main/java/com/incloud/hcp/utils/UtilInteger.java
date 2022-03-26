package com.incloud.hcp.utils;

import java.util.Optional;

public class UtilInteger {

    public static Integer coalesce( Integer input, Integer replacement ){

        return Optional.ofNullable( input ).orElse( replacement );

    }

}
