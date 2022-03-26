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

/**
 * Centificado Documento
 */
@Entity
@Table(name = "fac_documento_adjunto")
//@Audited
//@AuditTable("_audi_fac_documento_adjunto")
public class FacDocumentoAdjunto extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(FacDocumentoAdjunto.class.getName());

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
    private MtrUsuarioFacturacion mtrUsuarioCupa;
    private MtrUsuarioFacturacion mtrUsuarioAprobador0;
    private FacFactura facFactura;

    @Override
    public String entityClassName() {
        return FacDocumentoAdjunto.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "fac_documento_adjunto_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_fac_documento_adjunto")
    @Id
    @SequenceGenerator(name = "seq_fac_documento_adjunto", sequenceName = "seq_fac_documento_adjunto", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public FacDocumentoAdjunto id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [carpetaId] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.carpetaId.requerido}")
    @Size(max = 60, message = "{message.facDocumentoAdjunto.carpetaId.sizeMax} {max} {message.caracter}")
    @Column(name = "carpeta_id", nullable = false, length = 60)
    public String getCarpetaId() {
        return carpetaId;
    }

    public void setCarpetaId(String carpetaId) {
        this.carpetaId = carpetaId;
    }

    public FacDocumentoAdjunto carpetaId(String carpetaId) {
        setCarpetaId(carpetaId);
        return this;
    }
    // -- [archivoId] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.archivoId.requerido}")
    @Size(max = 60, message = "{message.facDocumentoAdjunto.archivoId.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_id", nullable = false, length = 60)
    public String getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(String archivoId) {
        this.archivoId = archivoId;
    }

    public FacDocumentoAdjunto archivoId(String archivoId) {
        setArchivoId(archivoId);
        return this;
    }
    // -- [archivoNombre] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.archivoNombre.requerido}")
    @Size(max = 255, message = "{message.facDocumentoAdjunto.archivoNombre.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_nombre", nullable = false)
    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public FacDocumentoAdjunto archivoNombre(String archivoNombre) {
        setArchivoNombre(archivoNombre);
        return this;
    }
    // -- [archivoTipo] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.archivoTipo.requerido}")
    @Size(max = 100, message = "{message.facDocumentoAdjunto.archivoTipo.sizeMax} {max} {message.caracter}")
    @Column(name = "archivo_tipo", nullable = false, length = 100)
    public String getArchivoTipo() {
        return archivoTipo;
    }

    public void setArchivoTipo(String archivoTipo) {
        this.archivoTipo = archivoTipo;
    }

    public FacDocumentoAdjunto archivoTipo(String archivoTipo) {
        setArchivoTipo(archivoTipo);
        return this;
    }
    // -- [rutaCatalogo] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.rutaCatalogo.requerido}")
    @Size(max = 1000, message = "{message.facDocumentoAdjunto.rutaCatalogo.sizeMax} {max} {message.caracter}")
    @Column(name = "ruta_catalogo", nullable = false, length = 1000)
    public String getRutaCatalogo() {
        return rutaCatalogo;
    }

    public void setRutaCatalogo(String rutaCatalogo) {
        this.rutaCatalogo = rutaCatalogo;
    }

    public FacDocumentoAdjunto rutaCatalogo(String rutaCatalogo) {
        setRutaCatalogo(rutaCatalogo);
        return this;
    }
    // -- [tipoAdjunto] ------------------------

    @NotEmpty(message = "{message.facDocumentoAdjunto.tipoAdjunto.requerido}")
    @Size(max = 10, message = "{message.facDocumentoAdjunto.tipoAdjunto.sizeMax} {max} {message.caracter}")
    @Column(name = "tipo_adjunto", nullable = false, length = 10)
    public String getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(String tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    public FacDocumentoAdjunto tipoAdjunto(String tipoAdjunto) {
        setTipoAdjunto(tipoAdjunto);
        return this;
    }
    // -- [adjuntoAprobador] ------------------------

    @Size(max = 1, message = "{message.facDocumentoAdjunto.adjuntoAprobador.sizeMax} {max} {message.caracter}")
    @Column(name = "adjunto_aprobador", length = 1)
    public String getAdjuntoAprobador() {
        return adjuntoAprobador;
    }

    public void setAdjuntoAprobador(String adjuntoAprobador) {
        this.adjuntoAprobador = adjuntoAprobador;
    }

    public FacDocumentoAdjunto adjuntoAprobador(String adjuntoAprobador) {
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

    public FacDocumentoAdjunto size(Long size) {
        setSize(size);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacDocumentoAdjunto.mtrUsuarioCupa ==> MtrUsuarioFacturacion.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "mtr_usuario_aprobacion0_id")
    @ManyToOne
    public MtrUsuarioFacturacion getMtrUsuarioAprobador0() {
        return mtrUsuarioAprobador0;
    }

    /**
     * Set the {@link #mtrUsuarioAprobador0} without adding this FacDocumentoAdjunto instance on the passed {@link #mtrUsuarioAprobador0}
     */
    public void setMtrUsuarioAprobador0(MtrUsuarioFacturacion mtrUsuarioAprobador0) {
        this.mtrUsuarioAprobador0 = mtrUsuarioAprobador0;
    }

    public FacDocumentoAdjunto mtrUsuarioAprobador0(MtrUsuarioFacturacion mtrUsuarioAprobador0) {
        setMtrUsuarioAprobador0(mtrUsuarioAprobador0);
        return this;
    }

    @JoinColumn(name = "mtr_usuario_cupa_id")
    @ManyToOne
    public MtrUsuarioFacturacion getMtrUsuarioCupa() {
        return mtrUsuarioCupa;
    }

    /**
     * Set the {@link #mtrUsuarioCupa} without adding this FacDocumentoAdjunto instance on the passed {@link #mtrUsuarioCupa}
     */
    public void setMtrUsuarioCupa(MtrUsuarioFacturacion mtrUsuarioCupa) {
        this.mtrUsuarioCupa = mtrUsuarioCupa;
    }

    public FacDocumentoAdjunto mtrUsuarioCupa(MtrUsuarioFacturacion mtrUsuarioCupa) {
        setMtrUsuarioCupa(mtrUsuarioCupa);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacDocumentoAdjunto.facFactura ==> FacFactura.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "fac_factura_id", nullable = false)
    @ManyToOne
    public FacFactura getFacFactura() {
        return facFactura;
    }

    /**
     * Set the {@link #facFactura} without adding this FacDocumentoAdjunto instance on the passed {@link #facFactura}
     */
    public void setFacFactura(FacFactura facFactura) {
        this.facFactura = facFactura;
    }

    public FacDocumentoAdjunto facFactura(FacFactura facFactura) {
        setFacFactura(facFactura);
        return this;
    }

    /**
     * Apply the default values.
     */
    public FacDocumentoAdjunto withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof FacDocumentoAdjunto && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this FacDocumentoAdjunto instance.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("facFactura", getFacFactura()) //    
                .add("carpetaId", getCarpetaId()) //
                .add("archivoId", getArchivoId()) //
                .add("archivoNombre", getArchivoNombre()) //
                .add("archivoTipo", getArchivoTipo()) //
                .add("rutaCatalogo", getRutaCatalogo()) //
                .add("tipoAdjunto", getTipoAdjunto()) //
                .add("adjuntoAprobador", getAdjuntoAprobador()) //
                .add("size", getSize()) //
                .add("mtrUsuarioCupa", getMtrUsuarioCupa()) //    
                .toString();
    }
}
