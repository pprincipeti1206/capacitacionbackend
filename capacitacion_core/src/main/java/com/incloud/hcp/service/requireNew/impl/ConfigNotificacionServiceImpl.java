package com.incloud.hcp.service.requireNew.impl;

import com.incloud.hcp._security.SystemLoggedUser;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain.ConfigNotificacion;
import com.incloud.hcp.domain.response.SaveInformationResponse;
import com.incloud.hcp.enums.MessageTypeEnum;
import com.incloud.hcp.repository.ConfigNotificacionRepository;
import com.incloud.hcp.service.requireNew.ConfigNotificacionService;
import com.incloud.hcp.utils.MessageError;
import com.incloud.hcp.utils.UtilString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConfigNotificacionServiceImpl implements ConfigNotificacionService {


    @Autowired private ConfigNotificacionRepository repository;
    @Autowired private SystemLoggedUser systemLoggedUser;

    @Transactional( readOnly = true )
    @Override
    public List<ConfigNotificacion> findAll() {

        return repository.findAllNotifications();

    }

    @Transactional
    @Override
    public SaveInformationResponse saveNotification(ConfigNotificacion entity) {

        //--- Validaciones - Errores de programación
        MessageError.validaCampoObligatorio( entity, "Notificacion" );

        //--- Response
        SaveInformationResponse response = SaveInformationResponse.builder()
                .save( false )
                .build();

        //--- Validaciones - proceso
        if( UtilString.coalesceTrim( entity.getDenominacion() ).isEmpty() ||
            UtilString.coalesceTrim( entity.getMensaje() ).isEmpty() ||
            Objects.isNull( entity.getFechaInicioVigencia() )
        ){

            //--- Armamos la respuesta
            response.setMessageType( MessageTypeEnum.WARNING.name() );
            response.setMessage( "Para poder guardar la información de la notificación es necesario que los datos obligatorios estén completamente ingresados. Nombre, Mensaje, Fecha Inicio de Vigencia." );
            return response;

        }

        //--- Obtenemos el usuario de sessión
        UserSession user = systemLoggedUser.getUserSession();

        //--- Si se está creando un registro
        if( Objects.isNull( entity.getId() ) )
            entity.setCreatedBy( user.getDisplayName() );

        //--- Seteo de datos
        entity.setModifiedBy( user.getDisplayName() );
        entity.setModifiedDate( LocalDateTime.now() );
        repository.save( entity );

        //--- Armamos la respuesta
        response.setMessageType( MessageTypeEnum.SUCCESS.name() );
        response.setSave( true );
        response.setMessage( "Los datos de la notificación se guardaron exitosamente." );

        return response;

    }

    @Transactional
    @Override
    public SaveInformationResponse deleteNotifications(List<Integer> ids) {

        //--- Datos
        List<Integer> claves = Optional.ofNullable( ids ).orElse( new ArrayList<>() ).stream()
                .filter( Objects::nonNull )
                .distinct()
                .collect(Collectors.toList());

        //--- Response
        SaveInformationResponse response = SaveInformationResponse.builder()
                .save( false )
                .build();

        if( claves.isEmpty() ){

            response.setMessageType( MessageTypeEnum.INFORMATION.name() );
            response.setMessage( "No se seleccionó ninguna notificación para eliminar." );
            return response;
        }

        claves.forEach( repository::deleteById );

        response.setMessageType( MessageTypeEnum.SUCCESS.name() );
        response.setMessage( "La transacción se realizó correctamente. Cantidad Notificaciones eliminadas: " + claves.size() );
        response.setSave( true );

        return response;

    }

    @Transactional( readOnly = true )
    @Override
    public List<ConfigNotificacion> findNotifications() {

        //--- Obtenemos las notificaciones que tengan como fecha inicio con la fecha actual en adelante
        List<ConfigNotificacion> list = repository.findNotificationsValidByNowDate(  );

        //--- Obtenemos las notificaciones que no hayan expirado
        List<ConfigNotificacion> notificaciones = list.stream()
                .filter( o -> {

                    boolean notificacionValida = false;

                    if( new Date().getTime() >= o.getFechaInicioVigencia().getTime() ){

                        if( Objects.nonNull( o.getFechaFinVigencia() ) ){

                            if( new Date().getTime() <= o.getFechaFinVigencia().getTime() ){

                                notificacionValida = true;

                            }

                        }else
                            notificacionValida = true;

                    }

                    return notificacionValida;

                } )
                .collect( Collectors.toList() );

        return notificaciones;

    }

}