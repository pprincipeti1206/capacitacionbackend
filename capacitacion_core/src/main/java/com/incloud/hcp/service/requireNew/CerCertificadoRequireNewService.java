/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntityrequirenewDTO.java.e.vm
 */
package com.incloud.hcp.service.requireNew;

import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.CerCertificadoDetalleSap;
import com.incloud.hcp.domain.CerFirma;
import com.incloud.hcp.domain.CerHistorial;
import com.incloud.hcp.service._framework.JPACustomRequiredNewService;
import com.incloud.hcp.service.dto.CerCertificadoDetalleDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Simple Interface for CerCertificado para Carga Masiva.
 */
public interface CerCertificadoRequireNewService extends JPACustomRequiredNewService<CerCertificado> {

    CompletableFuture<List<CerCertificadoDetalleDto>> getCerCertificadoDetalleDtoListComplete(CerCertificado cerCertificado) throws Exception;
    CompletableFuture<List<CerFirma>> getCerFirmasComplete(CerCertificado cerCertificado) throws Exception;
    CompletableFuture<List<CerCertificadoDetalleSap>> getCerCertificadoDetalleSapListComplete(Integer idCertificado) throws Exception ;

    CompletableFuture<List<CerHistorial>> getCerHistorialListConglomeradoComplete(CerCertificado cerCertificadoBuscar) throws Exception;

}
