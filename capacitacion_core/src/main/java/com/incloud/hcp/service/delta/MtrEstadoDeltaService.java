/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntitydeltaDTO.java.e.vm
 */
package com.incloud.hcp.service.delta;

import com.incloud.hcp.domain.MtrEstado;
import com.incloud.hcp.service.MtrEstadoService;

import java.util.List;

/**
 * Simple Interface for MtrEstado.
 */
public interface MtrEstadoDeltaService extends MtrEstadoService {

    List<MtrEstado> findEstadoFacturaRegistrado() throws Exception;
    List<MtrEstado> findEstadoFacturaPublicado() throws Exception;
    List<MtrEstado> findEstadoFacturaCupa() throws Exception;



}