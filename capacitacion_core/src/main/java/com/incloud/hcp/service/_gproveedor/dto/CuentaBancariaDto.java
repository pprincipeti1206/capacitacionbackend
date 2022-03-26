package com.incloud.hcp.service._gproveedor.dto;

/**
 * Created by Administrador on 30/08/2017.
 */
public class CuentaBancariaDto {

    private Integer idBanco;
    private String banco;
    private Integer idMoneda;
    private String moneda;
    private Integer idTipoCuenta;
    private String codigoTipoCuenta;
    private String tipoCuenta;
    private String contacto;
    private String numeroCuenta;
    private String numeroCuentaCci;

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public String getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuentaCci() {
        return numeroCuentaCci;
    }

    public void setNumeroCuentaCci(String numeroCuentaCci) {
        this.numeroCuentaCci = numeroCuentaCci;
    }

    @Override
    public String toString() {
        return "CuentaBancariaDto{" +
                "idBanco=" + idBanco +
                ", banco='" + banco + '\'' +
                ", idMoneda=" + idMoneda +
                ", moneda='" + moneda + '\'' +
                ", idTipoCuenta=" + idTipoCuenta +
                ", codigoTipoCuenta='" + codigoTipoCuenta + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", contacto='" + contacto + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", numeroCuentaCci='" + numeroCuentaCci + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaBancariaDto that = (CuentaBancariaDto) o;

        if (idBanco != null ? !idBanco.equals(that.idBanco) : that.idBanco != null) return false;
        if (banco != null ? !banco.equals(that.banco) : that.banco != null) return false;
        if (idMoneda != null ? !idMoneda.equals(that.idMoneda) : that.idMoneda != null) return false;
        if (moneda != null ? !moneda.equals(that.moneda) : that.moneda != null) return false;
        if (idTipoCuenta != null ? !idTipoCuenta.equals(that.idTipoCuenta) : that.idTipoCuenta != null) return false;
        if (codigoTipoCuenta != null ? !codigoTipoCuenta.equals(that.codigoTipoCuenta) : that.codigoTipoCuenta != null)
            return false;
        if (tipoCuenta != null ? !tipoCuenta.equals(that.tipoCuenta) : that.tipoCuenta != null) return false;
        if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;
        if (numeroCuenta != null ? !numeroCuenta.equals(that.numeroCuenta) : that.numeroCuenta != null) return false;
        return numeroCuentaCci != null ? numeroCuentaCci.equals(that.numeroCuentaCci) : that.numeroCuentaCci == null;
    }

    @Override
    public int hashCode() {
        int result = idBanco != null ? idBanco.hashCode() : 0;
        result = 31 * result + (banco != null ? banco.hashCode() : 0);
        result = 31 * result + (idMoneda != null ? idMoneda.hashCode() : 0);
        result = 31 * result + (moneda != null ? moneda.hashCode() : 0);
        result = 31 * result + (idTipoCuenta != null ? idTipoCuenta.hashCode() : 0);
        result = 31 * result + (codigoTipoCuenta != null ? codigoTipoCuenta.hashCode() : 0);
        result = 31 * result + (tipoCuenta != null ? tipoCuenta.hashCode() : 0);
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        result = 31 * result + (numeroCuenta != null ? numeroCuenta.hashCode() : 0);
        result = 31 * result + (numeroCuentaCci != null ? numeroCuentaCci.hashCode() : 0);
        return result;
    }
}
