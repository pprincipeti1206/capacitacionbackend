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
import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.FacFacturaCertificado;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Simple Interface for FacFacturaCertificado.
 */
@Data
@ToString
@EqualsAndHashCode
public class FacFacturaCertificadoResponse extends BaseResponseDomain<FacFacturaCertificado> {

    /****************************/
    /* Variables de Condiciones */
    /****************************/

    private String idCondicion;
    private String estadoCondicion;
    //private String cerCertificadoCondicion;
    //private String facFacturaCondicion;

    /****************************/
    /* Variables Listas         */
    /****************************/

    private List<Integer> idList;
    private List<String> estadoList;
    private List<CerCertificado> cerCertificadoList;
    private List<FacFactura> facFacturaList;

}
