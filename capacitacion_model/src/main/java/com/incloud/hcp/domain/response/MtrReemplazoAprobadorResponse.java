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
import com.incloud.hcp.domain.MtrAprobador;
import com.incloud.hcp.domain.MtrReemplazoAprobador;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Simple Interface for MtrReemplazoAprobador.
 */
@Data
@ToString
@EqualsAndHashCode
public class MtrReemplazoAprobadorResponse extends BaseResponseDomain<MtrReemplazoAprobador> {

    /****************************/
    /* Variables de Condiciones */
    /****************************/

    private String idCondicion;
    private String fechaInicioCondicion;
    private String fechaFinCondicion;
    //private String mtrAprobadorCondicion;
    //private String mtrReemplazoCondicion;

    /****************************/
    /* Variables Listas         */
    /****************************/

    private List<Integer> idList;
    private List<Date> fechaInicioList;
    private List<Date> fechaFinList;
    private List<MtrAprobador> mtrAprobadorList;
    private List<MtrAprobador> mtrReemplazoList;

}
