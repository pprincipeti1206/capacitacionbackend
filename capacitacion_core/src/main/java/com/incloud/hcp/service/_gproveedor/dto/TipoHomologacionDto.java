package com.incloud.hcp.service._gproveedor.dto;


import com.incloud.hcp.enums._gproveedor.TipoHomologacionEnum;

public class TipoHomologacionDto {
    private TipoHomologacionEnum descripcion;
    private boolean seleccionado;

    public TipoHomologacionDto() {
    }

    public TipoHomologacionDto(TipoHomologacionEnum descripcion, boolean seleccionado) {
        this.descripcion = descripcion;
        this.seleccionado = seleccionado;
    }

    public TipoHomologacionEnum getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TipoHomologacionEnum descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
