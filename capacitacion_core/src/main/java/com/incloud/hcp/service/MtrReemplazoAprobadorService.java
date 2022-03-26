/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.incloud.hcp.service;

import com.incloud.hcp.common.graph.GraphBean;
import com.incloud.hcp.domain.MtrAprobador;
import com.incloud.hcp.domain.MtrReemplazoAprobador;
import com.incloud.hcp.domain.response.MtrReemplazoAprobadorResponse;
import com.incloud.hcp.service._framework.JPACustomService;

/**
 * Simple Interface for MtrReemplazoAprobador.
 */
public interface MtrReemplazoAprobadorService extends JPACustomService<MtrReemplazoAprobadorResponse, MtrReemplazoAprobador, Integer> {

    Long countByMtrAprobador(MtrAprobador mtrAprobador);

    GraphBean graphByMtrAprobador();



}