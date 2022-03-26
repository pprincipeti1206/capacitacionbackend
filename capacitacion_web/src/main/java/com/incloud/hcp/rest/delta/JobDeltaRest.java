package com.incloud.hcp.rest.delta;

import com.incloud.hcp.service.delta.CerNotaPedidoDeltaService;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/job")
public class JobDeltaRest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CerNotaPedidoDeltaService cerNotaPedidoDeltaService;


    @ApiOperation(value = "Actualiza a NO VIGENTE las notas de Pedido", produces = "application/json")
    @GetMapping(value = "/notaPedido/actualizarNoVigente", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarNoVigenteNotaPedido() throws URISyntaxException {
        log.debug("Ingresando actualizarNoVigenteNotaPedido");
        try {
            this.cerNotaPedidoDeltaService.actualizarNoVigente();
            return Optional.ofNullable("ok").map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }
}
