package com.incloud.hcp.service._framework.bean;

public class BeanCargaMasivoDTO<T> {

    private T bean;
    private boolean error = false;
    private String errorMensaje = "";

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMensaje() {
        return errorMensaje;
    }

    public void setErrorMensaje(String errorMensaje) {
        this.errorMensaje = errorMensaje;
    }

    @Override
    public String toString() {
        return "BeanCargaMasivoDTO{" +
                "bean=" + bean +
                ", error=" + error +
                ", errorMensaje='" + errorMensaje + '\'' +
                '}';
    }
}
