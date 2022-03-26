package com.incloud.hcp.service._framework.bean;

import java.util.List;

public class BeanListaMasivoDTO<L extends BeanCargaMasivoDTO> {

    private List<L> listaDTO;
    private boolean ejecucionOK = true;

    public List<L> getListaDTO() {
        return listaDTO;
    }

    public void setListaDTO(List<L> listaDTO) {
        this.listaDTO = listaDTO;
    }

    public boolean isEjecucionOK() {
        return ejecucionOK;
    }

    public void setEjecucionOK(boolean ejecucionOK) {
        this.ejecucionOK = ejecucionOK;
    }

    @Override
    public String toString() {
        return "BeanListaMasivoDTO{" +
                "listaDTO=" + listaDTO +
                ", ejecucionOK=" + ejecucionOK +
                '}';
    }
}
