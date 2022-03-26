package com.incloud.hcp.job;


import com.incloud.hcp.bean.custom.ComprobantePagoSap;
import com.incloud.hcp.bean.custom.IndicadorFiscalSap;
import com.incloud.hcp.bean.custom.RangeSap;
import com.incloud.hcp.bean.custom.TipoCambioBean;
import com.incloud.hcp.domain.*;
import com.incloud.hcp.repository.delta.*;
import com.incloud.hcp.service.delta.CerCertificadoDeltaService;
import com.incloud.hcp.service.delta.CerNotaPedidoDeltaService;
import com.incloud.hcp.service.delta.FacFacturaDeltaService;
import com.incloud.hcp.service.delta.SapRfcDeltaService;
import com.incloud.hcp.util.Utils;
import com.incloud.hcp.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ScheduledTasks {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private CerNotaPedidoDeltaService cerNotaPedidoDeltaService;

    @Autowired
    private AppParametriaDeltaRepository appParametriaDeltaRepository;

    @Autowired
    private SapRfcDeltaService sapRfcDeltaService;

    @Autowired
    private MtrMonedaDeltaRepository mtrMonedaDeltaRepository;

    @Autowired
    private AppProcesoLogDeltaRepository appProcesoLogDeltaRepository;

    @Autowired
    private FacFacturaDeltaRepository facFacturaDeltaRepository;

    @Autowired
    private MtrTasaCambioDeltaRepository mtrTasaCambioDeltaRepository;

    @Autowired
    private MtrEstadoDeltaRepository mtrEstadoDeltaRepository;

    @Autowired
    private FacHistorialDeltaRepository facHistorialDeltaRepository;

    @Autowired
    private MtrSociedadDeltaRepository mtrSociedadDeltaRepository;

    @Autowired
    private FacFacturaDeltaService facFacturaDeltaService;

    @Autowired
    private CerCertificadoDeltaService cerCertificadoDeltaService;

    @Value("${activar.job.correo}")
    protected boolean activarJobCorreo;

    @Scheduled(cron = "0 5 * * * ?")
    public void scheduleActualizarNPNoVigentes() {
        logger.error("Cron Task scheduleActualizarNPNoVigentes :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            this.cerNotaPedidoDeltaService.actualizarNoVigente();
        } catch (Exception e) {
            logger.error("Cron Task Fin JOB scheduleActualizarNPNoVigentes ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
    }

   /* @Scheduled(cron = "0 00 00 * * ?")
    public void scheduleSincronizarDiarioNotaPedido() {
        logger.error("Cron Task liberacion nota de pedido scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        //java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
        AppParametria paramFechaInicial = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Inicio");
        AppParametria paramFechaFinal = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Fin");



        try {
            List<RangeSap> listaFecha = new ArrayList<RangeSap>();
            RangeSap range = new RangeSap();

            range.setSign("I");
            range.setOption("BT");
            range.setLow(paramFechaInicial.getValue1());
            range.setHigh(paramFechaFinal.getValue1());

            listaFecha.add(range);
            this.sapRfcDeltaService.integrarNotaPedido(null, listaFecha);

        }
        catch (Exception e) {
            logger.error("Cron Task Fin JOB liberacion nota de pedido scheduleSincronizarDiaria ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB liberacion nota de pedido scheduleSincronizarDiaria");
    }

    @Scheduled(fixedRate = 360000)
    public void scheduleSincronizarDiarioNotaPedido2() {
        logger.error("Cron Task liberacion nota de pedido cada 5 minutos scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
        //AppParametria paramFechaInicial = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Inicio");
        //AppParametria paramFechaFinal = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Fin");
        Date dateAyer = new Date();
        Date dateManiana = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dateAyer);
        c.add(Calendar.DATE, -1);
        dateAyer = c.getTime();

        c.setTime(dateManiana);
        c.add(Calendar.DATE, 1);
        dateManiana = c.getTime();


        try {
            List<RangeSap> listaFecha = new ArrayList<RangeSap>();
            RangeSap range = new RangeSap();

            range.setSign("I");
            range.setOption("BT");
            range.setLow(format.format(dateAyer));
            range.setHigh(format.format(dateManiana));

            listaFecha.add(range);
            this.sapRfcDeltaService.integrarNotaPedido(null, listaFecha);

        }
        catch (Exception e) {
            logger.error("Cron Task Fin JOB liberacion nota de pedido cada 5 minutos scheduleSincronizarDiaria ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB liberacion nota de pedido cada 5 minutos scheduleSincronizarDiaria");
    }*/

    @Scheduled(fixedRate = 900000)
    public void scheduleSincronizarMateriales() {
        logger.error("Cron Task scheduleSincronizarMateriales 15 minutos scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
        //AppParametria paramFechaInicial = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Inicio");
        //AppParametria paramFechaFinal = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Fin");
        Date dateAyer = new Date();
        Date dateManiana = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dateAyer);
        c.add(Calendar.DATE, -1);
        dateAyer = c.getTime();

        c.setTime(dateManiana);
        c.add(Calendar.DATE, 1);
        dateManiana = c.getTime();


        try {
            //Lis<RangeSap> listaFecha = new ArrayList<RangeSap>();
            //RangeSap range = new RangeSap();

            //range.setSign("I");
            //range.setOption("BT");
            //range.setLow(format.format(dateAyer));
            //range.setHigh(format.format(dateManiana));

            //listaFecha.add(range);
            this.sapRfcDeltaService.integrarMateriales(format.format(dateAyer), format.format(dateManiana));

        } catch (Exception e) {
            logger.error("Cron Task JOB scheduleSincronizarMateriales cada 6 minutos scheduleSincronizarDiaria ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarMateriales cada 6 minutos scheduleSincronizarDiaria");
    }

    @Scheduled(fixedRate = 900000)
    public void scheduleSincronizarListaServicio() {
        logger.error("Cron Task scheduleSincronizarListaServicio 15 minutos scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");
        //AppParametria paramFechaInicial = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Inicio");
        //AppParametria paramFechaFinal = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Fecha_Consulta", "Fecha Fin");
        Date dateAyer = new Date();
        Date dateManiana = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(dateAyer);
        c.add(Calendar.DATE, -1);
        dateAyer = c.getTime();

        c.setTime(dateManiana);
        c.add(Calendar.DATE, 1);
        dateManiana = c.getTime();


        try {
            //Lis<RangeSap> listaFecha = new ArrayList<RangeSap>();
            //RangeSap range = new RangeSap();

            //range.setSign("I");
            //range.setOption("BT");
            //range.setLow(format.format(dateAyer));
            //range.setHigh(format.format(dateManiana));

            //listaFecha.add(range);
            this.sapRfcDeltaService.integrarServicios(format.format(dateAyer), format.format(dateManiana));

        } catch (Exception e) {
            logger.error("Cron Task scheduleSincronizarListaServicio cada 6 minutos scheduleSincronizarDiaria ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarListaServicio cada 6 minutos scheduleSincronizarDiaria");
    }

    // se jecuta a cada hora
    //@Scheduled(cron = "0 00 * * * ?")
    //@Scheduled(fixedRate = 360000)
    //@Scheduled(fixedRate = 60000)
    @Scheduled(cron = "0 15 * * * ?")
    public void scheduleSincronizarTipoCambio() {
        logger.error("scheduleSincronizarTipoCambio INI :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        List<MtrMoneda> listaMoneda = this.mtrMonedaDeltaRepository.findAll();
        logger.error("scheduleSincronizarTipoCambio listaMoneda size: " + listaMoneda.size());
        logger.error("scheduleSincronizarTipoCambio listaMoneda: " + listaMoneda.toString());
        try {
            Date fechaHoy = DateUtils.obtenerFechaHoraActualMasSegundos(1);
            Date fecha20diasAtras = DateUtils.sumarRestarDias(fechaHoy, -20);
            logger.error("scheduleSincronizarTipoCambio fechaHoy: " + fechaHoy);
            logger.error("scheduleSincronizarTipoCambio fecha20diasAtras: " + fecha20diasAtras);
            boolean continuar = true;
            List<TipoCambioBean> lista = this.sapRfcDeltaService.listaTipoCambio(fechaHoy);
            if (lista != null && lista.size() > 0) {
                logger.error("scheduleSincronizarTipoCambio rest :: 1 lista.size" + lista.size());
                logger.error("scheduleSincronizarTipoCambio rest :: 1 " + lista);

                for (TipoCambioBean tipoCambio : lista) {

                    Optional<MtrMoneda> optionalMonedaExtranjera = listaMoneda.stream()
                            .filter(x -> tipoCambio.getMonedaextranjera().equalsIgnoreCase(x.getDescBrv()))
                            .findFirst();

                    Optional<MtrMoneda> optionalMonedLocal = listaMoneda.stream()
                            .filter(x -> tipoCambio.getMonedaLocal().equalsIgnoreCase(x.getDescBrv()))
                            .findFirst();

                    logger.error("scheduleSincronizarTipoCambio rest :: 2 ");
                    if (optionalMonedLocal.isPresent() && optionalMonedaExtranjera.isPresent()) {
                        logger.error("scheduleSincronizarTipoCambio rest :: 3 optionalMonedLocal.get()_1: " + optionalMonedLocal.get());
                        logger.error("scheduleSincronizarTipoCambio rest :: 3 optionalMonedaExtranjera.get()_2: " + optionalMonedaExtranjera.get());
                        MtrTasaCambio tasaCambio = new MtrTasaCambio();
                        MtrTasaCambio mtrTasaCambioBuscar = this.mtrTasaCambioDeltaRepository.
                                getByFechaTasaAndMtrMonedaOrigenAndMtrMonedaDestino(
                                        tipoCambio.getFecha(),
                                        optionalMonedLocal.get(),
                                        optionalMonedaExtranjera.get()
                                );
                        if (Optional.ofNullable(mtrTasaCambioBuscar).isPresent()) {
                            tasaCambio = mtrTasaCambioBuscar;
                            logger.error("scheduleSincronizarTipoCambio rest mtrTasaCambioBuscar encontrado UPDATE");
                        }
                        else {
                            tasaCambio.setMtrMonedaOrigen(optionalMonedLocal.get());
                            tasaCambio.setMtrMonedaDestino(optionalMonedaExtranjera.get());
                            tasaCambio.setFechaTasa(tipoCambio.getFecha());
                            logger.error("scheduleSincronizarTipoCambio rest mtrTasaCambioBuscar NO encontrado INSERT");
                        }

                        tasaCambio.setValor(tipoCambio.getTipoCambio().setScale(4, BigDecimal.ROUND_HALF_EVEN));


                        logger.error("scheduleSincronizarTipoCambio rest :: 4 tasaCambio: " + tasaCambio.toString());
                        this.mtrTasaCambioDeltaRepository.save(tasaCambio);
                        logger.error("scheduleSincronizarTipoCambio rest :: 5 ");
                    }
                }

            }
            else {
                logger.error("Cron Task_1 scheduleSincronizarTipoCambio rest :: 1 lista.size VACIO de ERP");
            }
            logger.error("scheduleSincronizarTipoCambio rest :: 6");
            //}


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("scheduleSincronizarTipoCambio ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("scheduleSincronizarTipoCambio FIN");
    }

    // Ejecutar todos los dias a las 6 am
    @Scheduled(cron = "0 00 06 * * ?")
    public void scheduleSincronizarCuentaMayor() {
        logger.error("Cron Task scheduleSincronizarCuentaMayor todos los días a las 6 de la mañana :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));


        try {

            this.sapRfcDeltaService.integrarCuentaMayor(0, 0, "");

        } catch (Exception e) {
            logger.error("Cron Task scheduleSincronizarCuentaMayor todos los días a las 6 de la mañana ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarCuentaMayor todos los días a las 6 de la mañana");
    }


    // Ejecutar todos los dias a las 6 am
    @Scheduled(cron = "0 00 06 * * ?")
    public void scheduleSincronizarCuentaImputacionK() {
        logger.error("Cron Task scheduleSincronizarCuentaImputacion_K todos los días a las 6 de la mañana :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));


        try {

            this.sapRfcDeltaService.integrarCuentaImputacion(0, 0, "K", "");


        } catch (Exception e) {
            logger.error("Cron Task scheduleSincronizarCuentaImputacion_K todos los días a las 6 de la mañana ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarCuentaImputacion_K todos los días a las 6 de la mañana");
    }

    @Scheduled(cron = "0 00 06 * * ?")
    public void scheduleSincronizarCuentaImputacionP() {
        logger.error("Cron Task scheduleSincronizarCuentaImputacion_P todos los días a las 6 de la mañana :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));


        try {

            this.sapRfcDeltaService.integrarCuentaImputacion(0, 0, "P", "");


        } catch (Exception e) {
            logger.error("Cron Task scheduleSincronizarCuentaImputacion_P todos los días a las 6 de la mañana ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarCuentaImputacion_P todos los días a las 6 de la mañana");
    }

    @Scheduled(cron = "0 00 06 * * ?")
    public void scheduleSincronizarCuentaImputacionF() {
        logger.error("Cron Task scheduleSincronizarCuentaImputacion_F todos los días a las 6 de la mañana :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));


        try {

            this.sapRfcDeltaService.integrarCuentaImputacion(0, 0, "F", "");


        } catch (Exception e) {
            logger.error("Cron Task scheduleSincronizarCuentaImputacion_F todos los días a las 6 de la mañana ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarCuentaImputacion_F todos los días a las 6 de la mañana");
    }


    @Scheduled(cron = "0 00 07 * * ?")
    public void scheduleEnviarCorreosAprobacionFacturacion() {
        logger.error("Cron Task scheduleEnviarCorreosAprobacionFacturacion Lu-Mie-Vier a las 7am:: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            if (this.activarJobCorreo) {
                this.facFacturaDeltaService.jobEnviarCorreoFacturaAprobador0();
                this.facFacturaDeltaService.jobEnviarCorreoFacturaFirmante();
                this.cerCertificadoDeltaService.jobEnviarCorreoCertificadoSinAprobar();
            }
        } catch (Exception e) {
            logger.error("Cron Task scheduleEnviarCorreosAprobacionFacturacion Lu-Mie-Vier a las 7am: ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleEnviarCorreosAprobacionFacturacion Lu-Mie-Vier a las 7am:");
    }


//==================


/*@Scheduled(fixedRate = 300000)
public void scheduleObtenerComprobanteSap() {
    logger.error("Cron Task scheduleObtenerComprobanteSap 5 minutos scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

    try {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");

        AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");
        AppParametria estado = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "Estado");

        Date dateHoy = DateUtils.obtenerFechaActual();
        Date dateAyer = DateUtils.sumarRestarDias(dateHoy, -1);



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

        List<ComprobantePagoSap> lista = this.sapRfcDeltaService.obtenerComprobantesPago(listaIndicador,
                listaFecha, listaClaseDoc);
        logger.error("obtenerComprobanteSap rest :: if(lista != null) { ");
        if(lista != null) {
            logger.error("obtenerComprobanteSap rest :: 1 ");
            for(ComprobantePagoSap ele: lista) {
                logger.error("obtenerComprobanteSap rest :: 2 ");
                List<FacFactura> listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorNumeroFacturaSap(ele.getBelnr().trim());
                FacFactura factura = null;
                if(listaFact != null && listaFact.size() > 0) {
                    factura = listaFact.get(0);
                    logger.error("obtenerComprobanteSap rest :: 3 ");
                }else {
                    logger.error("obtenerComprobanteSap rest :: 4 " + ele.getXblnr() );
                    String [] arrCode = ele.getXblnr().trim().split("-");
                    String tipoComprobante = arrCode[0];
                    String serie = arrCode[1];
                    String numeroFactura = arrCode[2];


                    //ele.getGjahr() -- aino
                    listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorCodigoUnico(numeroFactura,serie,tipoComprobante,ele.getStcd1().trim(), ele.getGjahr().trim());
                    if(listaFact != null && listaFact.size() > 0) {
                        factura = listaFact.get(0);

                    }


                }
                logger.error("obtenerComprobanteSap rest :: 5 ");
                if(factura != null) {
                    logger.error("obtenerComprobanteSap rest :: 5_1 ");

                    MtrEstado objEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(estado.getValue1(), estado.getValue2());
                    //=================================================================
                    logger.error("obtenerComprobanteSap rest :: 6");

                    factura.setMtrEstado(objEstado);
                    factura.setDocumentoPagoSap(ele.getAugbl());
                    if(ele.getAugdt() != null) {
                        logger.error("obtenerComprobanteSap ele.getAugdt() :: 6_1 " + ele.getAugdt());
                        factura.setFechaPago(ele.getAugdt());
                    }

                    FacFactura facx = this.facFacturaDeltaRepository.save(factura);
                    //==================================================Historial
                    FacHistorial facHistorial = new FacHistorial();
                    facHistorial.setDescripcion("Documento pagado por SAP");
                    facHistorial.setMtrEstado(objEstado);
                    facHistorial.setFechaHistorial(DateUtils.obtenerFechaHoraActual());
                    facHistorial.setFacFactura(facx);
                    facHistorial.setUsuarioHistorial("SAP");
                    this.facHistorialDeltaRepository.save(facHistorial);
                    logger.error("obtenerComprobanteSap rest :: 6_1");
                }

            }
        }

    }
    catch (Exception e) {
        logger.error("Cron Task scheduleObtenerComprobanteSap cada 5 minutos scheduleObtenerComprobanteSap ERROR: " + Utils.obtieneMensajeErrorException(e));
    }
    logger.error("Cron Task Fin JOB scheduleObtenerComprobanteSap cada 5 minutos scheduleObtenerComprobanteSap");
}*/

    @Scheduled(cron = "0 15 * ? * * ")
    public void scheduleObtenerComprobanteSapV2() {
        logger.error("Cron Task scheduleObtenerComprobanteSapV2 5 minutos scheduleSincronizarDiaria :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        List<ComprobantePagoSap> lista = null;
        try {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd");

            //AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");
            AppParametria estado = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "Estado");
            List<AppParametria> claseDocumento = appParametriaDeltaRepository.findByModuloAndLabelAndStatus("Consulta_Comprobante", "CLASE_DOCUMENTO", "1");
            AppParametria sociedad = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "SOCIEDAD");
            // AppParametria claseDocumento = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("Consulta_Comprobante", "CLASE_DOCUMENTO");

            Date dateHoy = DateUtils.obtenerFechaActual();
            Date dateAyer = DateUtils.sumarRestarDias(dateHoy, -7);


            List<IndicadorFiscalSap> listaIndicador = new ArrayList<>();


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

            lista = this.sapRfcDeltaService.obtenerComprobantesPagoV2(listaRangeSociedad,
                    listaClaseDoc, listaFecha);
            logger.error("scheduleObtenerComprobanteSapV2 rest :: if(lista != null) { ");
            if (lista != null) {
                logger.error("scheduleObtenerComprobanteSapV2 rest :: 1 ");
                for (ComprobantePagoSap ele : lista) {

                    //List<FacFactura> listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorNumeroFacturaSap(ele.getBelnr().trim());
                    List<FacFactura> listaFact = null;
                    FacFactura factura = null;
                    /*if(listaFact != null && listaFact.size() > 0) {

                        logger.error("scheduleObtenerComprobanteSapV2 rest :: 3 ");
                    }else {*/
                    logger.error("scheduleObtenerComprobanteSapV2 rest  :: 4 " + ele.getXblnr() + " ---- " + ele.getGjahr() + " ---- " + ele.getStcd1());
                    String[] arrCode = ele.getXblnr().trim().split("-");
                    String tipoComprobante = arrCode[0];
                    String serie = arrCode[1];
                    String numeroFactura = arrCode[2];


                    //ele.getGjahr() -- aino
                    listaFact = this.facFacturaDeltaRepository.devuelveFacturaPorCodigoUnico(numeroFactura, serie, tipoComprobante, ele.getStcd1().trim(), ele.getGjahr().trim());
                    if (listaFact != null && listaFact.size() > 0) {
                        factura = listaFact.get(0);
                        logger.error("scheduleObtenerComprobanteSapV2 rest :: unico " + ele.getBelnr());

                    }


                    // }
                    logger.error("scheduleObtenerComprobanteSapV2 rest :: 5 ");
                    if (factura != null && StringUtils.isBlank(factura.getDocumentoPagoSap())) {
                        logger.error("scheduleObtenerComprobanteSapV2 rest :: 5_1 " + factura.getId());

                        MtrEstado objEstado = this.mtrEstadoDeltaRepository.getByCodigoAgrupadoAndCodigoEstado(estado.getValue1(), estado.getValue2());
                        //=================================================================
                        logger.error("scheduleObtenerComprobanteSapV2 rest :: 6");

                        factura.setMtrEstado(objEstado);
                        factura.setDocumentoPagoSap(ele.getAugbl());
                        if (ele.getAugdt() != null) {
                            logger.error("scheduleObtenerComprobanteSapV2 ele.getAugdt() :: 6_1 " + ele.getAugdt());
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
                        logger.error("scheduleObtenerComprobanteSapV2 rest :: 6_1");
                    }else {
                        AppProcesoLog appProcesoLog = new AppProcesoLog();
                        appProcesoLog.setModulo("Job_Comprobante_SAPNOT");
                        appProcesoLog.setEstadoEjecucion("Error");
                        appProcesoLog.setResultadoSalida("No hay comprobante o No hay documento Pago");
                        if(lista != null)
                            appProcesoLog.setParametroEntrada(lista.toString().substring(0,3988));
                        //this.appProcesoLogDeltaRepository.save(appProcesoLog);
                    }

                }
            }

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            logger.error("Cron Task scheduleObtenerComprobanteSapV2 cada 5 minutos scheduleObtenerComprobanteSap ERROR: " + error);
            AppProcesoLog appProcesoLog = new AppProcesoLog();
            appProcesoLog.setModulo("Job_Comprobante_SAP");
            appProcesoLog.setEstadoEjecucion("Error");
            appProcesoLog.setResultadoSalida(error);
            if(lista != null)
                appProcesoLog.setParametroEntrada(lista.toString().substring(0,3988));
            this.appProcesoLogDeltaRepository.save(appProcesoLog);
        }
        logger.error("Cron Task Fin JOB scheduleObtenerComprobanteSapV2 cada 5 minutos scheduleObtenerComprobanteSap");
    }

}