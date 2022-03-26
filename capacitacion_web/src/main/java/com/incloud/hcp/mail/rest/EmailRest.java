package com.incloud.hcp.mail.rest;

import com.incloud.hcp.domain.Response;
import com.incloud.hcp.exception.PortalException;
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
public class EmailRest  {


    @Autowired private EmailNotificacionGeneral emailNotificacionGeneral;




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
