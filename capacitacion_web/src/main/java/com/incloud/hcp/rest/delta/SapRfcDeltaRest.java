package com.incloud.hcp.rest.delta;

import com.incloud.hcp.bean.custom.*;
import com.incloud.hcp.domain.*;
import com.incloud.hcp.repository.delta.*;
import com.incloud.hcp.rest.SapRfcRest;
import com.incloud.hcp.service.delta.CerHistorialDeltaService;
import com.incloud.hcp.service.delta.SapRfcDeltaService;
import com.incloud.hcp.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/integrasap")
public class SapRfcDeltaRest extends SapRfcRest {
    private final Logger log = LoggerFactory.getLogger(SapRfcDeltaRest.class);

    @Autowired
    protected CerHistorialDeltaService cerHistorialDeltaService;

    @Autowired
    protected SapRfcDeltaService sapRfcDeltaService;

    @Autowired
    protected AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    protected FacFacturaDeltaRepository facFacturaDeltaRepository;

    @Autowired
    protected MtrTasaCambioDeltaRepository mtrTasaCambioDeltaRepository;

    @Autowired
    protected MtrMonedaDeltaRepository mtrMonedaDeltaRepository;


    @Autowired
    protected MtrSociedadDeltaRepository mtrSociedadDeltaRepository;


    @Autowired
    protected MtrEstadoDeltaRepository mtrEstadoDeltaRepository;


    @Autowired
    protected FacHistorialDeltaRepository facHistorialDeltaRepository;

