/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/domain/EntityResponse.java.e.vm
 */
package com.incloud.hcp.domain.response;

import com.incloud.hcp.domain.BaseResponseDomain;
import com.incloud.hcp.domain.CerNotaPedido;
import com.incloud.hcp.domain.CerNotaPedidoHistorial;
import com.incloud.hcp.domain.MtrEstado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Simple Interface for CerNotaPedidoHistorial.
 */
@Data
@ToString
@EqualsAndHashCode
public class CerNotaPedidoHistorialResponse extends BaseResponseDomain<CerNotaPedidoHistorial> {

    /****************************/
    /* Variables de Condiciones */
    /****************************/

    private String idCondicion;
    private String descripcionCondicion;
    private String usuarioHistoralCondicion;
    private String fechaHistorialCondicion;
    private String fechaFinVigenciaCondicion;
    private String plazoCondicion;
    //private String cerNotaPedidoCondicion;
    //private String mtrEstadoCondicion;

    /****************************/
    /* Variables Listas         */
    /****************************/

    private List<Integer> idList;
    private List<String> descripcionList;
    private List<String> usuarioHistoralList;
    private List<Date> fechaHistorialList;
    private List<Date> fechaFinVigenciaList;
    private List<Integer> plazoList;
    private List<CerNotaPedido> cerNotaPedidoList;
    private List<MtrEstado> mtrEstadoList;

}
