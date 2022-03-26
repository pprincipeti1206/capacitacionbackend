package com.incloud.hcp.mapper._gproveedor;


import com.incloud.hcp.domain._gproveedor.bean.ProveedorCustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 06/09/2017.
 */
@Mapper
@Repository
public interface ProveedorMapper {
    List<ProveedorCustom> getListProveedorByRuc(String ruc);

    List<ProveedorCustom> getListProveedorByFiltro(
            @Param("idsPais") ArrayList<String> idsPais,
            @Param("razonSocial")String razonSocial,
            @Param("idsRegion") ArrayList<String> idsRegion,
            @Param("idsProvincia") ArrayList<String> idsProvincia,
            @Param("nroRuc") String nroRuc,
            @Param("tipoProveedor") String tipoProveedor,
            @Param("tipoPersona") String tipoPersona,
            @Param("indHomologado") String indHomologado,
            @Param("indPorValidarInfoAcreedor") String indPorValidarInfoAcreedor,
            @Param("marca") String marca,
            @Param("producto") String producto,
            @Param("descripcionAdicional") String descripcionAdicional,
            @Param("idsLinea") ArrayList<String> idsLinea,
            @Param("idsFamilia") ArrayList<String> idsFamilia,
            @Param("idsSubFamilia") ArrayList<String> idsSubFamilia,
            @Param("estadoProveedor") String estadoProveedor
    );

    List<ProveedorCustom> getListProveedorByFiltroPaginado(
            @Param("idsPais") ArrayList<String> idsPais,
            @Param("razonSocial")String razonSocial,
            @Param("idsRegion") ArrayList<String> idsRegion,
            @Param("idsProvincia") ArrayList<String> idsProvincia,
            @Param("nroRuc") String nroRuc,
            @Param("tipoProveedor") String tipoProveedor,
            @Param("tipoPersona") String tipoPersona,
            @Param("indHomologado") String indHomologado,
            @Param("indPorValidarInfoAcreedor") String indPorValidarInfoAcreedor,
            @Param("marca") String marca,
            @Param("producto") String producto,
            @Param("descripcionAdicional") String descripcionAdicional,
            @Param("idsLinea") ArrayList<String> idsLinea,
            @Param("idsFamilia") ArrayList<String> idsFamilia,
            @Param("idsSubFamilia") ArrayList<String> idsSubFamilia,
            @Param("estadoProveedor") String estadoProveedor,
            @Param("nroRegistros") Integer nroRegistros,
            @Param("paginaMostrar") Integer paginaMostrar
    );

    List<ProveedorCustom> getListProveedorMigrados();

    List<ProveedorCustom> getListProveedorByFiltroValidacion(
            @Param("idsPais") ArrayList<String> idsPais,
            @Param("idsRegion") ArrayList<String> idsRegion,
            @Param("idsProvincia") ArrayList<String> idsProvincia,
            @Param("nroRuc") String nroRuc,
            @Param("razonSocial") String razonSocial,
            @Param("tipoProveedor") String tipoProveedor,
            @Param("tipoPersona") String tipoPersona,
            @Param("indHomologado") String indHomologado,
            @Param("indPorValidarInfoAcreedor") String indPorValidarInfoAcreedor,
            @Param("marca") String marca,
            @Param("producto") String producto,
            @Param("descripcionAdicional") String descripcionAdicional,
            @Param("idsLinea") ArrayList<String> idsLinea,
            @Param("idsFamilia") ArrayList<String> idsFamilia,
            @Param("idsSubFamilia") ArrayList<String> idsSubFamilia
    );

    List<ProveedorCustom> getListProveedorByFiltroLicitacion(
            @Param("idsPais") ArrayList<String> idsPais,
            @Param("razonSocial")String razonSocial,
            @Param("idsRegion") ArrayList<String> idsRegion,
            @Param("idsProvincia") ArrayList<String> idsProvincia,
            @Param("nroRuc") String nroRuc,
            @Param("tipoProveedor") String tipoProveedor,
            @Param("tipoPersona") String tipoPersona,
            @Param("indHomologado") String indHomologado,
            @Param("indPorValidarInfoAcreedor") String indPorValidarInfoAcreedor,
            @Param("marca") String marca,
            @Param("producto") String producto,
            @Param("descripcionAdicional") String descripcionAdicional,
            @Param("idsLinea") ArrayList<String> idsLinea,
            @Param("idsFamilia") ArrayList<String> idsFamilia,
            @Param("idsSubFamilia") ArrayList<String> idsSubFamilia
    );

    List<ProveedorCustom> getListProveedorByFiltroLicitacionPaginado(
            @Param("idsPais") ArrayList<String> idsPais,
            @Param("razonSocial")String razonSocial,
            @Param("idsRegion") ArrayList<String> idsRegion,
            @Param("idsProvincia") ArrayList<String> idsProvincia,
            @Param("nroRuc") String nroRuc,
            @Param("tipoProveedor") String tipoProveedor,
            @Param("tipoPersona") String tipoPersona,
            @Param("indHomologado") String indHomologado,
            @Param("indPorValidarInfoAcreedor") String indPorValidarInfoAcreedor,
            @Param("marca") String marca,
            @Param("producto") String producto,
            @Param("descripcionAdicional") String descripcionAdicional,
            @Param("idsLinea") ArrayList<String> idsLinea,
            @Param("idsFamilia") ArrayList<String> idsFamilia,
            @Param("idsSubFamilia") ArrayList<String> idsSubFamilia,
            @Param("nroRegistros") Integer nroRegistros,
            @Param("paginaMostrar") Integer paginaMostrar
    );

    void updateEstadoProveedor(@Param("idProveedor") Integer idProveedor,
                               @Param("idEstadoProveedor") Integer idEstadoProveedor);

    /**
     * CC. Nuevo Flujo de Aprobaciones
     * */

    void saveStatusSupplier(
            @Param("idProveedor") Integer idProveedor,
            @Param("idEstadoProveedor") Integer idEstadoProveedor,
            @Param("enFlujoAprobacion") String enFlujoAprobacion
    );

    void saveStatusSupplierInfo(
            @Param("idProveedor") Integer idProveedor,
            @Param("idEstadoProveedor") Integer idEstadoProveedor,
            @Param("codigoMaximoEstadoAprobado") String codigoMaximoEstadoAprobado,
            @Param("indHomologado") String indHomologado,
            @Param("cantidadFlujosCompletados") Integer cantidadFlujosCompletados,
            @Param("enFlujoAprobacion") String enFlujoAprobacion

    );

}
