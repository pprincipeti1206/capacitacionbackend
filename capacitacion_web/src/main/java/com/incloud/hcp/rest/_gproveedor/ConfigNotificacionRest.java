package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp.domain.ConfigNotificacion;
import com.incloud.hcp.domain.Response;
import com.incloud.hcp.domain.response.SaveInformationResponse;
import com.incloud.hcp.service.requireNew.ConfigNotificacionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping( value = "/api-rest/config-notificacion" )
public class ConfigNotificacionRest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private ConfigNotificacionService service;

    @ApiOperation( value = "Servicio para obtener todas las notificaciones ordenadas de forma descendente por fecha de creación." )
    @GetMapping( value = "/find-all", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<Response<List<ConfigNotificacion>>> findAll(  ){

        Response<List<ConfigNotificacion>> response = new Response<>();

        try {

            List<ConfigNotificacion> o = service.findAll();
            response.ok( true, o, o.size() );

        }catch (Exception e) {

            logger.error( "Ocurrió un problema al obtener el listado de notificaciones: " + e.getMessage() );
            response.ok( e );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( value = "Servicio para guardar la información de la notificación." )
    @PostMapping( value = "/save", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Response<SaveInformationResponse>> save(
            @RequestBody ConfigNotificacion entity
    ){

        Response<SaveInformationResponse> response = new Response<>();

        try {

            SaveInformationResponse o = service.saveNotification( entity );
            response.ok( true, o );

        }catch (Exception e) {

            logger.error( "Ocurrió un problema al guardar la información de la notificación: " + e.getMessage() );
            response.ok( e );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( value = "Servicio para eliminar de forma masiva las notificacion con el id." )
    @DeleteMapping( value = "/delete-by-ids", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Response<SaveInformationResponse>> deleteByIds(
            @RequestBody List<Integer> ids
    ){

        Response<SaveInformationResponse> response = new Response<>();

        try {

            SaveInformationResponse o = service.deleteNotifications( ids );
            response.ok( true, o );

        }catch (Exception e) {

            logger.error( "Ocurrió un problema al guardar la información de la notificación: " + e.getMessage() );
            response.ok( e );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( value = "Servicio para mostrar las notificaciones en el portal" )
    @GetMapping( value = "/find-notifications", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<Response<List<ConfigNotificacion>>> findNotifications(  ){

        Response<List<ConfigNotificacion>> response = new Response<>();

        try {

            List<ConfigNotificacion> o = service.findNotifications( );
            response.ok( true, o );

            return new ResponseEntity<>( response, HttpStatus.OK );

        }catch (Exception e) {

            response.ok( e );

            logger.error( "Ocurrió un problema al obtener las notificaciones a mostrar en el portal : " + e.getMessage() );
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

}