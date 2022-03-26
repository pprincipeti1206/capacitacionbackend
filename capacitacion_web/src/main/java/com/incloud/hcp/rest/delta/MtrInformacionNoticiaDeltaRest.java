/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Modificado por CARLOS BAZALAR
 * Email: cbazalarlarosa@gmail.com
 * Template pack-angular:src/main/java/rest/EntitydeltaResource.java.e.vm
 */
package com.incloud.hcp.rest.delta;

import com.incloud.hcp.cmis.CmisFile;
import com.incloud.hcp.domain.MtrInformacionNoticia;
import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import com.incloud.hcp.rest.MtrInformacionNoticiaRest;
import com.incloud.hcp.service._framework.cmis.CmisService;
import com.incloud.hcp.service.delta.MtrTipoInformacionNoticiaDeltaService;
import com.incloud.hcp.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/mtrInformacionNoticia")
public class MtrInformacionNoticiaDeltaRest extends MtrInformacionNoticiaRest {

    @Autowired
    private CmisService cmisService;

    @Autowired
    private MtrTipoInformacionNoticiaDeltaService mtrTipoInformacionNoticiaDeltaService;

    @RequestMapping(value = "/noticia/{idInformacionNoticia}/adjuntos", 
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addArchivoAdjuntoNoticia(
            @PathVariable("idInformacionNoticia") Integer idInformacionNoticia,
            @RequestParam("file") MultipartFile file){
        try{
            log.debug("Se agregara un archivo");
            Optional<MtrInformacionNoticia> oInfo = Optional.ofNullable(
                    mtrInformacionNoticiaDeltaService.getInformacionNoticiaById(idInformacionNoticia));

            if (oInfo.isPresent()){
                log.debug("Existe Informacion");
                MtrInformacionNoticia info = oInfo.get();
                MtrTipoInformacionNoticia tipo = info.getMtrTipoInformacionNoticia();

                String folderId = Optional.ofNullable(tipo.getCarpetaId())
                        .orElseGet(() -> {
                            String folder = reemplazarAcentos(tipo.getDescripcion());
                            log.debug("Se creara un folder: " + folder);
                            String fi = cmisService.createFolder(folder);
                            tipo.setCarpetaId(fi);
                            log.debug("Se guardara el ID de la carpeta: " + fi);
                            mtrTipoInformacionNoticiaDeltaService.actualizarCarpetaIdTipoInformacionById(tipo.getId(), fi);
                            log.debug("se actualizo en la informacion del proveedor");
                            return fi;
                        });
                log.debug("Se creara el documento en el repositorio");
                CmisFile cmisFile = cmisService.createDocumento(folderId, file);
                log.debug("Documento Creado: " + cmisFile.getName());
                info.setRutaAdjunto(cmisFile.getUrl());
                info.setArchivoNombre(cmisFile.getName());
                info.setArchivoId(cmisFile.getId());
                this.mtrInformacionNoticiaDeltaService.update(info);
                log.debug("Informacion noticia actualizado");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                log.debug("No existe Informacion de Proveedor");
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map map = new HashMap<>();
            map.put("response", e.getMessage());
            return new ResponseEntity<Map>(map,HttpStatus.NOT_FOUND);
        }
    }

    private String reemplazarAcentos(String textoConAcentos) {
        String textoSinAcentos;
        String cadenaNormalize = Normalizer.normalize(textoConAcentos, Normalizer.Form.NFD);
        textoSinAcentos = cadenaNormalize.replaceAll(Constants.PATRON_ACENTOS, "").toLowerCase();
        textoSinAcentos = textoSinAcentos.replaceAll("\\s*", "");
        return textoSinAcentos;
    }

    @RequestMapping(value = "/{idTipoInfoNoticia}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MtrInformacionNoticia>> getInfoNoticiaByTipoInfoNoticia(
            @PathVariable("idTipoInfoNoticia") int idTipoSolicitud){
        return Optional.ofNullable(mtrInformacionNoticiaDeltaService.getInfoNoticiaByTipoInfoNoticia(idTipoSolicitud))
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> save(@RequestBody MtrInformacionNoticia informacionNoticia){
        return mtrInformacionNoticiaDeltaService.grabar(informacionNoticia);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map> update(@RequestBody MtrInformacionNoticia informacionNoticia){
        return mtrInformacionNoticiaDeltaService.update(informacionNoticia);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map> delete(@RequestBody MtrInformacionNoticia informacionNoticia){
        return mtrInformacionNoticiaDeltaService.delete(informacionNoticia);
    }

}