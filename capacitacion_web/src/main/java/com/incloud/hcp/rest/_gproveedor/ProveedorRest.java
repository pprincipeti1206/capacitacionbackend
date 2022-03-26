package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp._gproveedor.jco.consultaProveedor.service.JCOConsultaProveedorService;
import com.incloud.hcp._gproveedor.wsdl.inside.InSiteResponse;
import com.incloud.hcp._gproveedor.wsdl.inside.InSiteService;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.bean.custom.SaveApprovalInput;
import com.incloud.hcp.domain.Response;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorCustom;
import com.incloud.hcp.domain._gproveedor.bean.ProveedorFiltro;
import com.incloud.hcp.domain._gproveedor.domain.EstadoProveedor;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain.response.SaveInformationResponse;
import com.incloud.hcp.enums._gproveedor.EstadoProveedorEnum;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.EstadoProveedorRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.repository._gproveedor.TipoProveedorRepository;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._gproveedor.PreRegistroProveedorService;
import com.incloud.hcp.service._gproveedor.ProveedorService;
import com.incloud.hcp.service._gproveedor.UbigeoService;
import com.incloud.hcp.service._gproveedor.dto.*;
import com.incloud.hcp.service._gproveedor.facade.PreRegistroFacade;
import com.incloud.hcp.service._gproveedor.notificacion.ProveedorAcreedorRechazadoActualizacionNotificacion;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Administrador on 29/08/2017.
 */
@RestController
@RequestMapping(value = "/api/proveedor")
public class ProveedorRest extends AppRest {

    private final int NRO_EJECUCIONES = 10;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InSiteService inSiteService;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private PreRegistroProveedorService preRegistroProveedorService;

    @Autowired
    private JCOConsultaProveedorService jcoConsultaProveedorService;

    @Autowired
    private TipoProveedorRepository tipoProveedorRepository;

    @Autowired
    private UbigeoService ubigeoService;

    @Autowired
    private PreRegistroFacade preRegistroFacade;

    @Autowired
    private EstadoProveedorRepository estadoProveedorRepository;

    @Autowired
    private ProveedorAcreedorRechazadoActualizacionNotificacion proveedorAcreedorRechazadoActualizacionNotificacion;


