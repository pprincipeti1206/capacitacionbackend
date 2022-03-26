package com.incloud.hcp.service._gproveedor;

import com.incloud.hcp.domain._gproveedor.bean.LineaFamilia;
import com.incloud.hcp.domain._gproveedor.domain.LineaComercial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 28/08/2017.
 */
public interface LineaComercialService {

    LineaComercial getByIdLineaComercial(Integer idLineaComercial);

    List<LineaComercial> getListByIdParent(Integer idParent);

    List<LineaComercial> getListByNivel(Integer nivel);

    List<LineaComercial> getListByNivelAndWithoutIndGeneral(Integer nivel);

    List<LineaComercial> getListAll();

    List<LineaFamilia> getListFamiliaByIDs(ArrayList<String> ids);
}
