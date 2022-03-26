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

import com.incloud.hcp.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Simple Interface for MtrEstrategiaFacturacionFirma.
 */
@Data
@ToString
@EqualsAndHashCode
public class MtrEstrategiaFacturacionFirmaResponse extends BaseResponseDomain<MtrEstrategiaFacturacionFirma> {

    /****************************/
    /* Variables de Condiciones */
    /****************************/

    private String idCondicion;
    private String ordenEjecucionCondicion;
    //private String mtrEstrategiaFacturacionCondicion;
    //private String mtrTipoGerenciaCondicion;
    //private String mtrSectorCondicion;

    /****************************/
    /* Variables Listas         */
    /****************************/

    private List<Integer> idList;
    private List<Integer> ordenEjecucionList;
    private List<MtrEstrategiaFacturacion> mtrEstrategiaFacturacionList;
    private List<MtrTipoGerencia> mtrTipoGerenciaList;
    private List<MtrSector> mtrSectorList;

}