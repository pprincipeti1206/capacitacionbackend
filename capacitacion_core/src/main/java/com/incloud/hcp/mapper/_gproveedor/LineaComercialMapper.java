package com.incloud.hcp.mapper._gproveedor;


import com.incloud.hcp.domain._gproveedor.bean.LineaFamilia;
import com.incloud.hcp.service._gproveedor.dto.reporte.LineaComercialDtoExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 05/09/2017.
 */
@Mapper
@Repository
public interface LineaComercialMapper {

    List<LineaFamilia> getListFamiliaByIDs(@Param("ids") ArrayList<String> ids);

    List<LineaComercialDtoExcel> getListAllLineasComercialesProveedor();
}
