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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "mtr_vendedor")
//@Audited
//@AuditTable("_audi_mtr_vendedor")
public class MtrVendedor extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MtrVendedor.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private String nroDocumento;
    private String telefono;
    private String nombre;
    private String apellidoParterno;
    private String apellidoMaterno;
    private String estado;

    // Many to one
    private MtrProveedor mtrProveedor;

    @Override
    public String entityClassName() {
        return MtrVendedor.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "cer_vendedor_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_mtr_vendedor")
    @Id
    @SequenceGenerator(name = "seq_mtr_vendedor", sequenceName = "seq_mtr_vendedor", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public MtrVendedor id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [nroDocumento] ------------------------

    @Size(max = 40, message = "{message.mtrVendedor.nroDocumento.sizeMax} {max} {message.caracter}")
    @Column(name = "nro_documento", length = 40)
    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public MtrVendedor nroDocumento(String nroDocumento) {
        setNroDocumento(nroDocumento);
        return this;
    }
    // -- [telefono] ------------------------

    @Size(max = 20, message = "{message.mtrVendedor.telefono.sizeMax} {max} {message.caracter}")
    @Column(name = "telefono", length = 20)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public MtrVendedor telefono(String telefono) {
        setTelefono(telefono);
        return this;
    }
    // -- [nombre] ------------------------

    @Size(max = 80, message = "{message.mtrVendedor.nombre.sizeMax} {max} {message.caracter}")
    @Column(name = "nombre", length = 80)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MtrVendedor nombre(String nombre) {
        setNombre(nombre);
        return this;
    }
    // -- [apellidoParterno] ------------------------

    @Size(max = 80, message = "{message.mtrVendedor.apellidoParterno.sizeMax} {max} {message.caracter}")
    @Column(name = "apellido_parterno", length = 80)
    public String getApellidoParterno() {
        return apellidoParterno;
    }

    public void setApellidoParterno(String apellidoParterno) {
        this.apellidoParterno = apellidoParterno;
    }

    public MtrVendedor apellidoParterno(String apellidoParterno) {
        setApellidoParterno(apellidoParterno);
        return this;
    }
    // -- [apellidoMaterno] ------------------------

    @Size(max = 80, message = "{message.mtrVendedor.apellidoMaterno.sizeMax} {max} {message.caracter}")
    @Column(name = "apellido_materno", length = 80)
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public MtrVendedor apellidoMaterno(String apellidoMaterno) {
        setApellidoMaterno(apellidoMaterno);
        return this;
    }
    // -- [estado] ------------------------

    @Size(max = 1, message = "{message.mtrVendedor.estado.sizeMax} {max} {message.caracter}")
    @Column(name = "estado", length = 1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MtrVendedor estado(String estado) {
        setEstado(estado);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: MtrVendedor.mtrProveedor ==> MtrProveedor.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "mtr_proveedor_id", nullable = false)
    @ManyToOne
    public MtrProveedor getMtrProveedor() {
        return mtrProveedor;
    }

    /**
     * Set the {@link #mtrProveedor} without adding this MtrVendedor instance on the passed {@link #mtrProveedor}
     */
    public void setMtrProveedor(MtrProveedor mtrProveedor) {
        this.mtrProveedor = mtrProveedor;
    }

    public MtrVendedor mtrProveedor(MtrProveedor mtrProveedor) {
        setMtrProveedor(mtrProveedor);
        return this;
    }

    /**
     * Apply the default values.
     */
    public MtrVendedor withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof MtrVendedor && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this MtrVendedor instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("mtrProveedor", getMtrProveedor()) //    
                .add("nroDocumento", getNroDocumento()) //
                .add("telefono", getTelefono()) //
                .add("nombre", getNombre()) //
                .add("apellidoParterno", getApellidoParterno()) //
                .add("apellidoMaterno", getApellidoMaterno()) //
                .add("estado", getEstado()) //
                .toString();
    }
}
