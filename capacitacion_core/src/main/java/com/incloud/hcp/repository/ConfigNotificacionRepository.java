package com.incloud.hcp.repository;


import com.incloud.hcp.domain.ConfigNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigNotificacionRepository extends JpaRepository<ConfigNotificacion, Integer> {

    @Query( value = "select o from ConfigNotificacion o order by o.id desc" )
    List<ConfigNotificacion> findAllNotifications();

    @Query( value = "select o from ConfigNotificacion o order by o.modifiedDate desc" )
    List<ConfigNotificacion> findNotificationsValidByNowDate( );

}