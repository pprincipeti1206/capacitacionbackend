package com.incloud.hcp.mapper._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.ProveedorHomologacion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrador on 19/09/2017.
 */
@Mapper
@Repository
public interface ProveedorHomologacionMapper {
    List<ProveedorHomologacion> getListProveedorHomologacionByIdProveedor(@Param("idProveedor") Integer idProveedor);

    //List<ProveedorHomologacion> getListHomologacionByIdProveedor(@Param("idProveedor") Integer idProveedor);
}
