package com.incloud.hcp.rest._framework;

import com.incloud.hcp.common.BindingErrorsResponse;
import com.incloud.hcp.common.excel.ExcelTypeEnum;
import com.incloud.hcp.domain.BaseDomain;
import com.incloud.hcp.domain.BaseResponseDomain;
import com.incloud.hcp.repository._framework.JPACustomRepository;
import com.incloud.hcp.service._framework.JPACustomService;
import com.incloud.hcp.service._framework.bean.BeanCargaMasivoDTO;
import com.incloud.hcp.service._framework.bean.BeanListaMasivoDTO;
import com.incloud.hcp.service.support.PageRequestByExample;
import com.incloud.hcp.service.support.PageResponse;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class JPACustomRest<R extends BaseResponseDomain, T extends BaseDomain, I> extends BaseRest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final String NOMBRE_CLASE = this.getClass().getSimpleName();


    @Value("${prefijo.sistema.web}")
    protected String prefijoSistemaWeb;

    @Autowired
    protected JPACustomRepository<T, I> mainRepository;

    @Autowired
    protected JPACustomService<R, T, I> mainService;


    /*****************************/
    /* Metodos de Busqueda       */
    /*****************************/

    /**
     * Devuelve lista sin paginacion en base a los parametros ingresados
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo <T> en base a los parámetros ingresados (Usando Mybatis)", produces = "application/json")
    @PostMapping(value = "/findMybatis", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findMybatis(@RequestBody T bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando findMybatis by:" + bean.toString());
        try {
            return Optional.ofNullable(this.mainService.findMybatis(bean)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**obtenercapacitacionParaCreacion
     * Find by por Id
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<T> findById(@PathVariable I id) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findById: " + id);
        try {
            return Optional.ofNullable(this.mainService.findOne(id).get()).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find All sin paginacion.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo <T>", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findAll() throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findAll");
        try {
            return Optional.ofNullable(this.mainService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "Devuelve lista de registros de tipo <T> en base a los parámetros ingresados", produces = "application/json")
    @PostMapping(value = "/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> find(@RequestBody T bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando find by:" + bean.toString());
        try {
            bean = this.setPrevioFind(bean);
            return Optional.ofNullable(this.mainService.find(bean)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    protected T setPrevioFind(T bean) {
        return bean;
    }

    /**
     * Find paginado using query by example.
     */
    @ApiOperation(value = "Devuelve lista de registros paginados de tipo <T>", produces = "application/json")
    @PostMapping(value = "/findPaginated", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<T>> findPaginated(
            @RequestBody PageRequestByExample<T> prbe, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando findPaginated prbe: " + prbe.toString());
        try {
            prbe = this.setPrevioFindPaginated(prbe);
            PageResponse<T> pageResponse = this.mainService.findPaginated(prbe);
            return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    protected PageRequestByExample<T> setPrevioFindPaginated(PageRequestByExample<T> prbe) {
        return prbe;
    }


    @ApiOperation(value = "Devuelve lista de registros de tipo <T> en base a los parámetros y condiciones ingresados", produces = "application/json")
    @PostMapping(value = "/findCondicion", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findCondicion(@RequestBody R bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando findCondicion by:" + bean.toString());
        try {
            bean = this.setPrevioFindCondicion(bean);
            return Optional.ofNullable(this.mainService.findCondicion(bean)).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    protected R setPrevioFindCondicion(R bean) {
        return bean;
    }


    @ApiOperation(value = "Devuelve lista de registros de tipo <T> en base a los parámetros y condiciones ingresados en forma Paginada", produces = "application/json")
    @PostMapping(value = "/findCondicionPaginated", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<T>> findCondicionPaginated(@RequestBody R bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        if (!Optional.ofNullable(bean.getPageRequest()).isPresent()) {
            String errorDevuelve = "Debe ingresar Bean de Paginación (PageRequest)";
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        PageRequest pageRequest = bean.getPageRequest().toPageRequest();
        log.debug(this.NOMBRE_CLASE + " - Ingresando findCondicionPaginated by:" + bean.toString());
        try {
            bean = this.setPrevioFindCondicion(bean);
            PageResponse<T> result = this.mainService.findCondicionPaginated(bean, pageRequest);
            return Optional.ofNullable(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }




    /***************************************/
    /* Metodos de Busqueda Personalizados  */
    /***************************************/

    @ApiOperation(value = "Devuelve lista de registros en base al Campo y su valor (Contains)", produces = "application/json")
    @GetMapping(value = "/_findByCampoContainsText/{attributeName}/{valor}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCampoContainsText(
            @PathVariable String attributeName,
            @PathVariable String valor
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCampoContainsText");
        try {
            return Optional.ofNullable(this.mainRepository.findByAttributeContainsText(attributeName,valor))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    
    @ApiOperation(value = "Devuelve lista de registros en base al Campo y su valor (Left Contains)", produces = "application/json")
    @GetMapping(value = "/_findByCampoLeftContainsText/{attributeName}/{valor}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCampoLeftContainsText(
            @PathVariable String attributeName,
            @PathVariable String valor
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCampoLeftContainsText");
        try {
            return Optional.ofNullable(this.mainRepository.findByAttributeLeftContainsText(attributeName,valor))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    
    @ApiOperation(value = "Devuelve lista de registros en base al Campo y su valor (Right Contains)", produces = "application/json")
    @GetMapping(value = "/_findByCampoRightContainsText/{attributeName}/{valor}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCampoRightContainsText(
            @PathVariable String attributeName,
            @PathVariable String valor
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCampoRightContainsText");
        try {
            return Optional.ofNullable(this.mainRepository.findByAttributeRightContainsText(attributeName,valor))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    
    @ApiOperation(value = "Devuelve lista de registros en base al Campo y su valor Date (String en formato dd-mm-yyyy)", produces = "application/json")
    @GetMapping(value = "/_findByCampoBetweenDate/{attributeName}/{valor}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCampoBetweenDate(
            @PathVariable String attributeName,
            @PathVariable String valor
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCampoBetweenDate");
        try {
            return Optional.ofNullable(this.mainRepository.findByAttributeBetweenDate(attributeName,valor))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    
    @ApiOperation(value = "Devuelve lista de registros en base al Campo y su valor LocalDateTime (String en formato dd-mm-yyyy)", produces = "application/json")
    @GetMapping(value = "/_findByCampoBetweenLocalDateTime/{attributeName}/{valor}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCampoBetweenLocalDateTime(
            @PathVariable String attributeName,
            @PathVariable String valor
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCampoBetweenLocalDateTime");
        try {
            return Optional.ofNullable(this.mainRepository.findByAttributeBetweenLocalDateTime(attributeName,valor))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


    /*******************************************/
    /* Metodos de Busqueda de Campos Auditoria */
    /*******************************************/

    /**
     * Find by por createdBy
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base al campo createdBy", produces = "application/json")
    @GetMapping(value = "/_findByCreatedBy/{createdBy}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCreatedBy(@PathVariable String createdBy) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCreatedBy: " + createdBy);
        try {
            return Optional.ofNullable(this.mainRepository.findByCreatedBy(createdBy)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find by por modifiedBy
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base al campo modifiedBy", produces = "application/json")
    @GetMapping(value = "/_findByModifiedBy/{modifiedBy}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByModifiedBy(@PathVariable String modifiedBy) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByModifiedBy: " + modifiedBy);
        try {
            return Optional.ofNullable(this.mainRepository.findByModifiedBy(modifiedBy)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find by por createdDate
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base al campo createdDate (Formato String dd-MM-yyyy)", produces = "application/json")
    @GetMapping(value = "/_findByCreatedDate/{createdDate}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByCreatedDate(@PathVariable String createdDate) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByCreatedDate: " + createdDate);
        try {
            return Optional.ofNullable(this.mainRepository.findByCreatedDateBetween(createdDate)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find by por modifiedDate
     */
    @ApiOperation(value = "Busca registro de tipo <T> en base al campo modifiedDate (Formato String dd-MM-yyyy)", produces = "application/json")
    @GetMapping(value = "/_findByModifiedDate/{modifiedDate}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findByModifiedDate(@PathVariable String modifiedDate) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findByModifiedDate: " + modifiedDate);
        try {
            return Optional.ofNullable(this.mainRepository.findByModifiedDateBetween(modifiedDate)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Busca registro de tipo <T> ordenado por createBy (Paginado)", produces = "application/json")
    @GetMapping(value = "/_findAllPaginatedOrderCreatedBy/{page}/{size}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<T>> findAllPaginatedOrderCreatedBy(
            @PathVariable Integer page,
            @PathVariable Integer size) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando _findAllPaginatedOrderCreatedBy: " );
        try {
            return Optional.ofNullable(this.mainService.findAllPaginatedSortCreatedDate(
                     page.intValue(), size.intValue())).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }


    @ApiOperation(value = "Busca registro de tipo <T> a traves del tiempo en base a la tabla de Auditoria", produces = "application/json")
    @GetMapping(value = "/findAuditoria", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findAuditoria() throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findAuditoria: " );
        try {
            return Optional.ofNullable(this.mainRepository.findAuditQuery()).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Busca registro de tipo <T> a traves del tiempo en base a la tabla de Auditoria por Fechas Historicas (Formato String dd-MM-yyyy)", produces = "application/json")
    @GetMapping(value = "/findAuditoriaBetweenDate/{fechaInicio}/{fechaFin}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> findAuditoriaBetweenDate(
            @PathVariable String fechaInicio,
            @PathVariable String fechaFin
    ) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando findAuditoriaBetweenDate: " );
        try {
            return Optional.ofNullable(this.mainRepository.findAuditQueryBetweenDate(fechaInicio, fechaFin)).map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }



    /****************/
    /* METODOS CRUD */
    /****************/

    /**
     * Create a new.
     */
    @ApiOperation(value = "Crea un nuevo registro de tipo <T>", produces = "application/json")
    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<T> create(@RequestBody @Valid T bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando Create: " + bean.toString());
        if (bean.isIdSet()) {
            String error = "No se pudo crear ya que posee ID Existente";
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(error);
            }
            return ResponseEntity.badRequest().header("Failure", "Cannot create with existing ID").body(null);
        }
        try {
            T result = this.mainService.create(bean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update entity
     */
    @ApiOperation(value = "Modifica o crea registro de tipo <T>", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<T> save(@RequestBody @Valid T bean, BindingResult bindingResult) throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando save: " + bean.toString());
        try {
            T result = this.mainService.save(bean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update entity
     */
    @ApiOperation(value = "Modifica o crea registro de tipo <T>", produces = "application/json")
    @PostMapping(value = "/saveSinValid", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<T> saveSinValid(@RequestBody T bean) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        log.debug(this.NOMBRE_CLASE + " - Ingresando save: " + bean.toString());
        try {
            T result = this.mainService.save(bean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete by id
     */
    @ApiOperation(value = "Elimina registro de tipo <T>", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable I id) throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Delete by id: ", id);
        try {
            this.mainService.delete(id);
            String result = "OK";
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }

            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Delete en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo <T>", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<T> deleteAll() throws URISyntaxException {
        log.debug(this.NOMBRE_CLASE + " - Ingresando deleteAll");
        try {
            this.mainService.deleteAll();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }


    /************************/
    /* METODOS CRUD Masivos */
    /************************/
    
    @ApiOperation(value = "Inserta registros de tipo <T> en base a un Excel de Carga", produces = "application/json")
    @PostMapping(value = "/uploadExcel", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BeanCargaMasivoDTO<T>>> uploadExcel(@RequestParam("file") MultipartFile file) {
        log.debug(this.NOMBRE_CLASE + " - Ingresando uploadExcel");
        try {
            InputStream in = file.getInputStream();
            List<BeanCargaMasivoDTO<T>> result = this.mainService.uploadExcel(in);

            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created en forma Masiva en misma Transaction
     */
    @ApiOperation(value = "Modifica o crea registro de tipo <T> en forma Masiva en misma Transaction", produces = "application/json")
    @PostMapping(value = "/saveAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> saveAll(
            @RequestBody @Valid List<T> listaBean, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando saveAll");
        try {
            List<T> result = this.mainService.saveAll(listaBean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }


    /**
     * Update or created en forma Masiva en New Required Transaction
     */
    @ApiOperation(value = "Modifica o crea registro de tipo <T> en forma Masiva en New Required Transaction", produces = "application/json")
    @PostMapping(value = "/saveAllNewRequired", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BeanListaMasivoDTO<BeanCargaMasivoDTO<T>>> saveAllNewRequired(
            @RequestBody @Valid List<T> listaBean, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando saveAllNewRequired");
        try {
            BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> result = this.mainService.saveAllNewRequired(listaBean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }



    /**
     * Delete en forma masiva en New Required Transaction
     */
    @ApiOperation(value = "Elimina los registros de tipo <T> de forma masiva en New Required Transaction", produces = "application/json")
    @PostMapping(value = "/deleteAllNewRequired", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BeanListaMasivoDTO<BeanCargaMasivoDTO<T>>> deleteAllNewRequired(
            @RequestBody @Valid List<T> listaBean, BindingResult bindingResult)
            throws URISyntaxException {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors()) {
            String errorDevuelve = this.devuelveErrorHeaders(bindingResult, errors);
            if (this.devuelveRuntimeException) {
                throw new RuntimeException(errorDevuelve);
            }
            headers.add("errors", errorDevuelve);
        }
        log.debug(this.NOMBRE_CLASE + " - Ingresando deleteAllNewRequired");
        try {
            BeanListaMasivoDTO<BeanCargaMasivoDTO<T>> result = this.mainService.deleteAllNewRequired(listaBean);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }



    /*****************************/
    /* Metodos que generan Excel */
    /*****************************/

    @ApiOperation(value = "Genera Excel XLS de registros de tipo <T>", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/downloadCompleteExcel", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadCompleteExcel(HttpServletResponse response) {
        T bean = this.createInstance();
        log.debug(this.NOMBRE_CLASE + " - Ingresando downloadCompleteExcel");
        String nombreArchivo = this.setObtenerNombreArchivoExcel();
        ServletOutputStream out = null;
        try {
            HSSFWorkbook book = this.mainService.downloadExcel(bean);
            if (book == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            book.write(baos);
            response.setContentLength(baos.size());
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s%s\"", nombreArchivo, ExcelTypeEnum.XLS.getExtension()));
            out = response.getOutputStream();

            book.write(out);
            out.flush();
            baos.flush();
            book.close();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            if (this.devuelveRuntimeException) {
                String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
                throw new RuntimeException(error);
            }
            HttpHeaders headers = this.devuelveErrorHeaders(e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ioe) {
                    //  ioe.printStackTrace();
                }
            }
        }

    }


    @ApiOperation(value = "Genera Excel XLSX de registros de tipo <T>", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/downloadCompleteExcelSXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadCompleteExcelSXLSX(
            HttpServletResponse response) {
        T bean = this.createInstance();

        log.debug(this.NOMBRE_CLASE + " - Ingresando downloadCompleteExcelSXLSX by <T>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String nombreArchivo = this.setObtenerNombreArchivoExcel();
        String excelFileName = nombreArchivo + "_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.mainService.downloadExcelSXLSX(bean);
        ByteArrayOutputStream outByteStream;
        byte[] outArray;
        try {
            outByteStream = new ByteArrayOutputStream();
            book.write(outByteStream);
            outArray = outByteStream.toByteArray();
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);


            book.dispose();
            book.close();

            outStream.flush();
        } catch (FileNotFoundException e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        } catch (IOException e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Genera insert Excel XLSX de registros de tipo <T>", produces = "application/vnd.ms-excel")
    @GetMapping(value = "/generateInsertExcelSXLSX", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> generateInsertExcelSXLSX(HttpServletResponse response) {
        T bean = this.createInstance();
        log.debug(this.NOMBRE_CLASE + " - Ingresando generateInsertExcelSXLSX by <T>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String nombreArchivo = this.setObtenerNombreArchivoExcel();
        String excelFileName = "INSERT_" + nombreArchivo + "_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook book = this.mainService.generateInsertExcelSXLSX(bean);

        try {
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            book.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();

            book.dispose();
            book.close();
        } catch (FileNotFoundException e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        } catch (IOException e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            e.printStackTrace();
            throw new RuntimeException(error);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    protected abstract String setObtenerNombreArchivoExcel();



    /************************/
    /* Instancia de Bean    */
    /************************/
    protected abstract T createInstance();

//    private T createInstance() {
//        try {
//            Type sooper = getClass().getGenericSuperclass();
//            Type t = ((ParameterizedType)sooper).getActualTypeArguments()[ 0 ];
//            return (T)(Class.forName( t.toString() ).newInstance());
//        }
//        catch( Exception e ) {
//            return null;
//        }
//    }

}
