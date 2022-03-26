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

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "mtr_tasa_cambio_web", uniqueConstraints = {
        @UniqueConstraint(name = "mtr_tasa_cambio_web_ak01", columnNames = { "fecha_tasa", "mtr_moneda_origen_id", "mtr_moneda_destino_id" }) })
//@Audited
//@AuditTable("_audi_mtr_tasa_cambio_web")
public class MtrTasaCambioWeb extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MtrTasaCambioWeb.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private Date fechaTasa;
    private Integer mtrMonedaOrigenId;
    private Integer mtrMonedaDestinoId;
    private BigDecimal valor;

    @Override
    public String entityClassName() {
        return MtrTasaCambioWeb.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "mtr_tasa_cambio_web_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_mtr_tasa_cambio_web")
    @Id
    @SequenceGenerator(name = "seq_mtr_tasa_cambio_web", sequenceName = "seq_mtr_tasa_cambio_web", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public MtrTasaCambioWeb id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [fechaTasa] ------------------------

    @NotNull
    @Column(name = "fecha_tasa", nullable = false, length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaTasa() {
        return fechaTasa;
    }

    public void setFechaTasa(Date fechaTasa) {
        this.fechaTasa = fechaTasa;
    }

    public MtrTasaCambioWeb fechaTasa(Date fechaTasa) {
        setFechaTasa(fechaTasa);
        return this;
    }
    // -- [mtrMonedaOrigenId] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "mtr_moneda_origen_id", nullable = false, precision = 10)
    public Integer getMtrMonedaOrigenId() {
        return mtrMonedaOrigenId;
    }

    public void setMtrMonedaOrigenId(Integer mtrMonedaOrigenId) {
        this.mtrMonedaOrigenId = mtrMonedaOrigenId;
    }

    public MtrTasaCambioWeb mtrMonedaOrigenId(Integer mtrMonedaOrigenId) {
        setMtrMonedaOrigenId(mtrMonedaOrigenId);
        return this;
    }
    // -- [mtrMonedaDestinoId] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "mtr_moneda_destino_id", nullable = false, precision = 10)
    public Integer getMtrMonedaDestinoId() {
        return mtrMonedaDestinoId;
    }

    public void setMtrMonedaDestinoId(Integer mtrMonedaDestinoId) {
        this.mtrMonedaDestinoId = mtrMonedaDestinoId;
    }

    public MtrTasaCambioWeb mtrMonedaDestinoId(Integer mtrMonedaDestinoId) {
        setMtrMonedaDestinoId(mtrMonedaDestinoId);
        return this;
    }
    // -- [valor] ------------------------

    @Digits(integer = 14, fraction = 4)
    @NotNull
    @Column(name = "valor", nullable = false, precision = 18, scale = 4)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public MtrTasaCambioWeb valor(BigDecimal valor) {
        setValor(valor);
        return this;
    }

    /**
     * Apply the default values.
     */
    public MtrTasaCambioWeb withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof MtrTasaCambioWeb && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this MtrTasaCambioWeb instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("fechaTasa", getFechaTasa()) //
                .add("mtrMonedaOrigenId", getMtrMonedaOrigenId()) //
                .add("mtrMonedaDestinoId", getMtrMonedaDestinoId()) //
                .add("valor", getValor()) //
                .toString();
    }
}