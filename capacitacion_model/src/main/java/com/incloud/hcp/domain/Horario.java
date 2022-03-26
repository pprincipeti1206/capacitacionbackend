/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.incloud.hcp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "HORARIO")
//@Audited
//@AuditTable("_audi_HORARIO")
public class Horario extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Horario.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private Date fechaInicio;
    private String horaInicio;
    private String horaFin;
    private String frecuencia;
    private String modalidad;

    // Many to one
    private Curso curso;

    @Override
    public String entityClassName() {
        return Horario.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "HORARIO_ID", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_HORARIO")
    @Id
    @SequenceGenerator(name = "seq_HORARIO", sequenceName = "seq_HORARIO", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Horario id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [fechaInicio] ------------------------

    @NotNull
    @Column(name = "FECHA_INICIO", nullable = false, length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Horario fechaInicio(Date fechaInicio) {
        setFechaInicio(fechaInicio);
        return this;
    }
    // -- [horaInicio] ------------------------

    @NotEmpty(message = "{message.horario.horaInicio.requerido}")
    @Size(max = 5, message = "{message.horario.horaInicio.sizeMax} {max} {message.caracter}")
    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Horario horaInicio(String horaInicio) {
        setHoraInicio(horaInicio);
        return this;
    }
    // -- [horaFin] ------------------------

    @NotEmpty(message = "{message.horario.horaFin.requerido}")
    @Size(max = 5, message = "{message.horario.horaFin.sizeMax} {max} {message.caracter}")
    @Column(name = "HORA_FIN", nullable = false, length = 5)
    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Horario horaFin(String horaFin) {
        setHoraFin(horaFin);
        return this;
    }
    // -- [frecuencia] ------------------------

    @NotEmpty(message = "{message.horario.frecuencia.requerido}")
    @Size(max = 20, message = "{message.horario.frecuencia.sizeMax} {max} {message.caracter}")
    @Column(name = "FRECUENCIA", nullable = false, length = 20)
    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Horario frecuencia(String frecuencia) {
        setFrecuencia(frecuencia);
        return this;
    }
    // -- [modalidad] ------------------------

    @NotEmpty(message = "{message.horario.modalidad.requerido}")
    @Size(max = 20, message = "{message.horario.modalidad.sizeMax} {max} {message.caracter}")
    @Column(name = "MODALIDAD", nullable = false, length = 20)
    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Horario modalidad(String modalidad) {
        setModalidad(modalidad);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: Horario.curso ==> Curso.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "CURSO_ID", nullable = false)
    @ManyToOne
    public Curso getCurso() {
        return curso;
    }

    /**
     * Set the {@link #curso} without adding this Horario instance on the passed {@link #curso}
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Horario curso(Curso curso) {
        setCurso(curso);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Horario withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Horario && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Horario instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("fechaInicio", getFechaInicio()) //
                .add("horaInicio", getHoraInicio()) //
                .add("horaFin", getHoraFin()) //
                .add("frecuencia", getFrecuencia()) //
                .add("modalidad", getModalidad()) //
                .add("curso", getCurso()) //    
                .toString();
    }
}
