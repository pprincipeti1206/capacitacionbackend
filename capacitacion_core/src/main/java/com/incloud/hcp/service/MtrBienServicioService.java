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
import com.incloud.hcp.domain.MtrBienServicio;
import com.incloud.hcp.domain.MtrGrupoArticulo;
import com.incloud.hcp.domain.MtrUnidadMedida;
import com.incloud.hcp.domain.response.MtrBienServicioResponse;
import com.incloud.hcp.service._framework.JPACustomService;

/**
 * Simple Interface for MtrBienServicio.
 */
public interface MtrBienServicioService extends JPACustomService<MtrBienServicioResponse, MtrBienServicio, Integer> {

    Long countByMtrGrupoArticulo(MtrGrupoArticulo mtrGrupoArticulo);

    GraphBean graphByMtrGrupoArticulo();

    Long countByMtrUnidadMedida(MtrUnidadMedida mtrUnidadMedida);

    GraphBean graphByMtrUnidadMedida();

}
