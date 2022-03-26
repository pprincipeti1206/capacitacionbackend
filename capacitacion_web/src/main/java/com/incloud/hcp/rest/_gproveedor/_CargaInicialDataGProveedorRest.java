package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.*;
import com.incloud.hcp.repository._gproveedor.*;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.util.Utils;
import com.incloud.hcp.utils.CsvUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/_cargaInicialDataGProveedorRest")
public class _CargaInicialDataGProveedorRest extends AppRest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String RUTA_DATA_INICIAL_CSV = "data_inicial/csv/";
    private final String SUFIJO_CSV = ".csv";

    @Autowired
    private MonedaRepository monedaRepository;

    @Autowired
    private ParametroRepository parametroRepository;

    @Autowired
    private UbigeoRepository ubigeoRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private CondicionPagoReposity condicionPagoReposity;

    @Autowired
    private TipoProveedorRepository tipoProveedorRepository;

    @Autowired
    private LineaComercialRepository lineaComercialRepository;

    @Autowired
    private SectorTrabajoRepository sectorTrabajoRepository;

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    private EstadoProveedorRepository estadoProveedorRepository;

    @Autowired
    private PreguntaInformacionRepository preguntaInformacionRepository;


    @ApiOperation(value = "Carga inicial de Data (Tabla Moneda)", produces = "application/json")
    @PostMapping(value = "/_020_moneda", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _020_moneda() throws URISyntaxException {
        log.debug("Find by id _20_moneda : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "20_moneda" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.monedaRepository.deleteAll();
            this.monedaRepository.saveAll(CsvUtils.read(Moneda.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Parametro)", produces = "application/json")
    @PostMapping(value = "/_030_parametro", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _030_parametro() throws URISyntaxException {
        log.debug("Find by id _30_parametro : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "30_parametro" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.parametroRepository.deleteAll();
            this.parametroRepository.saveAll(CsvUtils.read(Parametro.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Parametro)", produces = "application/json")
    @PostMapping(value = "/_035_parametro_grupo_tesoreria", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _035_parametro() throws URISyntaxException {
        log.debug("Find by id _035_parametro_grupo_tesoreria : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "35_parametro_grupo_tesoreria" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            //this.parametroRepository.deleteByTipo("GRUPO_TESORERIA");
            List<Parametro> lista = CsvUtils.read(Parametro.class, inputStream);
            for(Parametro bean : lista) {
                this.parametroRepository.save(bean);
            }

            //this.parametroRepository.saveAll(CsvUtils.read(Parametro.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Parametro)", produces = "application/json")
    @PostMapping(value = "/_036_parametro_grupo_compra", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _036_parametro() throws URISyntaxException {
        log.debug("Find by id _036_parametro_grupo_compra : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "36_parametro_grupo_compra" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            //this.parametroRepository.deleteByTipo("GRUPO_COMPRA");
            this.parametroRepository.saveAll(CsvUtils.read(Parametro.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Ubigeo) - DESACTIVAR SECUENCIA!!!", produces = "application/json")
    @PostMapping(value = "/_040_ubigeo", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _040_ubigeo() throws URISyntaxException {
        log.debug("Find by id _40_ubigeo : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "40_ubigeo" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.ubigeoRepository.deleteAll();
            List<Ubigeo> lista = CsvUtils.read(Ubigeo.class, inputStream);
            for(Ubigeo bean : lista) {
                this.ubigeoRepository.save(bean);
            }
            //this.ubigeoRepository.saveAll();
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Ubigeo LISTA) - DESACTIVAR SECUENCIA!!!", produces = "application/json")
    @PostMapping(value = "/_041_ubigeo", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _041_ubigeo() throws URISyntaxException {
        log.debug("Find by id _41_ubigeo : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "40_ubigeo" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.ubigeoRepository.deleteAll();
            List<Ubigeo> lista = CsvUtils.read(Ubigeo.class, inputStream);
            this.ubigeoRepository.saveAll(lista);
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Banco)", produces = "application/json")
    @PostMapping(value = "/_050_banco", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _050_banco() throws URISyntaxException {
        log.debug("Find by id _50_Banco : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "50_banco" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.bancoRepository.deleteAll();
            this.bancoRepository.saveAll(CsvUtils.read(Banco.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Condicion Pago)", produces = "application/json")
    @PostMapping(value = "/_060_condicion_pago", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _060_condicion_pago() throws URISyntaxException {
        log.debug("Find by id _60_Condicion_pago : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "60_condicion_pago" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.condicionPagoReposity.deleteAll();
            this.condicionPagoReposity.saveAll(CsvUtils.read(CondicionPago.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Tipo_Proveedor)", produces = "application/json")
    @PostMapping(value = "/_070_tipo_proveedor", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _070_tipo_proveedor() throws URISyntaxException {
        log.debug("Find by id _70_tipo_proveedor : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "70_tipo_proveedor" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.tipoProveedorRepository.deleteAll();
            this.tipoProveedorRepository.saveAll(CsvUtils.read(TipoProveedor.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Linea_Comercial)", produces = "application/json")
    @PostMapping(value = "/_080_linea_comercial", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _080_linea_comercial() throws URISyntaxException {
        log.debug("Find by id _80_linea_comercial : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "80_linea_comercial" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.lineaComercialRepository.deleteAll();
            this.lineaComercialRepository.saveAll(CsvUtils.read(LineaComercial.class, inputStream));


            String nombreCSVfamilia = RUTA_DATA_INICIAL_CSV + "85_linea_comercial" + SUFIJO_CSV;
            ClassPathResource resourceCSVfamilia = new ClassPathResource(nombreCSVfamilia);
            InputStream inputStreamfamilia = resourceCSVfamilia.getInputStream();

            List<LineaComercial> lineaComercialFamiliaList = CsvUtils.read(LineaComercial.class, inputStreamfamilia);
            int contador = 0;
            for(LineaComercial bean : lineaComercialFamiliaList) {
                try {
                    LineaComercial lineaComercialPadre = this.lineaComercialRepository.getByDescripcion(bean.getDescripcionPadre());
                    bean.setIdPadre(lineaComercialPadre.getIdLineaComercial());
                    this.lineaComercialRepository.save(bean);
                }
                catch (Exception e) {
                    log.error("FASE FAMILIA BEAN: " + bean.toString());
                    contador++;
                }
            }

            String nombreCSVsfamilia = RUTA_DATA_INICIAL_CSV + "86_linea_comercial" + SUFIJO_CSV;
            ClassPathResource resourceCSVsfamilia = new ClassPathResource(nombreCSVsfamilia);
            InputStream inputStreamsfamilia = resourceCSVsfamilia.getInputStream();

            List<LineaComercial> lineaComercialsFamiliaList = CsvUtils.read(LineaComercial.class, inputStreamsfamilia);
            for(LineaComercial bean : lineaComercialsFamiliaList) {
                try {
                    LineaComercial lineaComercialPadre = this.lineaComercialRepository.
                            getByCodigoGrupoComercial(bean.getCodigoGrupoComercialPadre());
                    bean.setIdPadre(lineaComercialPadre.getIdLineaComercial());
                    this.lineaComercialRepository.save(bean);
                }
                catch (Exception e) {
                    log.error("FASE subFAMILIA BEAN: " + bean.toString());
                    contador++;
                }
            }

            return new ResponseEntity<>("OK + Errores: " + contador, HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Sector Trabajo)", produces = "application/json")
    @PostMapping(value = "/_090_sector_trabajo", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _090_sector_trabajo() throws URISyntaxException {
        log.debug("Find by id _90_sector_trabajo : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "90_sector_trabajo" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.sectorTrabajoRepository.deleteAll();
            this.sectorTrabajoRepository.saveAll(CsvUtils.read(SectorTrabajo.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Tipo Comprobante)", produces = "application/json")
    @PostMapping(value = "/_100_tipo_comprobante", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _100_tipo_comprobante() throws URISyntaxException {
        log.debug("Find by id _100_tipo_comprobante : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "100_tipo_comprobante" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.tipoComprobanteRepository.deleteAll();
            this.tipoComprobanteRepository.saveAll(CsvUtils.read(TipoComprobante.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Estado Proveedor) DESACTIVAR SECUENCIAS!!!", produces = "application/json")
    @PostMapping(value = "/_110_estado_proveedor", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _110_estado_proveedor() throws URISyntaxException {
        log.debug("Find by id _110_estado_proveedor : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "110_estado_proveedor" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.estadoProveedorRepository.deleteAll();
            this.estadoProveedorRepository.saveAll(CsvUtils.read(EstadoProveedor.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Carga inicial de Data (Tabla Pregunta Informacion)", produces = "application/json")
    @PostMapping(value = "/_120_pregunta_informacion", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> _120_pregunta_informacion() throws URISyntaxException {
        log.debug("Find by id _120_pregunta_informacion : {}");
        try {
            String nombreCSV = RUTA_DATA_INICIAL_CSV + "120_pregunta_informacion" + SUFIJO_CSV;
            ClassPathResource resourceCSV = new ClassPathResource(nombreCSV);
            InputStream inputStream = resourceCSV.getInputStream();
            this.preguntaInformacionRepository.deleteAll();
            this.preguntaInformacionRepository.saveAll(CsvUtils.read(PreguntaInformacion.class, inputStream));
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }




}
