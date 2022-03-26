package com.incloud.hcp.domain._gproveedor.bean;


import com.incloud.hcp.domain._gproveedor.domain.Banco;
import com.incloud.hcp.domain._gproveedor.domain.Moneda;

/**
 * Created by Administrador on 04/09/2017.
 */
public class CuentaBancaria {
    private Integer idCuenta;
    private Banco banco;
    private TipoCuenta tipoCuenta;
    private Moneda moneda;
    private String numeroCuenta;
    private String numeroCuentaCci;
    private String contacto;

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "idCuenta=" + idCuenta +
                ", banco=" + banco +
                ", tipoCuenta=" + tipoCuenta +
                ", moneda=" + moneda +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", numeroCuentaCci='" + numeroCuentaCci + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
