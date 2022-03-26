package com.incloud.hcp.domain._gproveedor.domain;

import com.incloud.hcp.domain._gproveedor._framework.BaseDomainGProveedor;
import com.incloud.hcp.validation.FixedLength;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the proveedor_cuenta_bancaria database table.
 * 
 */
@Entity
@Table(name="proveedor_cuenta_bancaria")
public class ProveedorCuentaBancaria extends BaseDomainGProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cuenta", unique=true, nullable=false)
	@GeneratedValue(generator = "proveedor_cuenta_bancaria_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "proveedor_cuenta_bancaria_id_seq", sequenceName = "proveedor_cuenta_bancaria_id_seq", allocationSize = 1)
	private Integer idCuenta;

	@Column(name="clave_control_banco", nullable=false, length=2)
	private String claveControlBanco;

	@Column(length=100)
	private String contacto;

	@Column(name="numero_cuenta", nullable=false, length=18)
	private String numeroCuenta;

	@Column(name="numero_cuenta_cci", length=20)
	private String numeroCuentaCci;

	private String indCuentaDetraccion;

	@Transient
	private Integer idTipoCuenta;

	@Transient
	private String codigoTipoCuenta;

	@Transient
	private String descripcionTipoCuenta;

	//uni-directional many-to-one association to Banco
	@ManyToOne
	@JoinColumn(name="id_banco", nullable=false)
	private Banco banco;

	//uni-directional many-to-one association to Moneda
	@ManyToOne
	@JoinColumn(name="id_moneda", nullable=false)
	private Moneda moneda;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor", nullable=false)
	private Proveedor proveedor;

	public ProveedorCuentaBancaria() {
	}

	public Integer getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getClaveControlBanco() {
		return this.claveControlBanco;
	}

	public void setClaveControlBanco(String claveControlBanco) {
		this.claveControlBanco = claveControlBanco;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroCuentaCci() {
		return this.numeroCuentaCci;
	}

	public void setNumeroCuentaCci(String numeroCuentaCci) {
		this.numeroCuentaCci = numeroCuentaCci;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	// -- [indCuentaDetraccion] ------------------------

	@FixedLength(length = 1)
	@Column(name = "ind_cuenta_detraccion", length = 1)
	public String getIndCuentaDetraccion() {
		return indCuentaDetraccion;
	}

	public void setIndCuentaDetraccion(String indCuentaDetraccion) {
		this.indCuentaDetraccion = indCuentaDetraccion;
	}

	public ProveedorCuentaBancaria indCuentaDetraccion(String indCuentaDetraccion) {
		setIndCuentaDetraccion(indCuentaDetraccion);
		return this;
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

	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	@Override
	public String toString() {
		return "ProveedorCuentaBancaria{" +
				"idCuenta=" + idCuenta +
				", claveControlBanco='" + claveControlBanco + '\'' +
				", contacto='" + contacto + '\'' +
				", numeroCuenta='" + numeroCuenta + '\'' +
				", numeroCuentaCci='" + numeroCuentaCci + '\'' +
				", indCuentaDetraccion='" + indCuentaDetraccion + '\'' +
				", banco=" + banco +
				", moneda=" + moneda +
				", proveedor=" + proveedor +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ProveedorCuentaBancaria that = (ProveedorCuentaBancaria) o;

		if (idCuenta != null ? !idCuenta.equals(that.idCuenta) : that.idCuenta != null) return false;
		if (claveControlBanco != null ? !claveControlBanco.equals(that.claveControlBanco) : that.claveControlBanco != null)
			return false;
		if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;
		if (numeroCuenta != null ? !numeroCuenta.equals(that.numeroCuenta) : that.numeroCuenta != null) return false;
		if (numeroCuentaCci != null ? !numeroCuentaCci.equals(that.numeroCuentaCci) : that.numeroCuentaCci != null)
			return false;
		if (indCuentaDetraccion != null ? !indCuentaDetraccion.equals(that.indCuentaDetraccion) : that.indCuentaDetraccion != null)
			return false;
		if (banco != null ? !banco.equals(that.banco) : that.banco != null) return false;
		if (moneda != null ? !moneda.equals(that.moneda) : that.moneda != null) return false;
		return proveedor != null ? proveedor.equals(that.proveedor) : that.proveedor == null;
	}

	@Override
	public int hashCode() {
		int result = idCuenta != null ? idCuenta.hashCode() : 0;
		result = 31 * result + (claveControlBanco != null ? claveControlBanco.hashCode() : 0);
		result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
		result = 31 * result + (numeroCuenta != null ? numeroCuenta.hashCode() : 0);
		result = 31 * result + (numeroCuentaCci != null ? numeroCuentaCci.hashCode() : 0);
		result = 31 * result + (indCuentaDetraccion != null ? indCuentaDetraccion.hashCode() : 0);
		result = 31 * result + (banco != null ? banco.hashCode() : 0);
		result = 31 * result + (moneda != null ? moneda.hashCode() : 0);
		result = 31 * result + (proveedor != null ? proveedor.hashCode() : 0);
		return result;
	}
}