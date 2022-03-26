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

import com.google.common.base.MoreObjects;
import com.incloud.hcp.domain.Identifiable;
import com.incloud.hcp.domain.IdentifiableHashBuilder;
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
@Table(name = "proveedor_validacion_info")
public class ProveedorValidacionInfo implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ProveedorValidacionInfo.class.getName());

    // Raw attributes
    private Integer id;
    private Date fechaAprobacionRechazo;
    private String usuarioAprobacionRechazo;
    private String indAprobacionRechazo;
    private String motivoRechazo;

    // Many to one
    private Proveedor idProveedor;

    @Override
    public String entityClassName() {
        return ProveedorValidacionInfo.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "id_proveedor_validacion_info", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_proveedor_validacion_info")
    @Id
    @SequenceGenerator(name = "seq_proveedor_validacion_info", sequenceName = "seq_proveedor_validacion_info", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ProveedorValidacionInfo id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [fechaAprobacionRechazo] ------------------------

    @NotNull
    @Column(name = "fecha_aprobacion_rechazo", nullable = false, length = 29)
    @Temporal(TIMESTAMP)
    public Date getFechaAprobacionRechazo() {
        return fechaAprobacionRechazo;
    }

    public void setFechaAprobacionRechazo(Date fechaAprobacionRechazo) {
        this.fechaAprobacionRechazo = fechaAprobacionRechazo;
    }

    public ProveedorValidacionInfo fechaAprobacionRechazo(Date fechaAprobacionRechazo) {
        setFechaAprobacionRechazo(fechaAprobacionRechazo);
        return this;
    }
    // -- [usuarioAprobacionRechazo] ------------------------

    @NotEmpty
    @Size(max = 100)
    @Column(name = "usuario_aprobacion_rechazo", nullable = false, length = 100)
    public String getUsuarioAprobacionRechazo() {
        return usuarioAprobacionRechazo;
    }

    public void setUsuarioAprobacionRechazo(String usuarioAprobacionRechazo) {
        this.usuarioAprobacionRechazo = usuarioAprobacionRechazo;
    }

    public ProveedorValidacionInfo usuarioAprobacionRechazo(String usuarioAprobacionRechazo) {
        setUsuarioAprobacionRechazo(usuarioAprobacionRechazo);
        return this;
    }
    // -- [indAprobacionRechazo] ------------------------

    @NotEmpty
    @Size(max = 1)
    @Column(name = "ind_aprobacion_rechazo", nullable = false, length = 1)
    public String getIndAprobacionRechazo() {
        return indAprobacionRechazo;
    }

    public void setIndAprobacionRechazo(String indAprobacionRechazo) {
        this.indAprobacionRechazo = indAprobacionRechazo;
    }

    public ProveedorValidacionInfo indAprobacionRechazo(String indAprobacionRechazo) {
        setIndAprobacionRechazo(indAprobacionRechazo);
        return this;
    }
    // -- [motivoRechazo] ------------------------

    @Size(max = 1000)
    @Column(name = "motivo_rechazo", length = 1000)
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public ProveedorValidacionInfo motivoRechazo(String motivoRechazo) {
        setMotivoRechazo(motivoRechazo);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: ProveedorValidacionInfo.idProveedor ==> Proveedor.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "id_proveedor", nullable = false)
    @ManyToOne
    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    /**
     * Set the {@link #idProveedor} without adding this ProveedorValidacionInfo instance on the passed {@link #idProveedor}
     */
    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public ProveedorValidacionInfo idProveedor(Proveedor idProveedor) {
        setIdProveedor(idProveedor);
        return this;
    }

    /**
     * Apply the default values.
     */
    public ProveedorValidacionInfo withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof ProveedorValidacionInfo && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this ProveedorValidacionInfo instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("fechaAprobacionRechazo", getFechaAprobacionRechazo()) //
                .add("usuarioAprobacionRechazo", getUsuarioAprobacionRechazo()) //
                .add("indAprobacionRechazo", getIndAprobacionRechazo()) //
                .add("motivoRechazo", getMotivoRechazo()) //
                .toString();
    }
}