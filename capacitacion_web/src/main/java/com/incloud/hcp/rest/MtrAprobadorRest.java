/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
package com.incloud.hcp.rest;

import com.incloud.hcp.domain.MtrAprobador;
import com.incloud.hcp.domain.response.MtrAprobadorResponse;
import com.incloud.hcp.repository.delta.MtrAprobadorDeltaRepository;
import com.incloud.hcp.rest._framework.JPACustomRest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class MtrAprobadorRest extends JPACustomRest<MtrAprobadorResponse, MtrAprobador, Integer> {

    @Autowired
    protected MtrAprobadorDeltaRepository mtrAprobadorDeltaRepository;

    protected String setObtenerNombreArchivoExcel() {
        return "MtrAprobador";
    }

    /************************/
    /* Instancia de Bean    */
    /************************/
    protected final MtrAprobador createInstance() {
        MtrAprobador mtrAprobador = new MtrAprobador();
        return mtrAprobador;
    }

    /**
     * Find by por aprobadorSap
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base a aprobadorSap", produces = "application/json")
    @GetMapping(value = "/_getByAprobadorSap/{aprobadorSap}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MtrAprobador> getByAprobadorSap(@PathVariable String aprobadorSap) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando getByAprobadorSap: " + aprobadorSap);
        try {
            return Optional.ofNullable(this.mtrAprobadorDeltaRepository.getByAprobadorSap(aprobadorSap)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find by por email
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base a email", produces = "application/json")
    @GetMapping(value = "/_getByEmail/{email}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MtrAprobador> getByEmail(@PathVariable String email) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando getByEmail: " + email);
        try {
            return Optional.ofNullable(this.mtrAprobadorDeltaRepository.getByEmail(email)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

}
