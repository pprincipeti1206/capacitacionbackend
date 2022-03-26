package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.cmis.CmisFile;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorAdjuntoSunat;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCatalogo;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.ProveedorRepository;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import com.incloud.hcp.service._framework.cmis.CmisService;
import com.incloud.hcp.service._gproveedor.PreRegistroProveedorService;
import com.incloud.hcp.service._gproveedor.ProveedorCatalogoService;
import com.incloud.hcp.service._gproveedor.ProveedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Administrador on 26/09/2017.
 */

@RestController
@RequestMapping(value = "/api/repositorio")
public class CmisRest extends AppRest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CmisService cmisService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorCatalogoService proveedorCatalogoService;


    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private PreRegistroProveedorService preRegistroProveedorService;


    @RequestMapping(value = "/proveedor/catalogos",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addCatalogo(@RequestParam("files") MultipartFile file) {
        logger.debug("Agregrando catalogo [" + file.getName() + " , " + file.getSize() + " ]");
        //Long current = System.currentTimeMillis();
        String newFolder = "temp";
        String folderId = cmisService.createFolder(newFolder);
        CmisFile cmisFile = cmisService.createDocumento(folderId, file);
        logger.debug("Archivo cargado al repositorio : " + cmisFile);
        ProveedorCatalogo proveedorCatalogo = new ProveedorCatalogo();
        proveedorCatalogo.setArchivoId(cmisFile.getId());
        proveedorCatalogo.setArchivoNombre(cmisFile.getName());
        proveedorCatalogo.setRutaCatalogo(cmisFile.getUrl());
        proveedorCatalogo.setArchivoTipo(cmisFile.getType());

        return this.processObject(proveedorCatalogo);
    }
    @RequestMapping(value = "/proveedor/adjuntosSunat",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addAdjuntoSunat(@RequestParam("fileSunat") MultipartFile file) {
        logger.debug("Agregrando adjunto sunat [" + file.getName() + " , " + file.getSize() + " ]");
        //Long current = System.currentTimeMillis();
        String newFolder = "temp";
        String folderId = cmisService.createFolder(newFolder);
        CmisFile cmisFile = cmisService.createDocumento(folderId, file);
        logger.debug("Archivo cargado al repositorio : " + cmisFile);
        ProveedorAdjuntoSunat proveedorAdjuntoSunat = new ProveedorAdjuntoSunat();
        proveedorAdjuntoSunat.setArchivoId(cmisFile.getId());
        proveedorAdjuntoSunat.setArchivoNombre(cmisFile.getName());
        proveedorAdjuntoSunat.setRutaAdjunto(cmisFile.getUrl());
        proveedorAdjuntoSunat.setArchivoTipo(cmisFile.getType());

        return this.processObject(proveedorAdjuntoSunat);
    }


    @RequestMapping(value = "/proveedor/{idProveedor}/catalogos",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getListCatalogo(@PathVariable("idProveedor") Integer idProveedor) {
        return this.processList(proveedorCatalogoService.getListCatalogoDtoByIdProveedor(idProveedor));
    }


    @RequestMapping(value = "/proveedor/catalogos/{archivoId}",
            method = RequestMethod.DELETE, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteCatalogo(HttpServletRequest request,
                                            @PathVariable("archivoId") String archivoId) {
        logger.debug("Eliminando el archivo con ID " + archivoId);
        UserSession userSession = this.getUserSession();
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            throw new PortalException("El usuario no esta registrado como proveedor");
        }
        Proveedor p = this.proveedorService.getProveedorByRuc(userSession.getRuc());
        proveedorCatalogoService.deleteCatalogoByIdProveedorCatalogoById(p.getIdProveedor(), archivoId);
        cmisService.deleteFile(archivoId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/proveedor/{folderId}",
            method = RequestMethod.DELETE, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getListCatalogoByFolderId(@PathVariable("folderId") String folderId) {
        logger.debug("Listar catalogos del proveedor del folderId " + folderId);

        if (!Optional.ofNullable(folderId).filter(s -> !s.trim().isEmpty()).isPresent()) {
            return this.processListEmpty();
        }

        return this.processList(cmisService.getListFileByFolderId(folderId));
    }


    @RequestMapping(value = "/proveedor/homologacion",
            method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addAttachHomologacion(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        logger.debug("Adjuntando archivo a la homologación");
        UserSession userSession = this.getUserSession();
        if (!Optional.ofNullable(userSession.getRuc()).isPresent()) {
            throw new PortalException("El usuario no esta registrado como proveedor");
        }

        Optional<Proveedor> oProveedor = Optional.ofNullable(userSession.getRuc())
                .map(proveedorService::getProveedorByRuc);
        if (!oProveedor.isPresent()) {
            logger.error("El proveedor no esta disponible");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        Proveedor p = oProveedor.get();
        String folderId = Optional.ofNullable(p.getCarpetaId())
                .orElseGet(() -> {
                    logger.debug("Se creara un folder " + p.getRuc());
                    String fi = cmisService.createFolder(p.getRuc());
                    p.setCarpetaId(fi);
                    logger.debug("Se guardara el ID de la carpeta " + fi);
                    proveedorService.save(p);
                    return fi;
                });

        logger.debug("Se esta adjuntando el archivo para el proveedor " + p.getRuc());
        CmisFile cmisFile = cmisService.createDocumento(folderId, file);
        logger.debug("Archivo cargado al repositorio : " + cmisFile);
        return this.processObject(cmisFile);
    }


}