    @RequestMapping(value = "",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> getProveedor(HttpServletRequest request) throws PortalException {
        logger.debug("[rest] /proveedor");
        logger.error("Ingresando GET PROVEEDOR");

        UserSession userSession = this.getUserSession();
        logger.error("Ingresando GET PROVEEDOR 000 ini userSession: " + userSession.toString());
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            logger.error("Ingresando GET PROVEEDOR 00 error");
            return this.processObject(new ProveedorDto());
        }
        logger.error("Ingresando GET PROVEEDOR 01");
        Optional<ProveedorDto> oProveedor = Optional.ofNullable(this.proveedorService.getProveedorDtoByRuc(userSession.getRuc()));
        logger.error("Ingresando GET PROVEEDOR 02");

        if (oProveedor.isPresent()) {
            logger.error("Ingresando GET PROVEEDOR 03");
            logger.error("Ingresando GET PROVEEDOR 03b oProveedor: " + oProveedor.get().toString());
            oProveedor.get().setIdHcp(userSession.getId());
            //Actualizar Datos del Ubigeo para los Migrados
            if(oProveedor.get().getIdEstadoProveedor().getCodigoEstadoProveedor().equals(EstadoProveedorEnum.MIGRADO_DE_SAP.getCodigo())){
                logger.error("Ingresando GET PROVEEDOR 04");
                Optional<PreRegistroProveedorDto> preRegistro = Optional.ofNullable(preRegistroFacade.getPreRegistroByIdHcp(userSession.getId()));
                //PreRegistroProveedorDto preRegistro = preRegistroFacade.getPreRegistroByIdHcp(userSession.getId());
                if(preRegistro.isPresent()){
                    logger.error("Ingresando GET PROVEEDOR 05");
                   // ProveedorDto temp = new ProveedorDto();
                    //BeanUtils.copyProperties(preRegistro, temp);
                    oProveedor.get().setDireccionFiscal(preRegistro.get().getDireccion());
                    oProveedor.get().setActivo(preRegistro.get().getActivo());
                    oProveedor.get().setHabido(preRegistro.get().getHabido());
                    oProveedor.get().setLineasComercial(preRegistro.get().getLineasComercial());
                    oProveedor.get().setCodigoSistemaEmisionElect(preRegistro.get().getCodigoSistemaEmisionElect());
                    oProveedor.get().setCodigoComprobantePago(preRegistro.get().getCodigoComprobantePago());
                    oProveedor.get().setCodigoPadron(preRegistro.get().getCodigoPadron());
                    oProveedor.get().setFechaInicioActiSunat(preRegistro.get().getFechaInicioActiSunat());

                    Optional.ofNullable(preRegistro.get().getIdTipoProveedor())
                            .map(id -> tipoProveedorRepository.getOne(id))
                            .ifPresent(tipoProveedor -> {
                                if (tipoProveedor.getDescripcion().toUpperCase().equals("NACIONAL")) {
                                    Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo("PE"))
                                            .ifPresent(ubigeo -> oProveedor.get().setCodigoPais(ubigeo.getIdUbigeo()));
                                    if (preRegistro.get().getUbigeo().length() > 6) {
                                        Integer nUbigeo = new Integer(preRegistro.get().getUbigeo());
                                        String sUbigeo = nUbigeo.toString();
                                        preRegistro.get().setUbigeo(sUbigeo);
                                    }
                                    Optional.ofNullable(preRegistro.get().getUbigeo())
                                            .filter(u -> !u.isEmpty())
                                            .filter(u -> u.length() == 6)
                                            .ifPresent(codigo -> {
                                                Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo))
                                                        .ifPresent(ubigeo -> oProveedor.get().setCodigoDistrito(ubigeo.getIdUbigeo()));

                                                Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo.substring(0, 4)))
                                                        .ifPresent(ubigeo -> oProveedor.get().setCodigoProvincia(ubigeo.getIdUbigeo()));

                                                Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo.substring(0, 2)))
                                                        .ifPresent(ubigeo -> oProveedor.get().setCodigoRegion(ubigeo.getIdUbigeo()));
                                            });
                                }
                            });

                }

            }
            logger.error("Ingresando GET PROVEEDOR 06");
            ProveedorDto proveedorValidar = oProveedor.get();
            try {
                ProveedorDto nproveedorDto = this.obtenerDatosSunat(userSession, oProveedor.get());
                logger.error("Ingresando GET PROVEEDOR FIN TABLA proveedor: " + nproveedorDto.toString());
                return this.processObject(nproveedorDto);
            }
            catch(Exception ex) {
                return this.processObject(proveedorValidar);
            }


        } else {
            logger.error("Ingresando GET PROVEEDOR 07");
            PreRegistroProveedorDto preRegistro = preRegistroFacade.getPreRegistroByIdHcp(userSession.getId());
            ProveedorDto temp = new ProveedorDto();
            logger.error("Ingresando GET PROVEEDOR 07A temp: " + temp.toString());
            EstadoProveedor estadoProveedor = this.estadoProveedorRepository.
                    getByCodigoEstadoProveedor(EstadoProveedorEnum.REGISTRADO.getCodigo());
            temp.setIdEstadoProveedor(estadoProveedor);

            logger.error("Ingresando GET PROVEEDOR 08");
            BeanUtils.copyProperties(preRegistro, temp);
            temp.setDireccionFiscal(preRegistro.getDireccion());
            temp.setCelular(preRegistro.getTelefono());
            temp.setTipoPersona("J");
            temp.setEvaluacionHomologacion(new BigDecimal(0.0));
            logger.error("Ingresando GET PROVEEDOR 09 temp: " + temp.toString());
            Optional.ofNullable(preRegistro.getIdTipoProveedor())
                    .map(id -> tipoProveedorRepository.getOne(id))
                    .ifPresent(tipoProveedor -> {
                        if (tipoProveedor.getDescripcion().toUpperCase().equals("NACIONAL")) {
                            Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo("PE"))
                                    .ifPresent(ubigeo -> temp.setCodigoPais(ubigeo.getIdUbigeo()));
                            if (preRegistro.getUbigeo().length() > 6) {
                                Integer nUbigeo = new Integer(preRegistro.getUbigeo());
                                String sUbigeo = nUbigeo.toString();
                                preRegistro.setUbigeo(sUbigeo);
                            }
                            Optional.ofNullable(preRegistro.getUbigeo())
                                    .filter(u -> !u.isEmpty())
                                    .filter(u -> u.length() == 6)
                                    .ifPresent(codigo -> {
                                        Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo))
                                                .ifPresent(ubigeo -> temp.setCodigoDistrito(ubigeo.getIdUbigeo()));

                                        Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo.substring(0, 4)))
                                                .ifPresent(ubigeo -> temp.setCodigoProvincia(ubigeo.getIdUbigeo()));

                                        Optional.ofNullable(this.ubigeoService.getUbigeoByCodigo(codigo.substring(0, 2)))
                                                .ifPresent(ubigeo -> temp.setCodigoRegion(ubigeo.getIdUbigeo()));
                                    });
                        }
                    });
            logger.error("Ingresando GET PROVEEDOR 10");
            try {
                logger.error("Ingresando GET PROVEEDOR 10A userSession: " + userSession.toString());
                logger.error("Ingresando GET PROVEEDOR 10B temp: " + temp.toString());
                ProveedorDto temp02 = this.obtenerDatosSunat(userSession, temp);
                logger.error("Ingresando GET PROVEEDOR FIN TABLA preproveedor: " + temp02.toString());
                return this.processObject(temp02);
            }
            catch(Exception ex) {
                logger.error("Ingresando GET PROVEEDOR error temp: " + temp.toString());
                return this.processObject(temp);
            }


        }

    }

    @RequestMapping(value = "/dummy",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> getProveedorDummy(HttpServletRequest request) throws PortalException {
        logger.debug("[rest] /proveedor");
        logger.error("Ingresando GET PROVEEDOR");
        return this.processObject(new ProveedorDto());
    }


    @RequestMapping(value = "devuelveProveedor",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> devuelveProveedor(HttpServletRequest request) throws PortalException {
        logger.debug("[rest] /proveedor");
        logger.error("Ingresando devuelveProveedor 000 ");

        UserSession userSession = this.getUserSession();
        logger.error("Ingresando devuelveProveedor 000 userSession: " + userSession.toString());
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            return this.processObject(new ProveedorDto());
        }

        logger.error("Ingresando devuelveProveedor 001 ");
        Optional<ProveedorDto> oProveedor = Optional.ofNullable(this.proveedorService.getProveedorDtoByRuc(userSession.getRuc()));
        logger.error("Ingresando devuelveProveedor 002 ");
        if (oProveedor.isPresent()) {
            logger.error("Ingresando devuelveProveedor 003 oProveedor: " + oProveedor.get().toString());
            oProveedor.get().setIdHcp(userSession.getId());
            return this.processObject(oProveedor.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "devuelveProveedorNew",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> devuelveProveedorNew(HttpServletRequest request) throws PortalException {
        logger.debug("[rest] /proveedor");

        UserSession userSession = this.getUserSession();
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            return this.processObject(new ProveedorDto());
        }

        Optional<ProveedorDto> oProveedor = Optional.ofNullable(this.proveedorService.getProveedorDtoByRuc(userSession.getRuc()));
        if (oProveedor.isPresent()) {
            oProveedor.get().setIdHcp(userSession.getId());
            return this.processObject(oProveedor.get());
        } else {
            ProveedorDto proveedorDto = new ProveedorDto();
            return this.processObject(proveedorDto);
        }
    }

    @RequestMapping(value = "/devuelveProveedorResponder",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> devuelveProveedorResponder(HttpServletRequest request) throws PortalException {
        logger.debug("[rest] /devuelveProveedorResponder");

        UserSession userSession = this.getUserSession();
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            return this.processObject(new ProveedorDto());
        }

        Optional<ProveedorDto> oProveedor = Optional.ofNullable(this.proveedorService.getProveedorDtoByRucResponder(userSession.getRuc()));
        if (oProveedor.isPresent()) {
            oProveedor.get().setIdHcp(userSession.getId());
            return this.processObject(oProveedor.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    private ProveedorDto obtenerDatosSunat(UserSession userSession, ProveedorDto proveedorDto) {
        InSiteResponse response = null;
        for (int contador = 0; contador < NRO_EJECUCIONES; contador++) {
            try {
                response = inSiteService.getConsultaRuc(userSession.getRuc());
                break;
            } catch (Exception e) {
                if (contador == NRO_EJECUCIONES - 1) {
                }
            }
        }
        logger.error("Ingresando obtenerDatosSunat 01 ProveedorDto: " + response.toString());
        if (Optional.ofNullable(response).isPresent()) {
            logger.error("Ingresando obtenerDatosSunat 01-A INGRESO OK");
            proveedorDto.setActivo(response.getEstado());
            proveedorDto.setHabido(response.getCondicion());
            proveedorDto.setCodigoSistemaEmisionElect(response.getCodigoSistemaEmisionElect());
            proveedorDto.setCodigoPadron(response.getCodigoPadron());
            proveedorDto.setCodigoComprobantePago(response.getCodigoComprobantePago());
            proveedorDto.setFechaInicioActiSunat(response.getFechaInicioActiSunat());
            proveedorDto.setCodigoActividadEconomica(response.getActividadEconomica());

        }
        logger.error("Ingresando obtenerDatosSunat 02 ProveedorDto: " + proveedorDto.toString());
        return proveedorDto;
    }

    @RequestMapping(value = "/{idProveedor}",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> getProveedorByIdProveedor(@PathVariable("idProveedor") Integer idProveedor) throws PortalException {
        logger.error("Ingresando getProveedorByIdProveedor 00");
        return this.processObject(proveedorService.getProveedorDtoById(idProveedor));
    }

    @RequestMapping(value = "/{idProveedor}/lineas-comerciales",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getListLineaComercialByIdProveedor(@PathVariable("idProveedor") Integer idProveedor) throws PortalException {
        return this.processList(proveedorService.getListLineaComercialByIdProveedor(idProveedor));
    }

    @RequestMapping(value = "/ruc/{rucProveedor}",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getProveedorByRucProveedor(@PathVariable("rucProveedor") String ruc) throws PortalException {
        return this.processList(this.proveedorService.getListProveedorByRuc(ruc));
    }


    @RequestMapping(value = "/obtenerProveedor/{rucProveedor}",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> obtenerProveedorByRuc(@PathVariable("rucProveedor") String ruc) throws PortalException {
        Proveedor proveedor = this.proveedorRepository.getProveedorByRuc(ruc);
        return ResponseEntity.ok().body(proveedor);
    }


    @RequestMapping(value = "/filtro", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> devuelveListaLicitacionByFiltro(@RequestBody ProveedorFiltro filtro) {
        return this.processList(this.proveedorService.getListProveedorByFiltro(filtro));
    }

    @RequestMapping(value = "/filtroPaginado", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> devuelveListaLicitacionByFiltroPaginado(@RequestBody ProveedorFiltro filtro) {
        return this.processList(this.proveedorService.getListProveedorByFiltroPaginado(filtro));
    }

    @RequestMapping(value = "/filtroDataMaestra", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> filtroDataMaestra(@RequestBody ProveedorFiltro filtro) {
        filtro.setEstadoProveedor(EstadoProveedorEnum.REGISTRADO.getCodigo());
        return this.processList(this.proveedorService.getListProveedorByFiltro(filtro));
    }

    @ApiOperation( "Servicio para obtener todos los proveedores que serán aprobados por impuetos" )
    @PostMapping( value = "/find-suppliers-pending-tax-approval", headers = "Accept=application/json")
    public ResponseEntity<?> findSuppliersPendingTaxApproval(
            @RequestBody ProveedorFiltro filtro
    ) {

        filtro.setEstadoProveedor( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() );
        return processList( proveedorService.getListProveedorByFiltro( filtro ) );

    }

    @RequestMapping(value = "/filtroValidacion", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> filtroValidacion(@RequestBody ProveedorFiltro filtro) {
        UserSession userSession = this.getUserSession();
        filtro.setEstadoProveedor( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() );
        /*return this.processList(this.proveedorService.getListProveedorByFiltroValidacion(userSession, filtro));*/
        return this.processList( proveedorService.getListProveedorByFiltro( filtro ) );
    }

    @RequestMapping(value = "/filtroLicitacion", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> filtroLicitacion(@RequestBody ProveedorFiltro filtro) {
        UserSession userSession = this.getUserSession();
        return this.processList(this.proveedorService.getListProveedorByFiltroLicitacion(filtro));
    }

    @RequestMapping(value = "/filtroLicitacionPaginado", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> filtroLicitacionPaginado(@RequestBody ProveedorFiltro filtro) {
        UserSession userSession = this.getUserSession();
        return this.processList(this.proveedorService.getListProveedorByFiltroLicitacionPaginado(filtro));
    }


    @RequestMapping(value = "/validarDataMaestra/{idProveedor}",
            method = RequestMethod.PATCH, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> validarDataMaestra(@PathVariable("idProveedor") Integer idProveedor) throws PortalException {
        this.proveedorService.evaluarDataMaestra(idProveedor);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/rechazarDataMaestra/{idProveedor}",
            method = RequestMethod.PATCH, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> rechazarDataMaestra(@PathVariable("idProveedor") Integer idProveedor) throws PortalException {
        this.proveedorService.rechazarDataMaestra(idProveedor, "");
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation( value = "Servicio para guardar el rechazo por Compras y Notificar sobre el mismo" )
    @PostMapping(value = "/rechazar-data-maestra",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<String>> rechazoCompras(

        @RequestBody RechazarValidacionDataMaestraEntradaDto input

    ) {

        Response<String> response = new Response<>();

        try{

            proveedorService.rechazarDataMaestra( input.getIdProveedor(), input.getMotivoRechazo() );
            response.ok( true, "El proveedor fue Rechazado correctamente." );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new ResponseEntity<>( response, HttpStatus.OK);

    }

    @ApiOperation( value = "Servicio para guardar la aprobación por Compras y Migrar migrar el proveedor en SAP" )
    @RequestMapping(value = "/validar-data-maestra/{idProveedor}/{usuarioSap}",
            method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<SaveInformationResponse>> aprobacionCompras(

            @PathVariable("idProveedor") Integer idProveedor,
            @PathVariable("usuarioSap") String usuarioSap

    ) {

        Response<SaveInformationResponse> response = new Response<>();

        try{

            SaveInformationResponse o = proveedorService.aprobacionCompras( idProveedor, usuarioSap );
            response.ok( true, o );

        }catch ( Exception ex ){

            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );

        }

        return new ResponseEntity<>( response, HttpStatus.OK);

    }

    @RequestMapping(value = "/rechazarDataMaestra02/{idProveedor}",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> rechazarDataMaestra02(@RequestBody RechazarValidacionDataMaestraEntradaDto bean)  throws Exception {
        this.proveedorService.rechazarDataMaestra02(bean);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation( "Servicio para aprobar o rechazar a un proveedor que se encuentre en la bandeja de Tesorería. Posibles Valores Status( 'APROBADO', 'RECHAZADO' )" )
    @PostMapping(value = "/save-treasury-approval/{usuarioSap}",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<SaveInformationResponse>> saveTreasuryApproval(
            @PathVariable( "usuarioSap" ) String usuarioSap,
            @RequestBody SaveApprovalInput input
    ) throws PortalException {

        Response<SaveInformationResponse> response = new Response<>();

        try{

            SaveInformationResponse o = proveedorService.saveTreasuryApproval( input, usuarioSap );
            response.ok( true, o );

        }catch ( Exception ex ){

            logger.error( " Ocurrió un problema al aprobar/rechazar a un proveedor por tesorería. Detalle: " + ex.getMessage() );
            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation( "Servicio para aprobar o rechazar a un proveedor que se encuentre en la bandeja de Impuestos. Posibles Valores Status( 'APROBADO', 'RECHAZADO' )" )
    @PostMapping(value = "/save-tax-approval",
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<SaveInformationResponse>> saveTaxApproval(
            @RequestBody SaveApprovalInput input
    ) throws PortalException {

        Response<SaveInformationResponse> response = new Response<>();

        try{

            SaveInformationResponse o = proveedorService.saveTaxApproval( input );
            response.ok( true, o );

        }catch ( Exception ex ){

            logger.error( " Ocurrió un problema al aprobar/rechazar a un proveedor por impuestos. Detalle: " + ex.getMessage() );
            response.ok( ex );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return new ResponseEntity<>( response, HttpStatus.OK );

    }

    @ApiOperation(value = "Elimina todos los datos del proveedor", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> eliminaDatosProveedor(@PathVariable Integer id) throws URISyntaxException {
        logger.debug("Delete by id eliminaDatosProveedor : {}", id);
        try {
            this.proveedorService.eliminarDatosProveedor(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = Utils.obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> save(@RequestBody ProveedorDto proveedorDto,
                                    BindingResult result)  {
        logger.error("Ingresando GRABAR PROVEEDOR: " + proveedorDto);

        try {
            return this.processObject(proveedorService.saveDto(proveedorDto));
        } catch (Exception ex) {
            ex.printStackTrace();
            PortalException pex = new PortalException(Utils.obtieneMensajeErrorException(ex));
            throw pex;
        }

    }


    @RequestMapping(value = "/id/{idProveedor}",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable("idProveedor") Integer idProveedor) throws PortalException {
        Proveedor proveedor = this.proveedorRepository.getOne(idProveedor);
        return ResponseEntity.ok().body(proveedor);
    }

    @RequestMapping(value = "/getProveedorDatosGenerales",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProveedorDatosGeneralesDTO>> getProveedorDatosGenerales(
            @RequestBody Map<String, Object> json) throws PortalException {
        String fechaCreacionIni = (String) json.get("fechaCreacionIni");
        String fechaCreacionFin = (String) json.get("fechaCreacionFin");
        List<ProveedorDatosGeneralesDTO> listaProveedorDatosGenerales = this.proveedorService.getProveedorDatosGenerales(fechaCreacionIni, fechaCreacionFin);

        return ResponseEntity.ok().body(listaProveedorDatosGenerales);
    }

    @RequestMapping(value = "/listaProveedorSinHCPID",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProveedorCustom>> getListaProveedoresSinHcpID() throws PortalException {

        List<ProveedorCustom> listaProveedores = this.proveedorService.getListProveedorSinHcpID();

        return ResponseEntity.ok().body(listaProveedores);
    }

    @RequestMapping(value = "/actualizarIDHCPProveedor", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Integer> devuelveCountByEstadoLicitacion(@RequestBody ListProveedorHCP listProveedorHCP)  {
        Integer nroProveedoresActualizados = this.proveedorService.updateProveedorIDHCP(listProveedorHCP);
        return ResponseEntity.ok().body(nroProveedoresActualizados);
    }

    @PostMapping("/xlsx/file/")
    public ResponseEntity<List<ProveedorXLSXDTO>> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            List<ProveedorXLSXDTO> result = this.proveedorService.uploadExcel(in);
            for (ProveedorXLSXDTO beanUpload : result) {
                if (beanUpload.isError()) {
                    continue;
                }
                ProveedorDto bean = beanUpload.getProveedor();
                try {
                    logger.error("Ingresando bean :" + bean.toString());
                    this.jcoConsultaProveedorService.listaProveedorByRFC(bean.getAcreedorCodigoSap(),"","",bean.getEmail());
                    beanUpload.setError(false);
                    beanUpload.setMensaje("SE AGREGO EL PROVEEDOR A LA BD " + bean.getAcreedorCodigoSap());
                    beanUpload.setProveedor(null);
                } catch (Exception e) {
                    beanUpload.setError(true);
                    beanUpload.setMensaje(Utils.obtieneMensajeErrorException(e));

                    logger.error("Ingresando bean error uploadExcel:" + e.getMessage());
                    e.printStackTrace();
                }
            }
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @RequestMapping(value = "_aprobarRechazarActualizacion",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<ProveedorValidacionInfoSalidaDto> aprobarRechazarActualizacion(
            @RequestBody ProveedorValidacionInfoEntradaDto bean)  {
        logger.error("Ingresando aprobarRechazarActualizacion: " + bean);

        try {
            ProveedorValidacionInfoSalidaDto result = proveedorService.aprobarRechazarActualizacion(bean);
            if (result.getIndicadorRechazado()) {
                Proveedor proveedor = result.getProveedor();
                proveedorAcreedorRechazadoActualizacionNotificacion.enviar(proveedor, result.getMotivoRechazo());
            }
            return Optional.of(result)
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception ex) {
            ex.printStackTrace();
            PortalException pex = new PortalException(Utils.obtieneMensajeErrorException(ex));
            throw pex;
        }

    }

}
