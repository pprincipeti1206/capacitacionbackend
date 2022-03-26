package com.incloud.hcp.service.requireNew;

import com.incloud.hcp.domain.ConfigNotificacion;
import com.incloud.hcp.domain.response.SaveInformationResponse;

import java.util.List;

public interface ConfigNotificacionService {

    /**
     * Mantenimiento
     * */
    List<ConfigNotificacion> findAll();
    SaveInformationResponse saveNotification(ConfigNotificacion entity );
    SaveInformationResponse deleteNotifications(List<Integer> ids );

    /**
     * Integración - BTP
     * */
    List<ConfigNotificacion> findNotifications(  );

}
