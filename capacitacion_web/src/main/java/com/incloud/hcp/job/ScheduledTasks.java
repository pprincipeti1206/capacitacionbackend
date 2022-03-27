package com.incloud.hcp.job;


import com.incloud.hcp.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");






    @Value("${activar.job.correo}")
    protected boolean activarJobCorreo;



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
            //this.sapRfcDeltaService.integrarMateriales(format.format(dateAyer), format.format(dateManiana));

        } catch (Exception e) {
            logger.error("Cron Task JOB scheduleSincronizarMateriales cada 6 minutos scheduleSincronizarDiaria ERROR: " + Utils.obtieneMensajeErrorException(e));
        }
        logger.error("Cron Task Fin JOB scheduleSincronizarMateriales cada 6 minutos scheduleSincronizarDiaria");
    }


}