package com.incloud.hcp.service._gproveedor.impl;

import com.incloud.hcp.domain._gproveedor.domain.PreRegistroProveedor;
import com.incloud.hcp.service._gproveedor.NotificacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrador on 05/09/2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class NotificacionServiceImpl implements NotificacionService {
    @Override
    public void proveedorRegistrado(PreRegistroProveedor preRegistroProveedor){
        // NOTIFICACION ENVIADA AL ADMIN
    }


}
