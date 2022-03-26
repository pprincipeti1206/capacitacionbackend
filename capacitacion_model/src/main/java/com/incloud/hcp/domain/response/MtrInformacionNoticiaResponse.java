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
import com.incloud.hcp.domain.MtrInformacionNoticia;
import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Simple Interface for MtrInformacionNoticia.
 */
@Data
@ToString
@EqualsAndHashCode
public class MtrInformacionNoticiaResponse extends BaseResponseDomain<MtrInformacionNoticia> {

    /****************************/
    /* Variables de Condiciones */
    /****************************/

    private String idCondicion;
    private String archivoIdCondicion;
    private String archivoNombreCondicion;
    private String contenidoCondicion;
    private String fechaCaducidadCondicion;
    private String fechaCreacionCondicion;
    private String fechaModificacionCondicion;
    private String fechaPublicacionCondicion;
    private String iconTextCondicion;
    private String indActivoCondicion;
    private String indNoticiaNuevoProveedorCondicion;
    private String rutaAdjuntoCondicion;
    private String textoInfoCondicion;
    private String tituloCondicion;
    private String usuarioCreacionCondicion;
    private String usuarioModificacionCondicion;
    //private String mtrTipoInformacionNoticiaCondicion;

    /****************************/
    /* Variables Listas         */
    /****************************/

    private List<Integer> idList;
    private List<String> archivoIdList;
    private List<String> archivoNombreList;
    private List<String> contenidoList;
    private List<Date> fechaCaducidadList;
    private List<Date> fechaCreacionList;
    private List<Date> fechaModificacionList;
    private List<Date> fechaPublicacionList;
    private List<String> iconTextList;
    private List<String> indActivoList;
    private List<String> indNoticiaNuevoProveedorList;
    private List<String> rutaAdjuntoList;
    private List<String> textoInfoList;
    private List<String> tituloList;
    private List<Integer> usuarioCreacionList;
    private List<Integer> usuarioModificacionList;
    private List<MtrTipoInformacionNoticia> mtrTipoInformacionNoticiaList;

}
