/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/rest/EntitydeltaResource.java.e.vm
 */
package com.incloud.hcp.rest._gproveedor.delta;

import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.proveedor.service.JCOProveedorService;
import com.incloud.hcp.rest._gproveedor.ProveedorClienteRest;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/proveedorCliente")
public class ProveedorClienteDeltaRest extends ProveedorClienteRest {

    //private final Logger log = LoggerFactory.getLogger(ProveedorClienteDeltaRest.class);
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JCOProveedorService jcoProveedorService;

    @ApiOperation(value = "Crear Proveedor RFC", produces = "application/json")
    @PostMapping(value = "/crearProveedor/{idProveedor}/{usuarioSap}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorRFCResponseDto> crearProveedorSap(@PathVariable Integer idProveedor, @PathVariable String usuarioSap) throws URISyntaxException {
        log.debug("Lista Centro Almacen : {}");
        try {
            return Optional.ofNullable(this.jcoProveedorService.grabarProveedor(idProveedor,usuarioSap))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

}