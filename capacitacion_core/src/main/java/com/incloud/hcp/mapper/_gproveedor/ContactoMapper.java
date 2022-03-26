package com.incloud.hcp.mapper._gproveedor;


import com.incloud.hcp.service._gproveedor.dto.CanalContactoDto;
import com.incloud.hcp.service._gproveedor.dto.reporte.SucursalDtoExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrador on 23/08/2017.
 */
@Mapper
@Repository
public interface ContactoMapper {
    List<CanalContactoDto> getListContactoByIdProveedor(@Param("idProveedor") Integer idProveedor);

    List<SucursalDtoExcel> getListAllSucursalContactoProveedor();
}