    @ApiOperation(value = "Integracion de ordenes de compra", produces = "application/json")
    @RequestMapping(value = "/integraOrdenCompra", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<DescargaServicioSap>> integraOrdenCompra(@RequestBody FiltroRangeDescarga filtroRangeDescarga) {

        return Optional.of(this.sapRfcDeltaService.listaDescarga(filtroRangeDescarga.getListaRangeDoc(), filtroRangeDescarga.getListaRangeFecha()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Detalle integracion de ordenes de compra", produces = "application/json")
    @RequestMapping(value = "/integraOrdenCompraDetalle", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<DescargaServicioSap>> integraOrdenCompraDetalle(@RequestBody FiltroRangeDescarga filtroRangeDescarga) {

        return Optional.of(this.sapRfcDeltaService.listaDescargaDetalle(filtroRangeDescarga.getListaRangeDoc(), filtroRangeDescarga.getListaRangeFecha()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Servicio Creación Hes", produces = "application/json")
    @RequestMapping(value = "/creacionHes", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<RemitoOutSap>> creacionHes(@RequestBody FiltroCreacionHes filtroCreacionHes) {

        return Optional.of(this.sapRfcDeltaService.creacionHes(filtroCreacionHes.getListaIn()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Servicio Creación Hes 2", produces = "application/json")
    @RequestMapping(value = "/crearHes", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<RemitoOutSap>> crearHes(@RequestBody FiltroCreacionHes filtroCreacionHes) throws Exception {

        return Optional.of(this.sapRfcDeltaService.crearHes(filtroCreacionHes.getListaIn()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @ApiOperation(value = "Consulta Clase Documento", produces = "application/json")
    @RequestMapping(value = "/claseDoc", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<TextClaseDocSap>> getDetailsMaterial() {

        return Optional.of(this.sapRfcDeltaService.listaClaseDocumentos())
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Lista Centro Sap", produces = "application/json")
    @RequestMapping(value = "/centroSap/{sociedad}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<CentroSap>> listaCentroSap(@PathVariable String sociedad) {

        return Optional.of(this.sapRfcDeltaService.listaCentroSap(sociedad))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Lista Centro almacen Sap", produces = "application/json")
    @RequestMapping(value = "/centroAlmacenSap/{sociedad}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<CentroAlmacenSap>> listaCentroAlmacenSap(@PathVariable String sociedad) {

        return Optional.of(this.sapRfcDeltaService.listaCentroAlmacen(sociedad))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        //sdsffhfghfghfghfh
    }

    @ApiOperation(value = "Integrar Unidad de medida", produces = "application/json")
    @RequestMapping(value = "/integra/unidadMedida", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<MensajeSap>> unidadMedia() {

        return Optional.of(this.sapRfcDeltaService.integrarUnidadMedida())
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        //sdsffhfghfghfghfh
    }

    @ApiOperation(value = "Integrar Grupo de articulos", produces = "application/json")
    @RequestMapping(value = "/integra/grupoArticulo", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<MensajeSap>> grupoArticulo() {

        return Optional.of(this.sapRfcDeltaService.integrarGrupoArticulos(null))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        //sdsffhfghfghfghfh
    }

    @ApiOperation(value = "Integracion de Materiales", produces = "application/json")
    @RequestMapping(value = "/integraMateriales", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSap>> integraMateriales(@RequestBody FiltroIntegracion filtro) {

        return Optional.of(this.sapRfcDeltaService.integrarMateriales(filtro.getFechaInicio(), filtro.getFechaFin()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Integracion de Servicios", produces = "application/json")
    @RequestMapping(value = "/integraServicios", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSap>> integraServicios(@RequestBody FiltroIntegracion filtro) {

        return Optional.of(this.sapRfcDeltaService.integrarServicios(filtro.getFechaInicio(), filtro.getFechaFin()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Integracion de Proveedores", produces = "application/json")
    @RequestMapping(value = "/integraProveedores", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSap>> integraProveedores(@RequestBody FiltroIntegracion filtro) {

        return Optional.of(this.sapRfcDeltaService.integrarProveedor(filtro.getFechaInicio(), filtro.getFechaFin(), filtro.getLifnr()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Integracion de aprobadores", produces = "application/json")
    @RequestMapping(value = "/aprobadores", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<MensajeSap> getAprobadores() {

        return Optional.of(this.sapRfcDeltaService.integrarAprobadores())
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Integra textos de pedido", produces = "application/json")
    @RequestMapping(value = "/integraTextoPedido", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<DescargaServicioSap>> integraTextoPedido(@RequestBody FiltroRangeDescarga filtroRangeDescarga) {

        return Optional.of(this.sapRfcDeltaService.integraTextosPedido(filtroRangeDescarga.getListaRangeDoc(), filtroRangeDescarga.getListaRangeFecha()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Integracion cuenta mayor", produces = "application/json")
    @RequestMapping(value = "/cuentaMayor", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSap>> cuentaMayor(@RequestBody FiltroCuenta filtroCuenta) {

        return Optional.of(this.sapRfcDeltaService.integrarCuentaMayor(filtroCuenta.getRangoMenor(), filtroCuenta.getRangoMayor(), filtroCuenta.getCodigoSap()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Inregracion cuenta de imputacion", produces = "application/json")
    @RequestMapping(value = "/cuentaImputacion", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSap>> cuentaImputacion(@RequestBody FiltroCuenta filtroCuenta) {

        return Optional.of(this.sapRfcDeltaService.integrarCuentaImputacion(filtroCuenta.getRangoMenor(), filtroCuenta.getRangoMayor(), filtroCuenta.getTipoImputacion(), filtroCuenta.getCodigoSap()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Actualiza nota de pedido Hana", produces = "application/json")
    @RequestMapping(value = "/actualizarPedidoHana", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<MensajeSap> actualizarPedidoHana(@RequestBody FiltroRangeDescarga filtroRangeDescarga) throws Exception {

        return Optional.of(this.sapRfcDeltaService.integrarNotaPedido(filtroRangeDescarga.getListaRangeDoc(), filtroRangeDescarga.getListaRangeFecha(), filtroRangeDescarga.getEnvioCorreo()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Servicio para pre registro de factura", produces = "application/json")
    @RequestMapping(value = "/preRegistroFactura", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<PreRegistroOutput> creacionHes(@RequestBody PreRegistroInput preRegistroInput) throws Exception {

        return Optional.of(this.sapRfcDeltaService.preRegistroFactura(preRegistroInput))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Obtener Comprobante SAP", produces = "application/json")
    @RequestMapping(value = "/obtenerComprobanteSap", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<ComprobantePagoSap>> obtenerComprobanteSap(@RequestBody ComprobanteSapInput comprobanteSapInput) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
        //AppParametria rucProveedor = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "RUC_PROVEEDOR");
        //AppParametria sociedad = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "SOCIEDAD");
        //AppParametria numeroAcreedor = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "NUMERO_ACREEDOR");
        AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");
        AppParametria estado = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "Estado");
        AppParametria fechaDesde = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "FECHA_DESDE");
        AppParametria fechaFasta = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "FECHA_HASTA");

        Date dateAyer = new Date();
        Date dateHoy = new Date();

        if (fechaDesde != null && fechaFasta != null && !StringUtils.isBlank(fechaDesde.getValue1()) && !StringUtils.isBlank(fechaFasta.getValue1())) {
            try {
                dateAyer = format.parse(fechaDesde.getValue1().trim());
                dateHoy = format.parse(fechaFasta.getValue1().trim());
            } catch (Exception e) {
                log.error("obtenerComprobanteSap rest :: error date parse " + e.toString());
            }
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(dateAyer);
            c.add(Calendar.DATE, -1);
            dateAyer = c.getTime();

            c.setTime(dateHoy);
            c.add(Calendar.DATE, 0);
            dateHoy = c.getTime();
        }


        //format.format(dateAyer), format.format(dateManiana)
        List<IndicadorFiscalSap> listaIndicador = new ArrayList<IndicadorFiscalSap>();
        /*IndicadorFiscalSap indicador = new IndicadorFiscalSap();
        indicador.setStcd1(rucProveedor.getValue1());
        indicador.setBukrs(sociedad.getValue1());
        indicador.setLifnr(numeroAcreedor.getValue1());
        listaIndicador.add(indicador);*/

        List<RangeSap> listaFecha = new ArrayList<RangeSap>();
        RangeSap rangeFecha = new RangeSap();
        rangeFecha.setSign("I");
        rangeFecha.setOption("BT");
        rangeFecha.setLow(format.format(dateAyer));
        rangeFecha.setHigh(format.format(dateHoy));

        listaFecha.add(rangeFecha);

        List<RangeSap> listaClaseDoc = new ArrayList<RangeSap>();
        RangeSap rangeClase = new RangeSap();
        rangeClase.setSign("I");
        rangeClase.setOption("EQ");
        rangeClase.setLow(claseDocumento.getValue1());
        rangeClase.setHigh("");

        listaClaseDoc.add(rangeClase);
        //GJAH    --anio
        //STCD1 -- ruc
        //XBLNR -- combinado
        //01-F040-00054804
        List<ComprobantePagoSap> lista = this.sapRfcDeltaService.obtenerComprobantesPago(listaIndicador,
                listaFecha, listaClaseDoc);
        log.error("obtenerComprobanteSap rest :: if(lista != null) { ");
        if (lista != null) {
            log.error("obtenerComprobanteSap rest :: 1 ");
            for (ComprobantePagoSap ele : lista) {
                log.error("obtenerComprobanteSap rest :: 2 ");
                List<FacFactura> listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorNumeroFacturaSap(ele.getBelnr().trim());
                FacFactura factura = null;
                if (listaFact != null && listaFact.size() > 0) {
                    factura = listaFact.get(0);
                    log.error("obtenerComprobanteSap rest :: 3 ");
                } else {
                    log.error("obtenerComprobanteSap rest :: 4 " + ele.getXblnr());
                    String[] arrCode = ele.getXblnr().trim().split("-");
                    String tipoComprobante = arrCode[0];
                    String serie = arrCode[1];
                    String numeroFactura = arrCode[2];


                    //ele.getGjahr() -- aino
                    listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorCodigoUnico(numeroFactura, serie, tipoComprobante, ele.getStcd1().trim(), ele.getGjahr().trim());
                    if (listaFact != null && listaFact.size() > 0) {
                        factura = listaFact.get(0);

                    }


                }
                log.error("obtenerComprobanteSap rest :: 5 ");
                if (factura != null) {
                    log.error("obtenerComprobanteSap rest :: 5_1 ");

                    MtrEstado objEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(estado.getValue1(), estado.getValue2());
                    //=================================================================
                    log.error("obtenerComprobanteSap rest :: 6");

                    factura.setMtrEstado(objEstado);
                    factura.setDocumentoPagoSap(ele.getAugbl());
                    //factura.setFechaPago();
                    if (ele.getAugdt() != null) {
                        log.error("obtenerComprobanteSap ele.getAugdt() :: 6_1 " + ele.getAugdt());
                        factura.setFechaPago(ele.getAugdt());
                    }
                    //factura.set
                    FacFactura facx = this.facFacturaDeltaRepository.save(factura);
                    //==================================================Historial
                    FacHistorial facHistorial = new FacHistorial();
                    facHistorial.setDescripcion("Documento pagado por SAP");
                    facHistorial.setMtrEstado(objEstado);
                    facHistorial.setFechaHistorial(DateUtils.obtenerFechaHoraActual());
                    facHistorial.setFacFactura(facx);
                    facHistorial.setUsuarioHistorial("SAP");
                    FacHistorial out = this.facHistorialDeltaRepository.save(facHistorial);
                    log.error("obtenerComprobanteSap rest :: 6_1");
                }

            }
        }
        log.error("obtenerComprobanteSap rest :: 7 ");
        return Optional.of(lista)
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        /*return Optional.of(this.sapRfcDeltaService.obtenerComprobantesPago(comprobanteSapInput.getListaIndicadorFiscalSap(),
                comprobanteSapInput.getListaFechaRegistro(), comprobanteSapInput.getListaClaseDocumento()))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));*/
    }


    @ApiOperation(value = "Obtener Tipo de Cambio", produces = "application/json")
    @RequestMapping(value = "/obtenerTipoCambio", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<TipoCambioBean>> obtenerTipoCambio() {
        List<MtrMoneda> listaMoneda = this.mtrMonedaDeltaRepository.findAll();

        Date fechaHoy = new Date();
        List<TipoCambioBean> lista = this.sapRfcDeltaService.listaTipoCambio(fechaHoy);
        log.error("obtenerTipoCambio rest :: 1 lista.size" + lista.size());
        log.error("obtenerTipoCambio rest :: 1 lista: " + lista);
        for (TipoCambioBean tipoCambio : lista) {

            Optional<MtrMoneda> optionalMonedaExtranjera = listaMoneda.stream()
                    .filter(x -> tipoCambio.getMonedaextranjera().equalsIgnoreCase(x.getDescBrv()))
                    .findFirst();

            Optional<MtrMoneda> optionalMonedLocal = listaMoneda.stream()
                    .filter(x -> tipoCambio.getMonedaLocal().equalsIgnoreCase(x.getDescBrv()))
                    .findFirst();

            log.error("obtenerTipoCambio rest :: 2 ");

            if (optionalMonedLocal.isPresent() && optionalMonedaExtranjera.isPresent()) {
                log.error("obtenerTipoCambio rest :: 3 optionalMonedLocal.get(): " + optionalMonedLocal.get());
                log.error("obtenerTipoCambio rest :: 3 optionalMonedaExtranjera.get(): " + optionalMonedaExtranjera.get());
                MtrTasaCambio tasaCambio = new MtrTasaCambio();
                MtrTasaCambio mtrTasaCambioBuscar = this.mtrTasaCambioDeltaRepository.
                        getByFechaTasaAndMtrMonedaOrigenAndMtrMonedaDestino(
                                tipoCambio.getFecha(),
                                optionalMonedLocal.get(),
                                optionalMonedaExtranjera.get()
                        );
                if (Optional.ofNullable(mtrTasaCambioBuscar).isPresent()) {
                    tasaCambio = mtrTasaCambioBuscar;
                }
                tasaCambio.setMtrMonedaOrigen(optionalMonedLocal.get());
                tasaCambio.setMtrMonedaDestino(optionalMonedaExtranjera.get());
                tasaCambio.setValor(tipoCambio.getTipoCambio().setScale(4, BigDecimal.ROUND_HALF_EVEN));
                tasaCambio.setFechaTasa(tipoCambio.getFecha());
                log.error("obtenerTipoCambio rest :: 4 tasaCambio: " + tasaCambio.toString());
                this.mtrTasaCambioDeltaRepository.save(tasaCambio);
                log.error("obtenerTipoCambio rest :: 5 ");
            }

        }
        log.error("obtenerTipoCambio rest :: 6");
        return Optional.of(lista)
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @ApiOperation(value = "Obtener Tipo de CambioFecha", produces = "application/json")
    @RequestMapping(value = "/obtenerTipoCambioRangoFecha", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<List<TipoCambioBean>> obtenerTipoCambioRangoFecha(
            @RequestBody TipoCambioEntradaRangoFechaBean beanFecha) {
        HttpHeaders headers = new HttpHeaders();
        try {
            if (!Optional.ofNullable(beanFecha.getFechaIni()).isPresent()) {
                throw new Exception("Debe ingresar Fecha Inicial");
            }
            if (!Optional.ofNullable(beanFecha.getFechaFin()).isPresent()) {
                throw new Exception("Debe ingresar Fecha Final");
            }
            boolean continuar = true;
            List<TipoCambioBean> listaFinal = new ArrayList<TipoCambioBean>();
            int contador = 0;
            Date fechaHoy = beanFecha.getFechaIni();
            List<MtrMoneda> listaMoneda = this.mtrMonedaDeltaRepository.findAll();

            while (continuar) {

                fechaHoy = DateUtils.sumarRestarDias(fechaHoy, contador);
                log.error("obtenerTipoCambio rest :: 0 fechaHoy " + fechaHoy);
                log.error("obtenerTipoCambio rest :: 0 contador " + contador);
                contador++;
                if (fechaHoy.after(beanFecha.getFechaFin())) {
                    continuar = false;
                }
                if (continuar) {
                    List<TipoCambioBean> lista = this.sapRfcDeltaService.listaTipoCambio(fechaHoy);
                    listaFinal.addAll(lista);

                    log.error("obtenerTipoCambio rest :: 1 " + lista);
                    log.error("obtenerTipoCambio rest :: 1 lista.size" + lista.size());
                    log.error("obtenerTipoCambio rest :: 1 lista: " + lista);
                    for (TipoCambioBean tipoCambio : lista) {

                        Optional<MtrMoneda> optionalMonedaExtranjera = listaMoneda.stream()
                                .filter(x -> tipoCambio.getMonedaextranjera().equalsIgnoreCase(x.getDescBrv()))
                                .findFirst();

                        Optional<MtrMoneda> optionalMonedLocal = listaMoneda.stream()
                                .filter(x -> tipoCambio.getMonedaLocal().equalsIgnoreCase(x.getDescBrv()))
                                .findFirst();

                        log.error("obtenerTipoCambio rest :: 2 ");

                        if (optionalMonedLocal.isPresent() && optionalMonedaExtranjera.isPresent()) {
                            log.error("obtenerTipoCambio rest :: 3 optionalMonedLocal.get(): " + optionalMonedLocal.get());
                            log.error("obtenerTipoCambio rest :: 3 optionalMonedaExtranjera.get(): " + optionalMonedaExtranjera.get());
                            MtrTasaCambio tasaCambio = new MtrTasaCambio();
                            MtrTasaCambio mtrTasaCambioBuscar = this.mtrTasaCambioDeltaRepository.
                                    getByFechaTasaAndMtrMonedaOrigenAndMtrMonedaDestino(
                                            tipoCambio.getFecha(),
                                            optionalMonedLocal.get(),
                                            optionalMonedaExtranjera.get()
                                    );
                            if (Optional.ofNullable(mtrTasaCambioBuscar).isPresent()) {
                                tasaCambio = mtrTasaCambioBuscar;
                            }

                            tasaCambio.setMtrMonedaOrigen(optionalMonedLocal.get());
                            tasaCambio.setMtrMonedaDestino(optionalMonedaExtranjera.get());
                            tasaCambio.setValor(tipoCambio.getTipoCambio().setScale(4, BigDecimal.ROUND_HALF_EVEN));
                            tasaCambio.setFechaTasa(tipoCambio.getFecha());
                            log.error("obtenerTipoCambio rest :: 4 tasaCambio: " + tasaCambio.toString());
                            this.mtrTasaCambioDeltaRepository.save(tasaCambio);
                            log.error("obtenerTipoCambio rest :: 5 ");
                        }
                    }
                }
                log.error("obtenerTipoCambio rest :: 5 SGTE ");
            }
            log.error("obtenerTipoCambio rest :: 6 ");
            return Optional.ofNullable(listaFinal).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

    }


    @ApiOperation(value = "Generar la contabilización de un comprobante PF", produces = "application/json")
    @RequestMapping(value = "/contabilizarComprobante", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<ContabilizacionComprobanteOutput> generaContablizacionComprobante(@RequestBody ContabilizacionInput input) throws Exception {

        return Optional.of(this.sapRfcDeltaService.generaContablizacionComprobante(input, null, null, "3"))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Generar la contabilización de un comprobante PF", produces = "application/json")
    @RequestMapping(value = "/contabilizarComprobante2", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<ContabilizacionComprobanteOutput> generaContablizacionComprobante2(@RequestBody ContabilizacionInput input) throws Exception {

        return Optional.of(this.sapRfcDeltaService.generaContablizacionComprobante(input, null, null, ""))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }



    @ApiOperation(value = "Obtener Comprobante Sap Rango Fecha", produces = "application/json")
    @RequestMapping(value = "/obtenerComprobanteSapRangoFecha", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> obtenerComprobanteSapRangoFecha(
            @RequestBody ObtenerComprobanteSapEntradaDto beanFecha) {
        HttpHeaders headers = new HttpHeaders();
        try {
            if (!Optional.ofNullable(beanFecha.getFechaIni()).isPresent()) {
                throw new Exception("Debe ingresar Fecha Inicial");
            }
            if (!Optional.ofNullable(beanFecha.getFechaFin()).isPresent()) {
                throw new Exception("Debe ingresar Fecha Final");
            }

            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
            AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");
            AppParametria estado = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "Estado");

            Date dateHoy = DateUtils.convertStringToDate("dd/MM/yyyy", beanFecha.getFechaFin());
            Date dateAyer = DateUtils.convertStringToDate("dd/MM/yyyy", beanFecha.getFechaIni());


            //format.format(dateAyer), format.format(dateManiana)
            List<IndicadorFiscalSap> listaIndicador = new ArrayList<IndicadorFiscalSap>();

            List<RangeSap> listaFecha = new ArrayList<RangeSap>();
            RangeSap rangeFecha = new RangeSap();
            rangeFecha.setSign("I");
            rangeFecha.setOption("BT");
            rangeFecha.setLow(format.format(dateAyer));
            rangeFecha.setHigh(format.format(dateHoy));

            listaFecha.add(rangeFecha);

            List<RangeSap> listaClaseDoc = new ArrayList<RangeSap>();
            RangeSap rangeClase = new RangeSap();
            rangeClase.setSign("I");
            rangeClase.setOption("EQ");
            rangeClase.setLow(claseDocumento.getValue1());
            rangeClase.setHigh("");

            listaClaseDoc.add(rangeClase);
            //GJAH    --anio
            //STCD1 -- ruc
            //XBLNR -- combinado
            //01-F040-00054804
            List<ComprobantePagoSap> lista = this.sapRfcDeltaService.obtenerComprobantesPago(listaIndicador,
                    listaFecha, listaClaseDoc);
            log.error("obtenerComprobanteSap rest :: if(lista != null) { ");
            if (lista != null) {
                log.error("obtenerComprobanteSap rest :: 1 ");
                for (ComprobantePagoSap ele : lista) {
                    log.error("obtenerComprobanteSap rest :: 2 ");
                    List<FacFactura> listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorNumeroFacturaSap(ele.getBelnr().trim());
                    FacFactura factura = null;
                    if (listaFact != null && listaFact.size() > 0) {
                        factura = listaFact.get(0);
                        log.error("obtenerComprobanteSap rest :: 3 ");
                    } else {
                        log.error("obtenerComprobanteSap rest :: 4 " + ele.getXblnr());
                        String[] arrCode = ele.getXblnr().trim().split("-");
                        String tipoComprobante = arrCode[0];
                        String serie = arrCode[1];
                        String numeroFactura = arrCode[2];


                        //ele.getGjahr() -- aino
                        listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorCodigoUnico(numeroFactura, serie, tipoComprobante, ele.getStcd1().trim(), ele.getGjahr().trim());
                        if (listaFact != null && listaFact.size() > 0) {
                            factura = listaFact.get(0);

                        }
                    }
                    log.error("obtenerComprobanteSap rest :: 5 ");
                    if (factura != null) {
                        log.error("obtenerComprobanteSap rest :: 5_1 ");

                        MtrEstado objEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(estado.getValue1(), estado.getValue2());
                        //=================================================================
                        log.error("obtenerComprobanteSap rest :: 6");

                        factura.setMtrEstado(objEstado);
                        factura.setDocumentoPagoSap(ele.getAugbl());
                        if (ele.getAugdt() != null) {
                            log.error("obtenerComprobanteSap ele.getAugdt() :: 6_1 " + ele.getAugdt());
                            factura.setFechaPago(ele.getAugdt());
                        }
                        //factura.setFechaPago();
                        FacFactura facx = this.facFacturaDeltaRepository.save(factura);
                        //==================================================Historial
                        FacHistorial facHistorial = new FacHistorial();
                        facHistorial.setDescripcion("Documento pagado por SAP");
                        facHistorial.setMtrEstado(objEstado);
                        facHistorial.setFechaHistorial(DateUtils.obtenerFechaHoraActual());
                        facHistorial.setFacFactura(facx);
                        facHistorial.setUsuarioHistorial("SAP");
                        this.facHistorialDeltaRepository.save(facHistorial);
                        log.error("obtenerComprobanteSap rest :: 6_1");
                    }

                }
            }


            return Optional.ofNullable("OK").map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

    }


    @ApiOperation(value = "Consulta Compensaciones Sap Rango Fecha", produces = "application/json")
    @RequestMapping(value = "/consultaCompensaciones", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> consultaCompensaciones(
            @RequestBody ObtenerComprobanteSapEntradaDto beanFecha) {
        HttpHeaders headers = new HttpHeaders();
        try {

            //Inicio
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");

            //AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");
            AppParametria estado = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "Estado");
            List<AppParametria> claseDocumento = appParametriaDeltaRepository.findByModuloAndLabelAndStatus("Consulta_Comprobante", "CLASE_DOCUMENTO", "1");
            //AppParametria sociedad = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "SOCIEDAD");
            // AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");

            // Date dateHoy = DateUtils.obtenerFechaActual();
            //Date dateAyer = DateUtils.sumarRestarDias(dateHoy, -1);


            // List<IndicadorFiscalSap> listaIndicador = new ArrayList<IndicadorFiscalSap>();


            Date dateHoy = DateUtils.convertStringToDate("dd/MM/yyyy", beanFecha.getFechaFin());
            Date dateAyer = DateUtils.convertStringToDate("dd/MM/yyyy", beanFecha.getFechaIni());


            //format.format(dateAyer), format.format(dateManiana)
            //List<IndicadorFiscalSap> listaIndicador = new ArrayList<IndicadorFiscalSap>();

            List<RangeSap> listaFecha = new ArrayList<RangeSap>();
            RangeSap rangeFecha = new RangeSap();
            rangeFecha.setSign("I");
            rangeFecha.setOption("BT");
            rangeFecha.setLow(format.format(dateAyer));
            rangeFecha.setHigh(format.format(dateHoy));

            listaFecha.add(rangeFecha);

            List<RangeSap> listaClaseDoc = new ArrayList<RangeSap>();
            if (claseDocumento != null && claseDocumento.size() > 0) {
                for (AppParametria ele : claseDocumento) {
                    RangeSap rangeClase = new RangeSap();
                    rangeClase.setSign("I");
                    rangeClase.setOption("EQ");
                    rangeClase.setLow(ele.getValue1());
                    rangeClase.setHigh("");

                    listaClaseDoc.add(rangeClase);
                }
            }

            List<RangeSap> listaRangeSociedad = new ArrayList<RangeSap>();
            List<MtrSociedad> listaSociedad = this.mtrSociedadDeltaRepository.findAll();
            for (MtrSociedad soc : listaSociedad) {
                RangeSap rg = new RangeSap();
                rg.setSign("I");
                rg.setOption("EQ");
                rg.setLow(soc.getDescBrv());
                rg.setHigh("");
                listaRangeSociedad.add(rg);
            }
            List<ComprobantePagoSap> lista = this.sapRfcDeltaService.obtenerComprobantesPagoV2(listaRangeSociedad,
                    listaClaseDoc, listaFecha);
            log.error("consultaCompensaciones rest :: if(lista != null) { ");
            if (lista != null) {
                log.error("consultaCompensaciones rest :: 1 ");
                for (ComprobantePagoSap ele : lista) {

                    //List<FacFactura> listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorNumeroFacturaSap(ele.getBelnr().trim());
                    List<FacFactura> listaFact = null;
                    FacFactura factura = null;
                    /*if(listaFact != null && listaFact.size() > 0) {

                        log.error("consultaCompensaciones rest :: 3 ");
                    }else {*/
                    log.error("consultaCompensaciones rest :: 4 " + ele.getXblnr() + " ---- " + ele.getGjahr() + " ---- " + ele.getStcd1());
                    String[] arrCode = ele.getXblnr().trim().split("-");
                    String tipoComprobante = arrCode[0];
                    String serie = arrCode[1];
                    String numeroFactura = arrCode[2];


                    //ele.getGjahr() -- aino
                    listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorCodigoUnico(numeroFactura, serie, tipoComprobante, ele.getStcd1().trim(), ele.getGjahr().trim());
                    if (listaFact != null && listaFact.size() > 0) {
                        factura = listaFact.get(0);
                        log.error("consultaCompensaciones rest :: unico " + ele.getBelnr());

                    }


                    //}
                    log.error("consultaCompensaciones rest :: 5 ");
                    if (factura != null && StringUtils.isBlank(factura.getDocumentoPagoSap())) {
                        log.error("consultaCompensaciones rest :: 5_1 " + factura.getId());

                        MtrEstado objEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(estado.getValue1(), estado.getValue2());
                        //=================================================================
                        log.error("consultaCompensaciones rest :: 6");

                        factura.setMtrEstado(objEstado);
                        factura.setDocumentoPagoSap(ele.getAugbl());
                        if (ele.getAugdt() != null) {
                            log.error("consultaCompensaciones ele.getAugdt() :: 6_1 " + ele.getAugdt());
                            factura.setFechaPago(ele.getAugdt());
                        }
                        //factura.setFechaPago();
                        FacFactura facx = this.facFacturaDeltaRepository.save(factura);
                        //==================================================Historial
                        FacHistorial facHistorial = new FacHistorial();
                        facHistorial.setDescripcion("Documento pagado por SAP");
                        facHistorial.setMtrEstado(objEstado);
                        facHistorial.setFechaHistorial(DateUtils.obtenerFechaHoraActual());
                        facHistorial.setFacFactura(facx);
                        facHistorial.setUsuarioHistorial("SAP");
                        this.facHistorialDeltaRepository.save(facHistorial);
                        log.error("consultaCompensaciones rest :: 6_1");
                    }

                }
            }
            //Fin
            return Optional.ofNullable("OK").map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

    }

}
