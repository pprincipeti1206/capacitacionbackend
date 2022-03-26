package com.incloud.hcp.service.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class PrefacturaDto {
    private String sociedad;
    private String proveedorRuc;
    private String proveedorRazonSocial;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaEmision;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaContabilizacion;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaBase;
    private String referencia;
    private String observaciones;
    private String cadenaNumerosOrdenCompra;
    private String cadenaNumerosGuia;
    private String codigoMoneda;
    private String subTotal; // bigdecimal (2 decimales)
    private String igv; // bigdecimal (2 decimales)
    private String otrosCargo; // bigdecimal (2 decimales)
    private String total; // bigdecimal (2 decimales)

    private List<Integer> idDocumentoAceptacionList;

    private String tipoComprobante;
    private String serieComprobante;
    private String nroComprobante;

    private String razonSocialSociedad;
    private String razonSocial;
    private String cityName;
    private String countrySubentity;
    private String countrySubentityCode;
    private String district;
    private String direccion;
    private String detraccion;
    private String concepto;
    private String fechaEmisionString;

    @JsonProperty("Sociedad")
    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    @JsonProperty("ProveedorRuc")
    public String getProveedorRuc() {
        return proveedorRuc;
    }

    public void setProveedorRuc(String proveedorRuc) {
        this.proveedorRuc = proveedorRuc;
    }

    @JsonProperty("ProveedorRazonSocial")
    public String getProveedorRazonSocial() {
        return proveedorRazonSocial;
    }

    public void setProveedorRazonSocial(String proveedorRazonSocial) {
        this.proveedorRazonSocial = proveedorRazonSocial;
    }

    @JsonProperty("FechaEmision")
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @JsonProperty("FechaContabilizacion")
    public Date getFechaContabilizacion() {
        return fechaContabilizacion;
    }

    public void setFechaContabilizacion(Date fechaContabilizacion) {
        this.fechaContabilizacion = fechaContabilizacion;
    }

    @JsonProperty("FechaBase")
    public Date getFechaBase() {
        return fechaBase;
    }

    public void setFechaBase(Date fechaBase) {
        this.fechaBase = fechaBase;
    }

    @JsonProperty("Referencia")
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @JsonProperty("Observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonProperty("CadenaNumerosOrdenCompra")
    public String getCadenaNumerosOrdenCompra() {
        return cadenaNumerosOrdenCompra;
    }

    public void setCadenaNumerosOrdenCompra(String cadenaNumerosOrdenCompra) {
        this.cadenaNumerosOrdenCompra = cadenaNumerosOrdenCompra;
    }

    @JsonProperty("CadenaNumerosGuia")
    public String getCadenaNumerosGuia() {
        return cadenaNumerosGuia;
    }

    public void setCadenaNumerosGuia(String cadenaNumerosGuia) {
        this.cadenaNumerosGuia = cadenaNumerosGuia;
    }

    @JsonProperty("CodigoMoneda")
    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @JsonProperty("SubTotal")
    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    @JsonProperty("Igv")
    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    @JsonProperty("Total")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @JsonProperty("Concepto")
    public String getConcepto() {
        return concepto;
    }

    @JsonProperty("FechaEmisionString")
    public String getFechaEmisionString() {
        return fechaEmisionString;
    }

    public void setFechaEmisionString(String fechaEmisionString) {
        this.fechaEmisionString = fechaEmisionString;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public List<Integer> getIdDocumentoAceptacionList() {
        return idDocumentoAceptacionList;
    }

    public void setIdDocumentoAceptacionList(List<Integer> idDocumentoAceptacionList) {
        this.idDocumentoAceptacionList = idDocumentoAceptacionList;
    }


    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerieComprobante() {
        return serieComprobante;
    }

    public void setSerieComprobante(String serieComprobante) {
        this.serieComprobante = serieComprobante;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountrySubentity() {
        return countrySubentity;
    }

    public void setCountrySubentity(String countrySubentity) {
        this.countrySubentity = countrySubentity;
    }

    public String getCountrySubentityCode() {
        return countrySubentityCode;
    }

    public void setCountrySubentityCode(String countrySubentityCode) {
        this.countrySubentityCode = countrySubentityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocialSociedad() {
        return razonSocialSociedad;
    }

    public void setRazonSocialSociedad(String razonSocialSociedad) {
        this.razonSocialSociedad = razonSocialSociedad;
    }

    public String getDetraccion() {
        return detraccion;
    }

    public void setDetraccion(String detraccion) {
        this.detraccion = detraccion;
    }

    @JsonProperty("OtrosCargo")
    public String getOtrosCargo() {
        return otrosCargo;
    }

    public void setOtrosCargo(String otrosCargo) {
        this.otrosCargo = otrosCargo;
    }
}
