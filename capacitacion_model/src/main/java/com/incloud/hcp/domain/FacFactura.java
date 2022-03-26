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
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "fac_factura", uniqueConstraints = {
        @UniqueConstraint(name = "fac_factura_ak01", columnNames = { "serie_factura", "numero_factura", "mtr_proveedor_id", "tipo_factura" }) })
//@Audited
//@AuditTable("_audi_fac_factura")
public class FacFactura extends BaseDomain implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(FacFactura.class.getName());

    /***************************/
    /* Atributos de la Entidad */
    /***************************/

    // Raw attributes
    private Integer id;
    private String serieFactura;
    private String numeroFactura;
    private String tipoFactura;
    private String tipoComprobanteFactura;
    private BigDecimal monto;
    private BigDecimal montoOtrosCargos;
    private BigDecimal montoIgv;
    private BigDecimal montoFinal;
    private String glosa;
    private Date fechaFactura;
    private Date fechaEmision;
    private Date fechaCreacion;
    private String existeContrato;
    private String estadoRetornoSunat;
    private String detalleServicio;
    private String indicadorConCertificado;
    private String concepto;
    private String codigoCorrelativoAdjunto;
    private String estado;
    private Date fechaEstimadaPago;
    private Date fechaPago;
    private Date fechaAprobacion;
    private Integer nroIteracion;
    private Date fechaPosibleVencimiento;
    private String indAprobacionRechazoFinal;
    private String indEstadoAdjunto;
    private String documentoPagoSap;
    private String numeroFacturaSap;
    private String anioFacturaSap;

    private String numeroFacturaSapPrelim;
    private String anioFacturaSapPrelim;

    // Many to one
    private MtrEstado mtrEstado;
    private MtrMoneda mtrMoneda;
    private MtrDetraccion mtrDetraccion;
    private MtrProveedor mtrProveedor;
    private MtrSociedad mtrSociedad;

    //Transient
    private String fechaEmisionIni;
    private String fechaEmisionFin;
    private String fechaPagoIni;
    private String fechaPagoFin;
    private String fechaAprobacionIni;
    private String fechaAprobacionFin;
    private String fechaCreacionIni;
    private String fechaCreacionFin;
    private String fechaEstimadaPagoIni;
    private String fechaEstimadaPagoFin;
    private String fechaFacturaIni;
    private String fechaFacturaFin;
    private String estadoValidacionAdjunto;
    private List<Integer> mtrEstadoLista;

    private String esProveedorExtranjero;
    private List<String> listaCodigoAcreedor;

    @Transient
    public List<String> getListaCodigoAcreedor() {
        return listaCodigoAcreedor;
    }

    public void setListaCodigoAcreedor(List<String> listaCodigoAcreedor) {
        this.listaCodigoAcreedor = listaCodigoAcreedor;
    }

    @Transient
    public String getEsProveedorExtranjero() {
        return esProveedorExtranjero;
    }

    public void setEsProveedorExtranjero(String esProveedorExtranjero) {
        this.esProveedorExtranjero = esProveedorExtranjero;
    }

    @Transient
    public String getFechaEmisionIni() {
        return fechaEmisionIni;
    }

    public void setFechaEmisionIni(String fechaEmisionIni) {
        this.fechaEmisionIni = fechaEmisionIni;
    }

    @Transient
    public String getFechaEmisionFin() {
        return fechaEmisionFin;
    }

    public void setFechaEmisionFin(String fechaEmisionFin) {
        this.fechaEmisionFin = fechaEmisionFin;
    }

    @Transient
    public String getFechaPagoIni() {
        return fechaPagoIni;
    }

    public void setFechaPagoIni(String fechaPagoIni) {
        this.fechaPagoIni = fechaPagoIni;
    }

    @Transient
    public String getFechaPagoFin() {
        return fechaPagoFin;
    }

    public void setFechaPagoFin(String fechaPagoFin) {
        this.fechaPagoFin = fechaPagoFin;
    }

    @Transient
    public String getFechaAprobacionIni() {
        return fechaAprobacionIni;
    }

    public void setFechaAprobacionIni(String fechaAprobacionIni) {
        this.fechaAprobacionIni = fechaAprobacionIni;
    }

    @Transient
    public String getFechaAprobacionFin() {
        return fechaAprobacionFin;
    }

    public void setFechaAprobacionFin(String fechaAprobacionFin) {
        this.fechaAprobacionFin = fechaAprobacionFin;
    }

    @Transient
    public String getFechaCreacionIni() {
        return fechaCreacionIni;
    }

    public void setFechaCreacionIni(String fechaCreacionIni) {
        this.fechaCreacionIni = fechaCreacionIni;
    }

    @Transient
    public String getFechaCreacionFin() {
        return fechaCreacionFin;
    }

    public void setFechaCreacionFin(String fechaCreacionFin) {
        this.fechaCreacionFin = fechaCreacionFin;
    }

    @Transient
    public String getFechaEstimadaPagoIni() {
        return fechaEstimadaPagoIni;
    }

    public void setFechaEstimadaPagoIni(String fechaEstimadaPagoIni) {
        this.fechaEstimadaPagoIni = fechaEstimadaPagoIni;
    }

    @Transient
    public String getFechaEstimadaPagoFin() {
        return fechaEstimadaPagoFin;
    }

    public void setFechaEstimadaPagoFin(String fechaEstimadaPagoFin) {
        this.fechaEstimadaPagoFin = fechaEstimadaPagoFin;
    }

    @Transient
    public List<Integer> getMtrEstadoLista() {
        return mtrEstadoLista;
    }

    public void setMtrEstadoLista(List<Integer> mtrEstadoLista) {
        this.mtrEstadoLista = mtrEstadoLista;
    }

    @Transient
    public String getEstadoValidacionAdjunto() {
        return estadoValidacionAdjunto;
    }

    public void setEstadoValidacionAdjunto(String estadoValidacionAdjunto) {
        this.estadoValidacionAdjunto = estadoValidacionAdjunto;
    }

    @Transient
    public String getFechaFacturaIni() {
        return fechaFacturaIni;
    }

    public void setFechaFacturaIni(String fechaFacturaIni) {
        this.fechaFacturaIni = fechaFacturaIni;
    }

    @Transient
    public String getFechaFacturaFin() {
        return fechaFacturaFin;
    }

    public void setFechaFacturaFin(String fechaFacturaFin) {
        this.fechaFacturaFin = fechaFacturaFin;
    }

    @Override
    public String entityClassName() {
        return FacFactura.class.getSimpleName();
    }




    // -- [id] ------------------------

    @Override
    @Column(name = "fac_factura_id", precision = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_fac_factura")
    @Id
    @SequenceGenerator(name = "seq_fac_factura", sequenceName = "seq_fac_factura", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public FacFactura id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [serieFactura] ------------------------

    @NotEmpty(message = "{message.facFactura.serieFactura.requerido}")
    @Size(max = 10, message = "{message.facFactura.serieFactura.sizeMax} {max} {message.caracter}")
    @Column(name = "serie_factura", nullable = false, length = 10)
    public String getSerieFactura() {
        return serieFactura;
    }

    public void setSerieFactura(String serieFactura) {
        this.serieFactura = serieFactura;
    }

    public FacFactura serieFactura(String serieFactura) {
        setSerieFactura(serieFactura);
        return this;
    }
    // -- [numeroFactura] ------------------------

    @NotEmpty(message = "{message.facFactura.numeroFactura.requerido}")
    @Size(max = 30, message = "{message.facFactura.numeroFactura.sizeMax} {max} {message.caracter}")
    @Column(name = "numero_factura", nullable = false, length = 30)
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public FacFactura numeroFactura(String numeroFactura) {
        setNumeroFactura(numeroFactura);
        return this;
    }
    // -- [tipoFactura] ------------------------

    @NotEmpty(message = "{message.facFactura.tipoFactura.requerido}")
    @Size(max = 1, message = "{message.facFactura.tipoFactura.sizeMax} {max} {message.caracter}")
    @Column(name = "tipo_factura", nullable = false, length = 1)
    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public FacFactura tipoFactura(String tipoFactura) {
        setTipoFactura(tipoFactura);
        return this;
    }
    // -- [tipoComprobanteFactura] ------------------------

    @NotEmpty(message = "{message.facFactura.tipoComprobanteFactura.requerido}")
    @Size(max = 2, message = "{message.facFactura.tipoComprobanteFactura.sizeMax} {max} {message.caracter}")
    @Column(name = "tipo_comprobante_factura", nullable = false, length = 2)
    public String getTipoComprobanteFactura() {
        return tipoComprobanteFactura;
    }

    public void setTipoComprobanteFactura(String tipoComprobanteFactura) {
        this.tipoComprobanteFactura = tipoComprobanteFactura;
    }

    public FacFactura tipoComprobanteFactura(String tipoComprobanteFactura) {
        setTipoComprobanteFactura(tipoComprobanteFactura);
        return this;
    }
    // -- [monto] ------------------------

    @Digits(integer = 17, fraction = 2)
    @NotNull
    @Column(name = "monto", nullable = false, precision = 19, scale = 2)
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public FacFactura monto(BigDecimal monto) {
        setMonto(monto);
        return this;
    }
    // -- [montoOtrosCargos] ------------------------

    @Digits(integer = 17, fraction = 2)
    @Column(name = "monto_otros_cargos", precision = 19, scale = 2)
    public BigDecimal getMontoOtrosCargos() {
        return montoOtrosCargos;
    }

    public void setMontoOtrosCargos(BigDecimal montoOtrosCargos) {
        this.montoOtrosCargos = montoOtrosCargos;
    }

    public FacFactura montoOtrosCargos(BigDecimal montoOtrosCargos) {
        setMontoOtrosCargos(montoOtrosCargos);
        return this;
    }
    // -- [montoIgv] ------------------------

    @Digits(integer = 17, fraction = 2)
    @NotNull
    @Column(name = "monto_igv", nullable = false, precision = 19, scale = 2)
    public BigDecimal getMontoIgv() {
        return montoIgv;
    }

    public void setMontoIgv(BigDecimal montoIgv) {
        this.montoIgv = montoIgv;
    }

    public FacFactura montoIgv(BigDecimal montoIgv) {
        setMontoIgv(montoIgv);
        return this;
    }
    // -- [montoFinal] ------------------------

    @Digits(integer = 17, fraction = 2)
    @NotNull
    @Column(name = "monto_final", nullable = false, precision = 19, scale = 2)
    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public FacFactura montoFinal(BigDecimal montoFinal) {
        setMontoFinal(montoFinal);
        return this;
    }
    // -- [glosa] ------------------------

    @Size(max = 255, message = "{message.facFactura.glosa.sizeMax} {max} {message.caracter}")
    @Column(name = "glosa")
    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public FacFactura glosa(String glosa) {
        setGlosa(glosa);
        return this;
    }
    // -- [fechaFactura] ------------------------

    @Column(name = "fecha_factura", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public FacFactura fechaFactura(Date fechaFactura) {
        setFechaFactura(fechaFactura);
        return this;
    }
    // -- [fechaEmision] ------------------------

    @Column(name = "fecha_emision", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public FacFactura fechaEmision(Date fechaEmision) {
        setFechaEmision(fechaEmision);
        return this;
    }
    // -- [fechaCreacion] ------------------------

    @NotNull
    @Column(name = "fecha_creacion", nullable = false, length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public FacFactura fechaCreacion(Date fechaCreacion) {
        setFechaCreacion(fechaCreacion);
        return this;
    }
    // -- [existeContrato] ------------------------

    @Size(max = 1, message = "{message.facFactura.existeContrato.sizeMax} {max} {message.caracter}")
    @Column(name = "existe_contrato", length = 1)
    public String getExisteContrato() {
        return existeContrato;
    }

    public void setExisteContrato(String existeContrato) {
        this.existeContrato = existeContrato;
    }

    public FacFactura existeContrato(String existeContrato) {
        setExisteContrato(existeContrato);
        return this;
    }
    // -- [estadoRetornoSunat] ------------------------

    @Size(max = 1, message = "{message.facFactura.estadoRetornoSunat.sizeMax} {max} {message.caracter}")
    @Column(name = "estado_retorno_sunat", length = 1)
    public String getEstadoRetornoSunat() {
        return estadoRetornoSunat;
    }

    public void setEstadoRetornoSunat(String estadoRetornoSunat) {
        this.estadoRetornoSunat = estadoRetornoSunat;
    }

    public FacFactura estadoRetornoSunat(String estadoRetornoSunat) {
        setEstadoRetornoSunat(estadoRetornoSunat);
        return this;
    }
    // -- [detalleServicio] ------------------------

    @Size(max = 255, message = "{message.facFactura.detalleServicio.sizeMax} {max} {message.caracter}")
    @Column(name = "detalle_servicio")
    public String getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(String detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public FacFactura detalleServicio(String detalleServicio) {
        setDetalleServicio(detalleServicio);
        return this;
    }
    // -- [indicadorConCertificado] ------------------------

    @Size(max = 1, message = "{message.facFactura.indicadorConCertificado.sizeMax} {max} {message.caracter}")
    @Column(name = "indicador_con_certificado", length = 1)
    public String getIndicadorConCertificado() {
        return indicadorConCertificado;
    }

    public void setIndicadorConCertificado(String indicadorConCertificado) {
        this.indicadorConCertificado = indicadorConCertificado;
    }

    public FacFactura indicadorConCertificado(String indicadorConCertificado) {
        setIndicadorConCertificado(indicadorConCertificado);
        return this;
    }
    // -- [concepto] ------------------------

    @Size(max = 255, message = "{message.facFactura.concepto.sizeMax} {max} {message.caracter}")
    @Column(name = "concepto")
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public FacFactura concepto(String concepto) {
        setConcepto(concepto);
        return this;
    }
    // -- [codigoCorrelativoAdjunto] ------------------------

    @Size(max = 20, message = "{message.facFactura.codigoCorrelativoAdjunto.sizeMax} {max} {message.caracter}")
    @Column(name = "codigo_correlativo_adjunto", length = 20)
    public String getCodigoCorrelativoAdjunto() {
        return codigoCorrelativoAdjunto;
    }

    public void setCodigoCorrelativoAdjunto(String codigoCorrelativoAdjunto) {
        this.codigoCorrelativoAdjunto = codigoCorrelativoAdjunto;
    }

    public FacFactura codigoCorrelativoAdjunto(String codigoCorrelativoAdjunto) {
        setCodigoCorrelativoAdjunto(codigoCorrelativoAdjunto);
        return this;
    }
    // -- [estado] ------------------------

    @Size(max = 1, message = "{message.facFactura.estado.sizeMax} {max} {message.caracter}")
    @Column(name = "estado", length = 1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public FacFactura estado(String estado) {
        setEstado(estado);
        return this;
    }
    // -- [fechaEstimadaPago] ------------------------

    @Column(name = "fecha_estimada_pago", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaEstimadaPago() {
        return fechaEstimadaPago;
    }

    public void setFechaEstimadaPago(Date fechaEstimadaPago) {
        this.fechaEstimadaPago = fechaEstimadaPago;
    }

    public FacFactura fechaEstimadaPago(Date fechaEstimadaPago) {
        setFechaEstimadaPago(fechaEstimadaPago);
        return this;
    }
    // -- [fechaPago] ------------------------

    @Column(name = "fecha_pago", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public FacFactura fechaPago(Date fechaPago) {
        setFechaPago(fechaPago);
        return this;
    }
    // -- [fechaAprobacion] ------------------------

    @Column(name = "fecha_aprobacion", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public FacFactura fechaAprobacion(Date fechaAprobacion) {
        setFechaAprobacion(fechaAprobacion);
        return this;
    }
    // -- [nroIteracion] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "nro_iteracion", nullable = false, precision = 10)
    public Integer getNroIteracion() {
        return nroIteracion;
    }

    public void setNroIteracion(Integer nroIteracion) {
        this.nroIteracion = nroIteracion;
    }

    public FacFactura nroIteracion(Integer nroIteracion) {
        setNroIteracion(nroIteracion);
        return this;
    }
    // -- [fechaPosibleVencimiento] ------------------------

    @Column(name = "fecha_posible_vencimiento", length = 29)

    @Temporal(TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getFechaPosibleVencimiento() {
        return fechaPosibleVencimiento;
    }

    public void setFechaPosibleVencimiento(Date fechaPosibleVencimiento) {
        this.fechaPosibleVencimiento = fechaPosibleVencimiento;
    }

    public FacFactura fechaPosibleVencimiento(Date fechaPosibleVencimiento) {
        setFechaPosibleVencimiento(fechaPosibleVencimiento);
        return this;
    }
    // -- [indAprobacionRechazoFinal] ------------------------

    @Size(max = 1, message = "{message.facFactura.indAprobacionRechazoFinal.sizeMax} {max} {message.caracter}")
    @Column(name = "ind_aprobacion_rechazo_final", length = 1)
    public String getIndAprobacionRechazoFinal() {
        return indAprobacionRechazoFinal;
    }

    public void setIndAprobacionRechazoFinal(String indAprobacionRechazoFinal) {
        this.indAprobacionRechazoFinal = indAprobacionRechazoFinal;
    }

    public FacFactura indAprobacionRechazoFinal(String indAprobacionRechazoFinal) {
        setIndAprobacionRechazoFinal(indAprobacionRechazoFinal);
        return this;
    }

    // -- [indEstadoAdjunto] ------------------------

    @Size(max = 1, message = "{message.facFactura.indEstadoAdjunto.sizeMax} {max} {message.caracter}")
    @Column(name = "ind_estado_adjunto", length = 1)
    public String getIndEstadoAdjunto() {
        return indEstadoAdjunto;
    }

    public void setIndEstadoAdjunto(String indEstadoAdjunto) {
        this.indEstadoAdjunto = indEstadoAdjunto;
    }

    public FacFactura indEstadoAdjunto(String indEstadoAdjunto) {
        setIndEstadoAdjunto(indEstadoAdjunto);
        return this;
    }
    // -- [documentoPagoSap] ------------------------

    @Size(max = 20, message = "{message.facFactura.documentoPagoSap.sizeMax} {max} {message.caracter}")
    @Column(name = "documento_pago_sap", length = 20)
    public String getDocumentoPagoSap() {
        return documentoPagoSap;
    }

    public void setDocumentoPagoSap(String documentoPagoSap) {
        this.documentoPagoSap = documentoPagoSap;
    }

    public FacFactura documentoPagoSap(String documentoPagoSap) {
        setDocumentoPagoSap(documentoPagoSap);
        return this;
    }
    // -- [numeroFacturaSap] ------------------------

    @Size(max = 20, message = "{message.facFactura.numeroFacturaSap.sizeMax} {max} {message.caracter}")
    @Column(name = "numero_factura_sap", length = 20)
    public String getNumeroFacturaSap() {
        return numeroFacturaSap;
    }

    public void setNumeroFacturaSap(String numeroFacturaSap) {
        this.numeroFacturaSap = numeroFacturaSap;
    }

    public FacFactura numeroFacturaSap(String numeroFacturaSap) {
        setNumeroFacturaSap(numeroFacturaSap);
        return this;
    }
    // -- [anioFacturaSap] ------------------------

    @Size(max = 10, message = "{message.facFactura.anioFacturaSap.sizeMax} {max} {message.caracter}")
    @Column(name = "anio_factura_sap", length = 10)
    public String getAnioFacturaSap() {
        return anioFacturaSap;
    }

    public void setAnioFacturaSap(String anioFacturaSap) {
        this.anioFacturaSap = anioFacturaSap;
    }

    public FacFactura anioFacturaSap(String anioFacturaSap) {
        setAnioFacturaSap(anioFacturaSap);
        return this;
    }


    // -- [numeroFacturaSapPrelim] ------------------------

    @Size(max = 20, message = "{message.facFactura.numeroFacturaSapPrelim.sizeMax} {max} {message.caracter}")
    @Column(name = "numero_factura_sap_prelim", length = 20)
    public String getNumeroFacturaSapPrelim() {
        return numeroFacturaSapPrelim;
    }

    public void setNumeroFacturaSapPrelim(String numeroFacturaSapPrelim) {
        this.numeroFacturaSapPrelim = numeroFacturaSapPrelim;
    }

    public FacFactura numeroFacturaSapPrelim(String numeroFacturaSapPrelim) {
        setNumeroFacturaSapPrelim(numeroFacturaSapPrelim);
        return this;
    }
    // -- [anioFacturaSapPrelim] ------------------------

    @Size(max = 10, message = "{message.facFactura.anioFacturaSapPrelim.sizeMax} {max} {message.caracter}")
    @Column(name = "anio_factura_sap_prelim", length = 10)
    public String getAnioFacturaSapPrelim() {
        return anioFacturaSapPrelim;
    }

    public void setAnioFacturaSapPrelim(String anioFacturaSapPrelim) {
        this.anioFacturaSapPrelim = anioFacturaSapPrelim;
    }

    public FacFactura anioFacturaSapPrelim(String anioFacturaSapPrelim) {
        setAnioFacturaSapPrelim(anioFacturaSapPrelim);
        return this;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacFactura.mtrEstado ==> MtrEstado.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "mtr_estado_id", nullable = false)
    @ManyToOne
    public MtrEstado getMtrEstado() {
        return mtrEstado;
    }

    /**
     * Set the {@link #mtrEstado} without adding this FacFactura instance on the passed {@link #mtrEstado}
     */
    public void setMtrEstado(MtrEstado mtrEstado) {
        this.mtrEstado = mtrEstado;
    }

    public FacFactura mtrEstado(MtrEstado mtrEstado) {
        setMtrEstado(mtrEstado);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacFactura.mtrMoneda ==> MtrMoneda.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "mtr_moneda_id", nullable = false)
    @ManyToOne
    public MtrMoneda getMtrMoneda() {
        return mtrMoneda;
    }

    /**
     * Set the {@link #mtrMoneda} without adding this FacFactura instance on the passed {@link #mtrMoneda}
     */
    public void setMtrMoneda(MtrMoneda mtrMoneda) {
        this.mtrMoneda = mtrMoneda;
    }

    public FacFactura mtrMoneda(MtrMoneda mtrMoneda) {
        setMtrMoneda(mtrMoneda);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacFactura.mtrDetraccion ==> MtrDetraccion.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "mtr_detraccion_id")
    @ManyToOne
    public MtrDetraccion getMtrDetraccion() {
        return mtrDetraccion;
    }

    /**
     * Set the {@link #mtrDetraccion} without adding this FacFactura instance on the passed {@link #mtrDetraccion}
     */
    public void setMtrDetraccion(MtrDetraccion mtrDetraccion) {
        this.mtrDetraccion = mtrDetraccion;
    }

    public FacFactura mtrDetraccion(MtrDetraccion mtrDetraccion) {
        setMtrDetraccion(mtrDetraccion);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacFactura.mtrProveedor ==> MtrProveedor.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "mtr_proveedor_id", nullable = false)
    @ManyToOne
    public MtrProveedor getMtrProveedor() {
        return mtrProveedor;
    }

    /**
     * Set the {@link #mtrProveedor} without adding this FacFactura instance on the passed {@link #mtrProveedor}
     */
    public void setMtrProveedor(MtrProveedor mtrProveedor) {
        this.mtrProveedor = mtrProveedor;
    }

    public FacFactura mtrProveedor(MtrProveedor mtrProveedor) {
        setMtrProveedor(mtrProveedor);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: FacFactura.mtrSociedad ==> MtrSociedad.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "mtr_sociedad_id")
    @ManyToOne
    public MtrSociedad getMtrSociedad() {
        return mtrSociedad;
    }

    /**
     * Set the {@link #mtrSociedad} without adding this FacFactura instance on the passed {@link #mtrSociedad}
     */
    public void setMtrSociedad(MtrSociedad mtrSociedad) {
        this.mtrSociedad = mtrSociedad;
    }

    public FacFactura mtrSociedad(MtrSociedad mtrSociedad) {
        setMtrSociedad(mtrSociedad);
        return this;
    }

    /**
     * Apply the default values.
     */
    public FacFactura withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof FacFactura && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    @Override
    public String toString() {
        return "FacFactura{" +
                "id=" + id +
                ", serieFactura='" + serieFactura + '\'' +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", tipoFactura='" + tipoFactura + '\'' +
                ", tipoComprobanteFactura='" + tipoComprobanteFactura + '\'' +
                ", monto=" + monto +
                ", montoOtrosCargos=" + montoOtrosCargos +
                ", montoIgv=" + montoIgv +
                ", montoFinal=" + montoFinal +
                ", glosa='" + glosa + '\'' +
                ", fechaFactura=" + fechaFactura +
                ", fechaEmision=" + fechaEmision +
                ", fechaCreacion=" + fechaCreacion +
                ", existeContrato='" + existeContrato + '\'' +
                ", estadoRetornoSunat='" + estadoRetornoSunat + '\'' +
                ", detalleServicio='" + detalleServicio + '\'' +
                ", indicadorConCertificado='" + indicadorConCertificado + '\'' +
                ", concepto='" + concepto + '\'' +
                ", codigoCorrelativoAdjunto='" + codigoCorrelativoAdjunto + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaEstimadaPago=" + fechaEstimadaPago +
                ", fechaPago=" + fechaPago +
                ", fechaAprobacion=" + fechaAprobacion +
                ", nroIteracion=" + nroIteracion +
                ", fechaPosibleVencimiento=" + fechaPosibleVencimiento +
                ", indAprobacionRechazoFinal='" + indAprobacionRechazoFinal + '\'' +
                ", indEstadoAdjunto='" + indEstadoAdjunto + '\'' +
                ", documentoPagoSap='" + documentoPagoSap + '\'' +
                ", numeroFacturaSap='" + numeroFacturaSap + '\'' +
                ", anioFacturaSap='" + anioFacturaSap + '\'' +
                ", numeroFacturaSapPrelim='" + numeroFacturaSapPrelim + '\'' +
                ", anioFacturaSapPrelim='" + anioFacturaSapPrelim + '\'' +
                ", mtrEstado=" + mtrEstado +
                ", mtrMoneda=" + mtrMoneda +
                ", mtrDetraccion=" + mtrDetraccion +
                ", mtrProveedor=" + mtrProveedor +
                ", mtrSociedad=" + mtrSociedad +
                ", fechaEmisionIni='" + fechaEmisionIni + '\'' +
                ", fechaEmisionFin='" + fechaEmisionFin + '\'' +
                ", fechaPagoIni='" + fechaPagoIni + '\'' +
                ", fechaPagoFin='" + fechaPagoFin + '\'' +
                ", fechaAprobacionIni='" + fechaAprobacionIni + '\'' +
                ", fechaAprobacionFin='" + fechaAprobacionFin + '\'' +
                ", fechaCreacionIni='" + fechaCreacionIni + '\'' +
                ", fechaCreacionFin='" + fechaCreacionFin + '\'' +
                ", fechaEstimadaPagoIni='" + fechaEstimadaPagoIni + '\'' +
                ", fechaEstimadaPagoFin='" + fechaEstimadaPagoFin + '\'' +
                ", fechaFacturaIni='" + fechaFacturaIni + '\'' +
                ", fechaFacturaFin='" + fechaFacturaFin + '\'' +
                ", estadoValidacionAdjunto='" + estadoValidacionAdjunto + '\'' +
                ", mtrEstadoLista=" + mtrEstadoLista +
                ", esProveedorExtranjero='" + esProveedorExtranjero + '\'' +
                ", listaCodigoAcreedor=" + listaCodigoAcreedor +
                ", identifiableHashBuilder=" + identifiableHashBuilder +
                '}';
    }
}
