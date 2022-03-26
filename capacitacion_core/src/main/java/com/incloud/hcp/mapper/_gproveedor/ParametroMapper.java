package com.incloud.hcp.mapper._gproveedor;

import com.incloud.hcp._gproveedor.sap.SapSetting;
import com.incloud.hcp.domain._gproveedor.domain.Parametro;
import com.incloud.hcp.service.notificacion.MailSetting;
import com.incloud.hcp.utils._gproveedor.constant.ParametroConstant;
import com.incloud.hcp.utils._gproveedor.constant.ParametroTipoConstant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrador on 23/08/2017.
 */
@Mapper
@Repository
public interface ParametroMapper {
    List<Parametro> getListParametro(@Param("parametro") ParametroConstant parametro,
                                     @Param("tipo") ParametroTipoConstant tipo);
    List<Parametro> getListParametrobyModuloandTipo(@Param("modulo") String modulo,
                                     @Param("tipo") String tipo);

    MailSetting getMailSetting();

    SapSetting getSapSetting();
}
