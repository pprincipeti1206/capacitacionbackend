package com.incloud.hcp.rest._gproveedor;


import com.incloud.hcp._gproveedor.wsdl.inside.InSiteResponse;
import com.incloud.hcp._gproveedor.wsdl.inside.InSiteService;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain._gproveedor.domain.PreRegistroProveedor;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.PreRegistroProveedorRepository;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._gproveedor.dto.PreRegistroProveedorDto;
import com.incloud.hcp.service._gproveedor.dto.RechazarValidacionDataMaestraEntradaDto;
import com.incloud.hcp.service._gproveedor.facade.PreRegistroFacade;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Administrador on 04/09/2017.
 */
@RestController
@RequestMapping(value = "/api/proveedor-potencial")
public class PreRegistroRest extends AppRest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int NRO_EJECUCIONES = 10;

    private InSiteService inSiteService;
    private PreRegistroFacade preRegistroFacade;
    private PreRegistroProveedorRepository preRegistroProveedorRepository;
    private ProveedorRepository proveedorRepository;

    @Autowired
    public void setInSiteService(InSiteService inSiteService) {
        this.inSiteService = inSiteService;
    }

    @Autowired
    public void setPreRegistroFacade(PreRegistroFacade preRegistroFacade) {
        this.preRegistroFacade = preRegistroFacade;
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> guardar(@Valid @RequestBody PreRegistroProveedorDto dto, BindingResult result) {
        if (result.hasErrors()) {
            PortalException ex = new PortalException("Información inválida");
        //   result.getFieldErrors().stream().forEach(f -> ex.addDetail(f.getField(), f.getDefaultMessage()));
            throw ex;
        }
        try {
            UserSession userSession = this.getUserSession();
            logger.error("guardar - userSession: " + userSession.toString());
            //dto.setIdHcp(userSession.getId());
            return this.processObject(this.preRegistroFacade.save(dto));
        }
        catch (Exception e) {
               String error = Utils.obtieneMensajeErrorException(e);
               throw new RuntimeException(error);
        }
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getPreregistroByIdHcp() {
        UserSession userSession = this.getUserSession();
        return this.processObject(this.preRegistroFacade.getPreRegistroByIdHcp(userSession.getId()));
    }

    @RequestMapping(value = "/pendientes",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listarPendientes() {
        UserSession userSession = this.getUserSession();
        //return this.processList(this.preRegistroFacade.getListSolicitudPendiente(userSession.getId()));
        return this.processList(this.preRegistroFacade.getListSolicitudPendiente());
    }

    @RequestMapping(value = "/pendientes/{idSolicitud}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getSolicitudByIdRegistro(@PathVariable("idSolicitud") Integer idSolicitud) {

        PreRegistroProveedorDto p = this.preRegistroFacade.getPreRegistroById(idSolicitud);

       if (!p.getSunat()) {
           if(p.getIdTipoProveedor().equals(1)){ // 1 - Nacional, 2 - Extranjero
               InSiteResponse response = null;
               for(int contador=0; contador < NRO_EJECUCIONES; contador++) {
                   try {
                       response = inSiteService.getConsultaRuc(p.getRuc());
                       break;
                   } catch (Exception e) {
                       if (contador == NRO_EJECUCIONES - 1 ) {
                           //throw new PortalException(e.getMessage());
                       }
                   }
               }

               if (Optional.ofNullable(response).isPresent()) {
                   logger.error("getSolicitudByIdRegistro InSiteResponse:" + response.toString());
                   BeanUtils.copyProperties(response, p);
                   p.setSunat(true);
                   p.setHabido(response.getCondicion());
                   p.setActivo(response.getEstado());
                   p.setRazonSocial(response.getRazonSocial());
                   p.setRegion(response.getRegion());
                   p.setProvincia(response.getProvincia());
                   p.setDistrito(response.getDistrito());
                   p.setDireccion(response.getDireccion());
                   p.setUbigeo(response.getUbigeo());

                   logger.error("getSolicitudByIdRegistro p:" + p.toString());
                   //return this.processObject(this.preRegistroFacade.updateSearchSunat(p));
                   this.preRegistroFacade.updateSearchSunat(p);
                   return this.processObject(this.preRegistroFacade.getPreRegistroById(idSolicitud));

               }
               //throw new PortalException("Error al consultar a Sunat");
           }
        }
        if(p.getIdTipoProveedor().equals(1)) { // 1 - Nacional, 2 - Extranjero
            if (p.getUbigeo().length() > 6) {
                Integer nUbigeo = new Integer(p.getUbigeo());
                String sUbigeo = nUbigeo.toString();
                p.setUbigeo(sUbigeo);
            }
        }
        return this.processObject(p);
    }
    @RequestMapping(value = "/pendientesauxiliar/{idSolicitud}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getSolicitudByIdRegistroAux(@PathVariable("idSolicitud") Integer idSolicitud) {

        PreRegistroProveedorDto p = this.preRegistroFacade.getPreRegistroById(idSolicitud);

        return this.processObject(p);
    }
    @RequestMapping(value = "/pendientes/ruc/{rucProveedor}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getDatosEmisionByRucProveedor(@PathVariable("rucProveedor") String rucProveedor) {
        InSiteResponse p = new InSiteResponse();
        for(int contador=0; contador < NRO_EJECUCIONES; contador++) {
            try {
                p = inSiteService.getConsultaRuc(rucProveedor);
                break;
            } catch (Exception e) {
                if (contador == NRO_EJECUCIONES - 1 ) {
                    throw new PortalException(e.getMessage());
                }
            }
        }
        if (p != null) {
            return this.processObject(p);
        }
        throw new PortalException("Error al consultar a Sunat");
//
//
//        try {
//
//            p = inSiteService.getConsultaRuc(rucProveedor);
//
//            if (p != null) {
//
//                return this.processObject(p);
//            }
//            throw new PortalException("Error al consultar a Sunat");
//        } catch (InSiteException ex) {
//            throw new PortalException(ex.getMessage());
//        }

        //return this.processObject(p);
    }


    @RequestMapping(value = "/{idPreRegistro}/aprobar",
            method = RequestMethod.PATCH,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> aprobar(@PathVariable("idPreRegistro") Integer idPreRegistro) {
        return this.processObject(this.preRegistroFacade.aprobarSolicitud(idPreRegistro));
    }

    @RequestMapping(value = "/{idPreRegistro}/reprobar",
            method = RequestMethod.PATCH,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> reprobar(@PathVariable("idPreRegistro") Integer idPreRegistro) {
        return this.processObject(this.preRegistroFacade.reprobarSolicitud(idPreRegistro));
    }


    @ApiOperation(value = "Aprueba Solicitud de Proveedor", produces = "application/json")
    @RequestMapping(value = "/{idPreRegistro}/aprobarSolicitud",
            method = RequestMethod.PATCH,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map> aprobarSolicitud(@PathVariable("idPreRegistro") Integer idPreRegistro) throws URISyntaxException {
        logger.debug("Ingresando aprobar: " );
        try {
            return this.processObject(this.preRegistroFacade.aprobarSolicitud02(idPreRegistro));
        }
        catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);

        }
    }

    @ApiOperation(value = "Rechaza Solicitud de Proveedor", produces = "application/json")
    @RequestMapping(value = "/{idPreRegistro}/rechazarSolicitud",
            method = RequestMethod.PATCH,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map> rechazarSolicitud(@PathVariable("idPreRegistro") Integer idPreRegistro, @RequestBody RechazarValidacionDataMaestraEntradaDto bean) throws URISyntaxException {
        logger.debug("Ingresando rechazar: " );
        try {
            return this.processObject(this.preRegistroFacade.reprobarSolicitud02(idPreRegistro, bean.getMotivoRechazo()));
        }
        catch (Exception e) {
            String error = Utils.obtieneMensajeErrorExceptionDepurado(e);
            throw new RuntimeException(error);
        }
    }

    @RequestMapping(value = "/obtenerPreProveedor/{rucProveedor}",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> obtenerPreProveedorByRuc(@PathVariable("rucProveedor") String ruc) throws PortalException {
        PreRegistroProveedor proveedor = this.preRegistroProveedorRepository.getByRuc(ruc);
        return ResponseEntity.ok().body(proveedor);
    }


    @RequestMapping(value = "/datosSunat/{ruc}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> datosSunat(@PathVariable("ruc") String ruc) {

        InSiteResponse response = null;
        for(int contador=0; contador < NRO_EJECUCIONES; contador++) {
            try {
                response = inSiteService.getConsultaRuc(ruc);
                break;
            } catch (Exception e) {
                if (contador == NRO_EJECUCIONES - 1 ) {
                    //throw new PortalException(e.getMessage());
                }
            }
        }
        return this.processObject(response);
    }
}
