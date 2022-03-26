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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "mtr_moneda")
//@Audited
//@AuditTable("_audi_mtr_moneda")
public class MtrMoneda extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MtrMoneda.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private String sigla;
    private String monedaDescrip;
    private String descBrv;

    @Override
    public String entityClassName() {
        return MtrMoneda.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "mtr_moneda_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_mtr_moneda")
    @Id
    @SequenceGenerator(name = "seq_mtr_moneda", sequenceName = "seq_mtr_moneda", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public MtrMoneda id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [sigla] ------------------------

    @NotEmpty(message = "{message.mtrMoneda.sigla.requerido}")
    @Size(max = 10, message = "{message.mtrMoneda.sigla.sizeMax} {max} {message.caracter}")
    @Column(name = "sigla", nullable = false, length = 10)
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public MtrMoneda sigla(String sigla) {
        setSigla(sigla);
        return this;
    }
    // -- [monedaDescrip] ------------------------

    @NotEmpty(message = "{message.mtrMoneda.monedaDescrip.requerido}")
    @Size(max = 100, message = "{message.mtrMoneda.monedaDescrip.sizeMax} {max} {message.caracter}")
    @Column(name = "moneda_descrip", nullable = false, length = 100)
    public String getMonedaDescrip() {
        return monedaDescrip;
    }

    public void setMonedaDescrip(String monedaDescrip) {
        this.monedaDescrip = monedaDescrip;
    }

    public MtrMoneda monedaDescrip(String monedaDescrip) {
        setMonedaDescrip(monedaDescrip);
        return this;
    }
    // -- [descBrv] ------------------------

    @NotEmpty(message = "{message.mtrMoneda.descBrv.requerido}")
    @Size(max = 4, message = "{message.mtrMoneda.descBrv.sizeMax} {max} {message.caracter}")
    @Column(name = "desc_brv", nullable = false, length = 4)
    public String getDescBrv() {
        return descBrv;
    }

    public void setDescBrv(String descBrv) {
        this.descBrv = descBrv;
    }

    public MtrMoneda descBrv(String descBrv) {
        setDescBrv(descBrv);
        return this;
    }

    /**
     * Apply the default values.
     */
    public MtrMoneda withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof MtrMoneda && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this MtrMoneda instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("sigla", getSigla()) //
                .add("monedaDescrip", getMonedaDescrip()) //
                .add("descBrv", getDescBrv()) //
                .toString();
    }
}