package com.incloud.hcp.mail.rest;

import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp.bean.custom.SendNotifySupplier;
import com.incloud.hcp.bean.custom.SendNotifyUserDto;
import com.incloud.hcp.domain.Response;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._gproveedor.ProveedorService;
import com.incloud.hcp.service._gproveedor.notificacion.NotificacionFlujoAprobacion;
import com.incloud.hcp.service.notificacion.EmailNotificacionGeneral;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/unit-test/email")
public class EmailRest extends AppRest {

    @Autowired private NotificacionFlujoAprobacion notificacionFlujoAprobacion;
    @Autowired private EmailNotificacionGeneral emailNotificacionGeneral;
    @Autowired private ProveedorService proveedorService;

    @ApiOperation( "Servicio para notificar a un proveedor según el estado de aprobación - Unit Test" )
    @PostMapping(value = "/notify-supplier-by-status/{Ruc}",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> notifySupplierByStatus(
            @RequestBody SendNotifySupplier sendNotifySupplier,
            @PathVariable( "Ruc" ) String ruc
            ) throws PortalException {

        Response<String> response = new Response<>();

        try{

            //--- Armamos la data del proveedor
            sendNotifySupplier.setSupplier( proveedorService.getProveedorByRuc( ruc ) );

            notificacionFlujoAprobacion.sendNotifySupplier( sendNotifySupplier );
            String o = "Éxito";
            response.ok( true, o );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( "Servicio para notificar los siguientes aprobadores - Unit Test" )
    @PostMapping(value = "/notify-user-by-status-supplier/{Ruc}",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> notifyUserByStatusSupplier(
            @RequestBody SendNotifyUserDto sendNotifyUserDto,
            @PathVariable( "Ruc" ) String ruc
    ) throws PortalException {

        Response<String> response = new Response<>();

        try{

            //--- Armamos la data del proveedor
            sendNotifyUserDto.setSupplier( proveedorService.getProveedorByRuc( ruc ) );

            notificacionFlujoAprobacion.sendNotifyUser( sendNotifyUserDto );
            String o = "Éxito";
            response.ok( true, o );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( "Servicio para notificar a los usuarios de soporte cuando el proveedor no se crea en SAP - Unit Test" )
    @PostMapping(value = "/notify-support-user-error-sap/{Ruc}",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> notifySupportUserErrorSap(
            @PathVariable( "Ruc" ) String ruc,
            @RequestBody ProveedorRFCResponseDto proveedorRFCResponseDto
    ) throws PortalException {

        Response<String> response = new Response<>();

        try{

            //--- Armamos la data del proveedor
            proveedorRFCResponseDto.setProveedorSap( proveedorService.getProveedorByRuc( ruc ) );

            notificacionFlujoAprobacion.sendNotifyTeamSuport( proveedorRFCResponseDto );
            String o = "Éxito";
            response.ok( true, o );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( "Servicio para enviar un correo - Unit Test" )
    @PostMapping(value = "/send-email-unit-test",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> sendEmailUnitTest(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestPart MultipartFile body
            ) throws PortalException {

        Response<String> response = new Response<>();

        try{

            String html = new String( body.getBytes(), StandardCharsets.UTF_8 );

            emailNotificacionGeneral.enviarCorreoSap( to, subject, html );
            String o = "Éxito";
            response.ok( true, o );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

}
