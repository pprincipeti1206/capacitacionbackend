package com.incloud.hcp.rest._inicio;


import com.incloud.hcp.CapacitacionApplication;
import com.incloud.hcp.rest._framework.BaseRest;
import com.incloud.hcp.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/*@RestController
@RequestMapping("/api/welcome")*/
public class _WelcomeRest extends BaseRest{

    private final Logger log = LoggerFactory.getLogger(_WelcomeRest.class);

    @ApiOperation(value = "Vuelve a Restaurar la Aplicacion, que deploye de nuevo")
    @PostMapping("/restart")
    public void restart() {
        CapacitacionApplication.restart();
    }

    @ApiOperation(value = "Devuelve OK para ser utilizado en Amazon AWS para que determine la salud de la Aplicacion")
    @GetMapping(value = "/checkStatus")
    public String checkStatus() {
        return "ok";
    }

    @ApiOperation(value = "Detecta el tipo de dispositivo utilizado")
    @GetMapping("/detect")
    public String detect(Device device, Model model) {

        String deviceType = null;
        if (device.isMobile()) {
            deviceType = "Mobile";
        } else if (device.isTablet()) {
            deviceType = "Tablet";
        } else {
            deviceType = "Desktop";
        }
        System.out.println("Hello User, you are viewing this application on " + deviceType);
        log.info("Hello User, you are viewing this application on " + deviceType);
        model.addAttribute("deviceType", deviceType);
        return deviceType;
    }

    /**
     * Invocacion Rest al BackEnd
     */
    @ApiOperation(value = "Obtiene fecha Actual v01", produces = "application/json")
    @RequestMapping(value = "/fecha Actual", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fechaActual() throws URISyntaxException {
        log.debug("Find by fechaActual");
        try {
            Date fecha = DateUtils.obtenerFechaHoraActual();
            String sFecha = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", fecha);
            return Optional.ofNullable(sFecha).map(aauditTrail -> new ResponseEntity<>(aauditTrail, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Invocacion Rest al BackEnd
     */
    @ApiOperation(value = "Obtiene fecha Actual + minutos", produces = "application/json")
    @RequestMapping(value = "/fechaActualmasMinutos", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fechaActualmasMinutos() throws URISyntaxException {
        log.debug("Find by fechaActualmasMinutos");
        try {
            Date fecha = DateUtils.obtenerFechaHoraActualv02();
            String sFecha = DateUtils.convertDateToString("dd/MM/yyyy HH:mm:ss", fecha);
            return Optional.ofNullable(sFecha).map(aauditTrail -> new ResponseEntity<>(aauditTrail, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Invocacion Rest al BackEnd
     */
    @ApiOperation(value = "Welcome REST OK", produces = "application/json")
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ok() throws URISyntaxException {
        log.debug("Find by welcome");
        try {
            String welcome = "Hola ingresaste al BackEnd";
            return Optional.ofNullable(welcome).map(aauditTrail -> new ResponseEntity<>(aauditTrail, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Welcome REST Error", produces = "application/json")
    @RequestMapping(value = "/error", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> error() throws URISyntaxException {
        try {
            String error = "Error ingresaste al BackEnd";
            throw new Exception(error);

        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


}
