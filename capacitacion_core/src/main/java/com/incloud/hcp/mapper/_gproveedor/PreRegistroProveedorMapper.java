package com.incloud.hcp.mapper._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.PreRegistroProveedor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrador on 23/11/2017.
 */
@Mapper
@Repository
public interface PreRegistroProveedorMapper {
    void guardarSolicitud(@Param("registro") PreRegistroProveedor registro);
}
