package com.incloud.hcp.domain._gproveedor.domain;

import com.incloud.hcp.domain._gproveedor._framework.BaseDomainGProveedor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * The persistent class for the tipo_comprobante database table.
 * 
 */
@Entity
@Table(name="tipo_comprobante")
public class TipoComprobante extends BaseDomainGProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_comprobante", unique=true, nullable=false)
	@GeneratedValue(generator = "tipo_comprobante_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "tipo_comprobante_id_seq", sequenceName = "tipo_comprobante_id_seq", allocationSize = 1)
	private Integer idTipoComprobante;

	@Column(nullable=false, length=30)
	private String descripcion;

	private String codigoTipoComprobante;


	public TipoComprobante() {
	}

	public Integer getIdTipoComprobante() {
		return this.idTipoComprobante;
	}

	public void setIdTipoComprobante(Integer idTipoComprobante) {
		this.idTipoComprobante = idTipoComprobante;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// -- [codigoTipoComprobante] ------------------------

	@NotEmpty
	@Size(max = 3)
	@Column(name = "codigo_tipo_comprobante", nullable = false, length = 3)
	public String getCodigoTipoComprobante() {
		return codigoTipoComprobante;
	}

	public void setCodigoTipoComprobante(String codigoTipoComprobante) {
		this.codigoTipoComprobante = codigoTipoComprobante;
	}

	public TipoComprobante codigoTipoComprobante(String codigoTipoComprobante) {
		setCodigoTipoComprobante(codigoTipoComprobante);
		return this;
	}

	@Override
	public String toString() {
		return "TipoComprobante{" +
				"idTipoComprobante=" + idTipoComprobante +
				", descripcion='" + descripcion + '\'' +
				", codigoTipoComprobante='" + codigoTipoComprobante + '\'' +
				'}';
	}
}