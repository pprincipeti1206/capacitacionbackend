package com.incloud.hcp.rest._framework;

import com.incloud.hcp.common.BindingErrorsResponse;
import com.incloud.hcp.util.Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public abstract class BaseRest {

    protected boolean devuelveRuntimeException = true;
    private final String CONSTANTE_SEPARADOR = " / ";

    protected HttpHeaders devuelveErrorHeaders(Exception e) {
        String msjError = Utils.obtieneMensajeErrorExceptionDepurado(e);
        HttpHeaders headers = new HttpHeaders();
        headers.add("errors", msjError);
        return headers;
    }

    protected String devuelveErrorHeaders(BindingResult bindingResult, BindingErrorsResponse errors) {
        errors.addAllErrors(bindingResult);
        String errorDevuelve = "";
        int tam = bindingResult.getFieldErrors().size();
        int contador = 0;
        for (FieldError beanError: bindingResult.getFieldErrors()) {
            contador++;
            errorDevuelve += beanError.getDefaultMessage() ;
            if (contador < tam)
                errorDevuelve += CONSTANTE_SEPARADOR;
        }
        return errorDevuelve;
    }


}
