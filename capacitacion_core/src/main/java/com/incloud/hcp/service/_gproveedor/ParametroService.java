package com.incloud.hcp.service._gproveedor;


import com.incloud.hcp._gproveedor.sap.SapSetting;
import com.incloud.hcp.domain._gproveedor.bean.Area;
import com.incloud.hcp.domain._gproveedor.bean.TipoCuenta;
import com.incloud.hcp.domain._gproveedor.domain.Parametro;
import com.incloud.hcp.service.notificacion.MailSetting;

import java.util.List;

/**
 * Created by Administrador on 23/08/2017.
 */
public interface ParametroService {

    List<Area> getListAreaAll();

    List<TipoCuenta> getListTipoCuentaAll();

    MailSetting getMailSetting();

    SapSetting getSapSetting();

    List<Parametro> getByModuloandTipo(String modulo, String tipo);

}
