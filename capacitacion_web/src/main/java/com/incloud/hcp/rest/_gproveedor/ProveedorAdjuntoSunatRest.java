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
import com.incloud.hcp.domain._gproveedor.domain.ProveedorAdjuntoSunat;
import com.incloud.hcp.repository._gproveedor.ProveedorAdjuntoSunatRepository;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._gproveedor.delta.ProveedorAdjuntoSunatDeltaService;
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

public abstract class ProveedorAdjuntoSunatRest extends AppRest {
    private final Logger log = LoggerFactory.getLogger(ProveedorAdjuntoSunatRest.class);

    @Autowired
    protected ProveedorAdjuntoSunatRepository proveedorAdjuntoSunatRepository;

    @Autowired
    protected ProveedorAdjuntoSunatDeltaService proveedorAdjuntoSunatDeltaService;

    /**
    * Find by id ProveedorAdjuntoSunat.
    */
    @ApiOperation(value = "Busca registro de tipo ProveedorAdjuntoSunat en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorAdjuntoSunat> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id ProveedorAdjuntoSunat : {}", id);
        try {
            return Optional.ofNullable(this.proveedorAdjuntoSunatDeltaService.findOne(id).get())
                    .map(proveedorAdjuntoSunat -> new ResponseEntity<>(proveedorAdjuntoSunat, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
    * Find All sin paginacion ProveedorAdjuntoSunat.
    */
    @ApiOperation(value = "Devuelve lista de registros de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProveedorAdjuntoSunat>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.proveedorAdjuntoSunatDeltaService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
    * Devuelve lista sin paginacion en base a los parametros ingresados en ProveedorAdjuntoSunat.
    */
    @ApiOperation(value = "Devuelve lista de registros de tipo ProveedorAdjuntoSunat en base a los parámetros ingresados", produces = "application/json")
    @PostMapping(value = "/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProveedorAdjuntoSunat>> find(@RequestBody ProveedorAdjuntoSunat bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando find by ProveedorAdjuntoSunat : {}", bean);
        try {
            return Optional.of(this.proveedorAdjuntoSunatDeltaService.find(bean)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find a Page of ProveedorAdjuntoSunat using query by example.
     */
    @ApiOperation(value = "Devuelve lista de registros paginados de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @PostMapping(value = "/findPaginated", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ProveedorAdjuntoSunat>> findPaginated(@RequestBody PageRequestByExample<ProveedorAdjuntoSunat> prbe,
                                                                             BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando findPaginated by PageRequestByExample : {}", prbe);
        try {
            PageResponse<ProveedorAdjuntoSunat> pageResponse = this.proveedorAdjuntoSunatDeltaService.findPaginated(prbe);
            return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Genera Excel XLSX de registros de tipo ProveedorAdjuntoSunat", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/downloadCompleteExcelXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadCompleteExcelXLSX(HttpServletResponse response) {
        ProveedorAdjuntoSunat bean = new ProveedorAdjuntoSunat();
        log.debug("Ingresando downloadCompleteExcelXLSX by ProveedorAdjuntoSunat : {}", bean);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String excelFileName = "ProveedorAdjuntoSunat_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.proveedorAdjuntoSunatDeltaService.downloadExcelSXLSX(bean);

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

    @ApiOperation(value = "Genera insert Excel XLSX de registros de tipo ProveedorAdjuntoSunat", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/generateInsertExcelSXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> generateInsertExcelSXLSX(HttpServletResponse response) {
        ProveedorAdjuntoSunat bean = new ProveedorAdjuntoSunat();
        log.debug("Ingresando generateInsertExcelSXLSX by ProveedorAdjuntoSunat : {}", bean);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String excelFileName = "INSERT_ProveedorAdjuntoSunat_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.proveedorAdjuntoSunatDeltaService.generateInsertExcelSXLSX(bean);

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
     * Create a new ProveedorAdjuntoSunat.
     */
    @ApiOperation(value = "Crea un nuevo registro de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorAdjuntoSunat> create(@RequestBody @Valid ProveedorAdjuntoSunat proveedorAdjuntoSunat, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando Create ProveedorAdjuntoSunatRest : {}", proveedorAdjuntoSunat);
        if (proveedorAdjuntoSunat.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create ProveedorAdjuntoSunat with existing ID").body(null);
        }
        try {
            ProveedorAdjuntoSunat result = this.proveedorAdjuntoSunatDeltaService.create(proveedorAdjuntoSunat);
            return ResponseEntity.created(new URI("proveedorAdjuntoSunats/" + result.getId())).body(result);
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created ProveedorAdjuntoSunat.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProveedorAdjuntoSunat> save(@RequestBody @Valid ProveedorAdjuntoSunat proveedorAdjuntoSunat, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            errors.addAllErrors(bindingResult);
            throw new RuntimeException(errors.toJSON());
        }
        log.debug("Ingresando Update ProveedorAdjuntoSunatRest : {}", proveedorAdjuntoSunat);
        try {
            ProveedorAdjuntoSunat result = this.proveedorAdjuntoSunatDeltaService.save(proveedorAdjuntoSunat);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id ProveedorAdjuntoSunat.
     */
    @ApiOperation(value = "Elimina registro de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id ProveedorAdjuntoSunat : {}", id);
        try {
            this.proveedorAdjuntoSunatDeltaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely 
            String error = Utils.obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete ProveedorAdjuntoSunat en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo ProveedorAdjuntoSunat", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll ProveedorAdjuntoSunatRest");
        try {
            this.proveedorAdjuntoSunatDeltaService.deleteAll();
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