/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.incloud.hcp.domain._gproveedor.domain;

import com.incloud.hcp.domain.Identifiable;
import com.incloud.hcp.domain._gproveedor._framework.BaseDomainGProveedor;
import com.incloud.hcp.utils.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Optional;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "pregunta_informacion")
public class PreguntaInformacion extends BaseDomainGProveedor implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(PreguntaInformacion.class.getName());

    // Raw attributes

    @Column(name = "id_pregunta_informacion", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_pregunta_informacion")
    @Id
    @SequenceGenerator(name = "seq_pregunta_informacion", sequenceName = "seq_pregunta_informacion", allocationSize = 1)
    private Integer id;

    @NotEmpty
    @Size(max = 200)
    @Column(name = "descripcion_pregunta", nullable = false, length = 200)
    private String descripcionPregunta;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "orden", nullable = false, precision = 10)
    private Integer orden;

    @Size(max = 1)
    @Column(name = "ind_respuesta_si_no", nullable = false, length = 1)
    private String indRespuestaSiNo;

    @Transient
    private boolean respuestaSiNo;

    @Override
    public String entityClassName() {
        return PreguntaInformacion.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public PreguntaInformacion id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [descripcionPregunta] ------------------------


    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }

    public PreguntaInformacion descripcionPregunta(String descripcionPregunta) {
        setDescripcionPregunta(descripcionPregunta);
        return this;
    }
    // -- [orden] ------------------------


    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public PreguntaInformacion orden(Integer orden) {
        setOrden(orden);
        return this;
    }

    // -- [indRespuestaSiNo] ------------------------


    public String getIndRespuestaSiNo() {
        return indRespuestaSiNo;
    }

    public void setIndRespuestaSiNo(String indRespuestaSiNo) {
        this.indRespuestaSiNo = indRespuestaSiNo;
    }

    public PreguntaInformacion indRespuestaSiNo(String indRespuestaSiNo) {
        setIndRespuestaSiNo(indRespuestaSiNo);
        return this;
    }

    /**
     * Apply the default values.
     */
    public PreguntaInformacion withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof PreguntaInformacion && hashCode() == other.hashCode());
    }



    public boolean isRespuestaSiNo() {
        this.respuestaSiNo = false;
        if (Optional.ofNullable(this.indRespuestaSiNo).isPresent()) {
            if (this.indRespuestaSiNo.equals(Constants.S)) {
                this.respuestaSiNo = true;
            }
        }
        return respuestaSiNo;
    }

    public void setRespuestaSiNo(boolean respuestaSiNo) {
        this.respuestaSiNo = respuestaSiNo;
    }

    @Override
    public String toString() {
        return "PreguntaInformacion{" +
                "id=" + id +
                ", descripcionPregunta='" + descripcionPregunta + '\'' +
                ", orden=" + orden +
                ", indRespuestaSiNo='" + indRespuestaSiNo + '\'' +
                ", respuestaSiNo=" + respuestaSiNo +
                '}';
    }
}