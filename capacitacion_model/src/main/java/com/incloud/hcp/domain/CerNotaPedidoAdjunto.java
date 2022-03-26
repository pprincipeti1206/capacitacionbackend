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
@Table(name = "cer_nota_pedido_adjunto")
//@Audited
//@AuditTable("_audi_cer_nota_pedido_adjunto")
public class CerNotaPedidoAdjunto extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(CerNotaPedidoAdjunto.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private String carpetaId;
    private String archivoId;
    private String archivoNombre;
    private String archivoTipo;
    private String rutaCatalogo;
    private String tipoAdjunto;
    private String adjuntoAprobador;
    private Long size;

    // Many to one
    private CerNotaPedido cerNotaPedido;

    @Override
    public String entityClassName() {
        return CerNotaPedidoAdjunto.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "cer_nota_pedido_adjunto_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_cer_nota_pedido_adjunto")
    @Id
    @SequenceGenerator(name = "seq_cer_nota_pedido_adjunto", sequenceName = "seq_cer_nota_pedido_adjunto", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public CerNotaPedidoAdjunto id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [carpetaId] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.carpetaId.requerido}")
    @Size(max = 60, message = "{message.cerNotaPedidoAdjunto.carpetaId.sizeMax} {max} {message.caracter}")
    @Column(name = "carpeta_id", nullable = false, length = 60)
    public String getCarpetaId() {
        return carpetaId;
    }

    public void setCarpetaId(String carpetaId) {
        this.carpetaId = carpetaId;
    }

    public CerNotaPedidoAdjunto carpetaId(String carpetaId) {
        setCarpetaId(carpetaId);
        return this;
    }
    // -- [archivoId] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.archivoId.requerido}")
    @Size(max = 60, message = "{message.cerNotaPedidoAdjunto.archivoId.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_id", nullable = false, length = 60)
    public String getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(String archivoId) {
        this.archivoId = archivoId;
    }

    public CerNotaPedidoAdjunto archivoId(String archivoId) {
        setArchivoId(archivoId);
        return this;
    }
    // -- [archivoNombre] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.archivoNombre.requerido}")
    @Size(max = 255, message = "{message.cerNotaPedidoAdjunto.archivoNombre.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_nombre", nullable = false)
    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public CerNotaPedidoAdjunto archivoNombre(String archivoNombre) {
        setArchivoNombre(archivoNombre);
        return this;
    }
    // -- [archivoTipo] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.archivoTipo.requerido}")
    @Size(max = 100, message = "{message.cerNotaPedidoAdjunto.archivoTipo.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_tipo", nullable = false, length = 100)
    public String getArchivoTipo() {
        return archivoTipo;
    }

    public void setArchivoTipo(String archivoTipo) {
        this.archivoTipo = archivoTipo;
    }

    public CerNotaPedidoAdjunto archivoTipo(String archivoTipo) {
        setArchivoTipo(archivoTipo);
        return this;
    }
    // -- [rutaCatalogo] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.rutaCatalogo.requerido}")
    @Size(max = 1000, message = "{message.cerNotaPedidoAdjunto.rutaCatalogo.sizeMax} {max} {message.caracter}")
    @Column(name = "ruta_catalogo", nullable = false, length = 1000)
    public String getRutaCatalogo() {
        return rutaCatalogo;
    }

    public void setRutaCatalogo(String rutaCatalogo) {
        this.rutaCatalogo = rutaCatalogo;
    }

    public CerNotaPedidoAdjunto rutaCatalogo(String rutaCatalogo) {
        setRutaCatalogo(rutaCatalogo);
        return this;
    }
    // -- [tipoAdjunto] ------------------------

    @NotEmpty(message = "{message.cerNotaPedidoAdjunto.tipoAdjunto.requerido}")
    @Size(max = 10, message = "{message.cerNotaPedidoAdjunto.tipoAdjunto.sizeMax} {max} {message.caracter}")
    @Column(name = "tipo_adjunto", nullable = false, length = 10)
    public String getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(String tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    public CerNotaPedidoAdjunto tipoAdjunto(String tipoAdjunto) {
        setTipoAdjunto(tipoAdjunto);
        return this;
    }
    // -- [adjuntoAprobador] ------------------------

    @Size(max = 1, message = "{message.cerNotaPedidoAdjunto.adjuntoAprobador.sizeMax} {max} {message.caracter}")
    @Column(name = "adjunto_aprobador", length = 1)
    public String getAdjuntoAprobador() {
        return adjuntoAprobador;
    }

    public void setAdjuntoAprobador(String adjuntoAprobador) {
        this.adjuntoAprobador = adjuntoAprobador;
    }

    public CerNotaPedidoAdjunto adjuntoAprobador(String adjuntoAprobador) {
        setAdjuntoAprobador(adjuntoAprobador);
        return this;
    }
    // -- [size] ------------------------

    @Digits(integer = 19, fraction = 0)
    @Column(name = "\"size\"", precision = 19)
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public CerNotaPedidoAdjunto size(Long size) {
        setSize(size);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: CerNotaPedidoAdjunto.cerNotaPedido ==> CerNotaPedido.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "cer_nota_pedido_id", nullable = false)
    @ManyToOne
    public CerNotaPedido getCerNotaPedido() {
        return cerNotaPedido;
    }

    /**
     * Set the {@link #cerNotaPedido} without adding this CerNotaPedidoAdjunto instance on the passed {@link #cerNotaPedido}
     */
    public void setCerNotaPedido(CerNotaPedido cerNotaPedido) {
        this.cerNotaPedido = cerNotaPedido;
    }

    public CerNotaPedidoAdjunto cerNotaPedido(CerNotaPedido cerNotaPedido) {
        setCerNotaPedido(cerNotaPedido);
        return this;
    }

    /**
     * Apply the default values.
     */
    public CerNotaPedidoAdjunto withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof CerNotaPedidoAdjunto && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this CerNotaPedidoAdjunto instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("cerNotaPedido", getCerNotaPedido()) //    
                .add("carpetaId", getCarpetaId()) //
                .add("archivoId", getArchivoId()) //
                .add("archivoNombre", getArchivoNombre()) //
                .add("archivoTipo", getArchivoTipo()) //
                .add("rutaCatalogo", getRutaCatalogo()) //
                .add("tipoAdjunto", getTipoAdjunto()) //
                .add("adjuntoAprobador", getAdjuntoAprobador()) //
                .add("size", getSize()) //
                .toString();
    }
}
