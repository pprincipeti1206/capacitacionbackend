package com.incloud.hcp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Response<T> {

    private boolean ok;
    private String message;
    private Integer size;
    private List<String> errors;

    @JsonInclude( JsonInclude.Include.ALWAYS )
    private T result;

    // Métodos
    public void ok(boolean ok, T result) {
        this.ok = ok;
        this.message = "ok";
        this.result = result;
        this.errors = null;
    }

    public void ok(boolean ok, T result, String message) {
        this.ok = ok;
        this.message = "ok";
        this.result = result;
        this.errors = null;
        this.message = message;
    }

    public void ok(boolean ok, T result, int size) {
        this.ok = ok;
        this.message = "ok";
        this.result = result;
        this.size = size;
        this.errors = null;
        this.message = message;
    }
    public void ok(boolean ok, T result, List<Exception> exceptions) {
        this.ok = ok;
        this.message = "ok";
        this.result = result;
        this.errors = exceptions.size() > 0 ? this.errors(exceptions) : null;
        this.message = exceptions.size() > 0 ? exceptions.get(0).getMessage() : null;
    }

    private void ok(boolean ok, List<String> errors, String message) {
        this.ok = ok;
        this.errors = errors;
        this.message = message;
        this.result = null;
    }

    public void ok(Exception exception) {
        this.ok(false, new ArrayList<>(this.findAllCauseError(exception)), exception.getMessage());
    }

    public void ok(List<Exception> exceptions) {
        this.ok(false, this.errors(exceptions), exceptions.get(0).getMessage());
    }

    public List<String> errors(List<Exception> exceptions) {

        Set<String> errors = new HashSet<>();
        for (Exception exception : exceptions) {
            errors.addAll(this.findAllCauseError(exception));
        }
        return new ArrayList<>(errors);
    }

    public Set<String> findAllCauseError(Exception exception) {
        Set<String> errors = new HashSet<>();
        Throwable rootCause = exception;
        errors.add(exception.toString());

        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            Iterator<String> itr = errors.iterator();
            while (itr.hasNext())
                if (!itr.next().endsWith(rootCause.toString()))
                    errors.add(rootCause.toString()); // endWith: busca carcteres en string

            rootCause = rootCause.getCause();
        }
        errors.add(rootCause.toString());

        return errors;
    }
}