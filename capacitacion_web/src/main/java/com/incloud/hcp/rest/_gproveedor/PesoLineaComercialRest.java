/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp.common.BindingErrorsResponse;
import com.incloud.hcp.common.excel.ExcelTypeEnum;
import com.incloud.hcp.domain._gproveedor.domain.PesoLineaComercial;
import com.incloud.hcp.repository._gproveedor.PesoLineaComercialRepository;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._gproveedor.delta.PesoLineaComercialDeltaService;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.service.support.PageResponse;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class PesoLineaComercialRest extends AppRest {
    private final Logger log = LoggerFactory.getLogger(PesoLineaComercialRest.class);

    @Autowired
    protected PesoLineaComercialRepository pesoLineaComercialRepository;

    @Autowired
    protected PesoLineaComercialDeltaService pesoLineaComercialDeltaService;

    /**
    * Find by id PesoLineaComercial.
    */
    @ApiOperation(value = "Busca registro de tipo PesoLineaComercial en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PesoLineaComercial> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id PesoLineaComercial : {}", id);
        try {
            return Optional.ofNullable(this.pesoLineaComercialDeltaService.findOne(id).get())
                    .map(pesoLineaComercial -> new ResponseEntity<>(pesoLineaComercial, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
    * Find All sin paginacion PesoLineaComercial.
    */
    @ApiOperation(value = "Devuelve lista de registros de tipo PesoLineaComercial", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PesoLineaComercial>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.pesoLineaComercialDeltaService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
    * Devuelve lista sin paginacion en base a los parametros ingresados en PesoLineaComercial.
    */
    @ApiOperation(value = "Devuelve lista de registros de tipo PesoLineaComercial en base a los parámetros ingresados", produces = "application/json")
    @PostMapping(value = "/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PesoLineaComercial>> find(@RequestBody PesoLineaComercial bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando find by PesoLineaComercial : {}", bean);
        try {
            return Optional.of(this.pesoLineaComercialDeltaService.find(bean)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find a Page of PesoLineaComercial using query by example.
     */
    @ApiOperation(value = "Devuelve lista de registros paginados de tipo PesoLineaComercial", produces = "application/json")
    @PostMapping(value = "/findPaginated", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<PesoLineaComercial>> findPaginated(@RequestBody PageRequestByExample<PesoLineaComercial> prbe,
                                                                          BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando findPaginated by PageRequestByExample : {}", prbe);
        try {
            PageResponse<PesoLineaComercial> pageResponse = this.pesoLineaComercialDeltaService.findPaginated(prbe);
            return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Genera Excel XLSX de registros de tipo PesoLineaComercial", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/downloadCompleteExcelXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadCompleteExcelXLSX(HttpServletResponse response) {
        PesoLineaComercial bean = new PesoLineaComercial();
        log.debug("Ingresando downloadCompleteExcelXLSX by PesoLineaComercial : {}", bean);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String excelFileName = "PesoLineaComercial_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.pesoLineaComercialDeltaService.downloadExcelSXLSX(bean);

        try {
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            book.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType(ExcelTypeEnum.XLSX.getExtension());
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();

            book.dispose();
            book.close();
        } catch (FileNotFoundException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        } catch (IOException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Genera insert Excel XLSX de registros de tipo PesoLineaComercial", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/generateInsertExcelSXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> generateInsertExcelSXLSX(HttpServletResponse response) {
        PesoLineaComercial bean = new PesoLineaComercial();
        log.debug("Ingresando generateInsertExcelSXLSX by PesoLineaComercial : {}", bean);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String excelFileName = "INSERT_PesoLineaComercial_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.pesoLineaComercialDeltaService.generateInsertExcelSXLSX(bean);

        try {
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            book.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentType(ExcelTypeEnum.XLSX.getExtension());
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();

            book.dispose();
            book.close();
        } catch (FileNotFoundException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        } catch (IOException e) {
            String error = Utils.obtieneMensajeErrorException(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create a new PesoLineaComercial.
     */
    @ApiOperation(value = "Crea un nuevo registro de tipo PesoLineaComercial", produces = "application/json")
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PesoLineaComercial> create(@RequestBody @Valid PesoLineaComercial pesoLineaComercial, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando Create PesoLineaComercialRest : {}", pesoLineaComercial);
        if (pesoLineaComercial.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create PesoLineaComercial with existing ID").body(null);
        }
        try {
            PesoLineaComercial result = this.pesoLineaComercialDeltaService.create(pesoLineaComercial);
            return ResponseEntity.created(new URI("pesoLineaComercials/" + result.getId())).body(result);
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created PesoLineaComercial.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo PesoLineaComercial", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PesoLineaComercial> save(@RequestBody @Valid PesoLineaComercial pesoLineaComercial, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando Update PesoLineaComercialRest : {}", pesoLineaComercial);
        try {
            PesoLineaComercial result = this.pesoLineaComercialDeltaService.save(pesoLineaComercial);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id PesoLineaComercial.
     */
    @ApiOperation(value = "Elimina registro de tipo PesoLineaComercial", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id PesoLineaComercial : {}", id);
        try {
            this.pesoLineaComercialDeltaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely 
            String error = Utils.obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete PesoLineaComercial en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo PesoLineaComercial", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll PesoLineaComercialRest");
        try {
            this.pesoLineaComercialDeltaService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    protected HttpHeaders devuelveErrorHeaders(Exception e) {
        String msjError = Utils.obtieneMensajeErrorException(e);
        HttpHeaders headers = new HttpHeaders();
        headers.add("errors", Utils.obtieneMensajeErrorException(e));
        return headers;
    }

}