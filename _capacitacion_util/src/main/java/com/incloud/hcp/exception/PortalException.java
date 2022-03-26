package com.incloud.hcp.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 03/09/2017.
 */
public class PortalException extends RuntimeException{

    private String message;

    private List<Error> details;

    public PortalException(String message) {
        this.message = message;
        details = new ArrayList<>();
    }

    public PortalException(String message, List<Error> details) {
        this.message = message;
        this.details = details;
    }


    public String getMessage() {
        return message;
    }

    public List<Error>  getDetails(){
        return this.details;
    }
}
