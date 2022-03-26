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

import com.incloud.hcp.common.BindingErrorsResponse;
import com.incloud.hcp.common.graph.GraphBean;
import com.incloud.hcp.domain.*;
import com.incloud.hcp.domain.response.MtrEstrategiaFacturacionResponse;
import com.incloud.hcp.repository.delta.MtrEstrategiaFacturacionDeltaRepository;
import com.incloud.hcp.rest._framework.JPACustomRest;
import com.incloud.hcp.service.delta.MtrEstrategiaFacturacionDeltaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class MtrEstrategiaFacturacionRest extends JPACustomRest<MtrEstrategiaFacturacionResponse, MtrEstrategiaFacturacion, Integer> {

    @Autowired
    protected MtrEstrategiaFacturacionDeltaService mtrEstrategiaFacturacionDeltaService;

    @Autowired
    protected MtrEstrategiaFacturacionDeltaRepository mtrEstrategiaFacturacionDeltaRepository;

    protected String setObtenerNombreArchivoExcel() {
        return "MtrEstrategiaFacturacion";
    }

    /************************/
    /* Instancia de Bean    */
    /************************/
    protected final MtrEstrategiaFacturacion createInstance() {
        MtrEstrategiaFacturacion mtrEstrategiaFacturacion = new MtrEstrategiaFacturacion();
        return mtrEstrategiaFacturacion;
    }

    /*****************/
    /* Otros Metodos */
    /*****************/

    /**
    * Contador de registros para el atributo mtrRangoMontoEstrategia.
    */
    @ApiOperation(value = "Contador de registros para el atributo mtrRangoMontoEstrategia", produces = "application/json")
    @PostMapping(value = "/countByMtrRangoMontoEstrategia", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> countByMtrRangoMontoEstrategia(@RequestBody @Valid MtrRangoMontoEstrategia mtrRangoMontoEstrategia, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errors.toJSON());
            }
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug("Ingresando countByMtrRangoMontoEstrategia");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.countByMtrRangoMontoEstrategia(mtrRangoMontoEstrategia))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Genera Grafico de registros para el atributo mtrRangoMontoEstrategia.
    */
    @ApiOperation(value = "Genera Grafico de registros para el atributo mtrRangoMontoEstrategia", produces = "application/json")
    @GetMapping(value = "/graphByMtrRangoMontoEstrategia", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GraphBean> graphByMtrRangoMontoEstrategia() throws URISyntaxException {
        log.debug("Ingresando graphByMtrRangoMontoEstrategia");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.graphByMtrRangoMontoEstrategia())
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Contador de registros para el atributo mtrTipoContrato.
    */
    @ApiOperation(value = "Contador de registros para el atributo mtrTipoContrato", produces = "application/json")
    @PostMapping(value = "/countByMtrTipoContrato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> countByMtrTipoContrato(@RequestBody @Valid MtrTipoContrato mtrTipoContrato, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errors.toJSON());
            }
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug("Ingresando countByMtrTipoContrato");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.countByMtrTipoContrato(mtrTipoContrato))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Genera Grafico de registros para el atributo mtrTipoContrato.
    */
    @ApiOperation(value = "Genera Grafico de registros para el atributo mtrTipoContrato", produces = "application/json")
    @GetMapping(value = "/graphByMtrTipoContrato", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GraphBean> graphByMtrTipoContrato() throws URISyntaxException {
        log.debug("Ingresando graphByMtrTipoContrato");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.graphByMtrTipoContrato()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Contador de registros para el atributo mtrSociedad.
    */
    @ApiOperation(value = "Contador de registros para el atributo mtrSociedad", produces = "application/json")
    @PostMapping(value = "/countByMtrSociedad", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> countByMtrSociedad(@RequestBody @Valid MtrSociedad mtrSociedad, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errors.toJSON());
            }
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug("Ingresando countByMtrSociedad");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.countByMtrSociedad(mtrSociedad))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Genera Grafico de registros para el atributo mtrSociedad.
    */
    @ApiOperation(value = "Genera Grafico de registros para el atributo mtrSociedad", produces = "application/json")
    @GetMapping(value = "/graphByMtrSociedad", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GraphBean> graphByMtrSociedad() throws URISyntaxException {
        log.debug("Ingresando graphByMtrSociedad");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.graphByMtrSociedad()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Contador de registros para el atributo mtrSector.
    */
    @ApiOperation(value = "Contador de registros para el atributo mtrSector", produces = "application/json")
    @PostMapping(value = "/countByMtrSector", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> countByMtrSector(@RequestBody @Valid MtrSector mtrSector, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errors.toJSON());
            }
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug("Ingresando countByMtrSector");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.countByMtrSector(mtrSector)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
    * Genera Grafico de registros para el atributo mtrSector.
    */
    @ApiOperation(value = "Genera Grafico de registros para el atributo mtrSector", produces = "application/json")
    @GetMapping(value = "/graphByMtrSector", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GraphBean> graphByMtrSector() throws URISyntaxException {
        log.debug("Ingresando graphByMtrSector");
        try {
            return Optional.ofNullable(this.mtrEstrategiaFacturacionDeltaService.graphByMtrSector()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(e);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

}