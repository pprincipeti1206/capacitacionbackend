package com.incloud.hcp.mapper._gproveedor;


import com.incloud.hcp.domain._gproveedor.bean.CuentaBancaria;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import com.incloud.hcp.service._gproveedor.dto.reporte.CuentaBancoDtoExcel;
import com.incloud.hcp.utils._gproveedor.constant.ParametroConstant;
import com.incloud.hcp.utils._gproveedor.constant.ParametroTipoConstant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrador on 04/09/2017.
 */
@Mapper
@Repository
public interface CuentaBancariaMapper {
    List<CuentaBancaria> getListCuentaByIdProveedor(@Param("parametro") ParametroConstant parametro,
                                                    @Param("tipo") ParametroTipoConstant tipo,
                                                    @Param("idProveedor") Integer idProveedor);

    List<ProveedorCuentaBancaria> getListProveedorCuentaBancariaByIdProveedor(@Param("parametro") ParametroConstant parametro,
                                                                              @Param("tipo") ParametroTipoConstant tipo,
                                                                              @Param("idProveedor") Integer idProveedor);
    List<CuentaBancoDtoExcel> getListAllCuentasBancariasProveedor();
}
