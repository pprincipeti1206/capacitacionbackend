package com.incloud.hcp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name="config_notificacion")
public class ConfigNotificacion extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_config_notificacion", unique=true, nullable=false )
	@GeneratedValue(generator = "config_notificacion_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "config_notificacion_id_seq", sequenceName = "config_notificacion_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name="denominacion", nullable=false, length = 500 )
	private String denominacion;

	@Column(name="mensaje", nullable=false, length = 5000 )
	private String mensaje;

	@Column(name="fecha_inicio_vigencia", nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp fechaInicioVigencia;

	/**
	 * En caso la fecha fin de vigencia esté nulo significa que la notificación no tiene fecha fin de vigencia definida
	 * */

	@Column(name="fecha_fin_vigencia")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp fechaFinVigencia;

	@Override
	@Transient
	public boolean isIdSet() {
		return Objects.nonNull( id );
	}

	/**
	 * Atributos para auditoría
	 * */

	/*@Column(name="fecha_creacion", nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion", nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp fechaModificacion;

	@Column(name ="usuario_creacion", nullable=false )
	private Integer usuarioCreacion;

	@Column(name="usuario_modificacion")
	private Integer usuarioModificacion;*/

}