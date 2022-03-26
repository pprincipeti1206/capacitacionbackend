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

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "PARTICIPANTE")
//@Audited
//@AuditTable("_audi_PARTICIPANTE")
public class Participante extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Participante.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private String nombre;
    private String apellido;
    private String sexo;
    private Integer edad;
    private String profesion;
    private String rol;

    @Override
    public String entityClassName() {
        return Participante.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "PARTICIPANTE_ID", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_PARTICIPANTE")
    @Id
    @SequenceGenerator(name = "seq_PARTICIPANTE", sequenceName = "seq_PARTICIPANTE", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Participante id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [nombre] ------------------------

    @NotEmpty(message = "{message.participante.nombre.requerido}")
    @Size(max = 50, message = "{message.participante.nombre.sizeMax} {max} {message.caracter}")
    @Column(name = "NOMBRE", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Participante nombre(String nombre) {
        setNombre(nombre);
        return this;
    }
    // -- [apellido] ------------------------

    @NotEmpty(message = "{message.participante.apellido.requerido}")
    @Size(max = 150, message = "{message.participante.apellido.sizeMax} {max} {message.caracter}")
    @Column(name = "APELLIDO", nullable = false, length = 150)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Participante apellido(String apellido) {
        setApellido(apellido);
        return this;
    }
    // -- [sexo] ------------------------

    @NotEmpty(message = "{message.participante.sexo.requerido}")
    @Size(max = 1, message = "{message.participante.sexo.sizeMax} {max} {message.caracter}")
    @Column(name = "SEXO", nullable = false, length = 1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Participante sexo(String sexo) {
        setSexo(sexo);
        return this;
    }
    // -- [edad] ------------------------

    @Digits(integer = 5, fraction = 0)
    @NotNull
    @Column(name = "EDAD", nullable = false, precision = 5)
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Participante edad(Integer edad) {
        setEdad(edad);
        return this;
    }
    // -- [profesion] ------------------------

    @Size(max = 50, message = "{message.participante.profesion.sizeMax} {max} {message.caracter}")
    @Column(name = "PROFESION", length = 50)
    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Participante profesion(String profesion) {
        setProfesion(profesion);
        return this;
    }
    // -- [rol] ------------------------

    @NotEmpty(message = "{message.participante.rol.requerido}")
    @Size(max = 1, message = "{message.participante.rol.sizeMax} {max} {message.caracter}")
    @Column(name = "ROL", nullable = false, length = 1)
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Participante rol(String rol) {
        setRol(rol);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Participante withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Participante && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Participante instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("nombre", getNombre()) //
                .add("apellido", getApellido()) //
                .add("sexo", getSexo()) //
                .add("edad", getEdad()) //
                .add("profesion", getProfesion()) //
                .add("rol", getRol()) //
                .toString();
    }
}
