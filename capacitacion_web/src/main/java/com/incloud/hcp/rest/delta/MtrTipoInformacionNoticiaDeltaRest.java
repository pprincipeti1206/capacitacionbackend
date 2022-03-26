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

import com.incloud.hcp.domain.MtrTipoInformacionNoticia;
import com.incloud.hcp.rest.MtrTipoInformacionNoticiaRest;
import com.incloud.hcp.service.delta.MtrTipoInformacionNoticiaDeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/mtrTipoInformacionNoticia")
public class MtrTipoInformacionNoticiaDeltaRest extends MtrTipoInformacionNoticiaRest {

    @Autowired
    protected MtrTipoInformacionNoticiaDeltaService mtrTipoInformacionNoticiaDeltaService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MtrTipoInformacionNoticia>> getAll(){
        return Optional.ofNullable(mtrTipoInformacionNoticiaDeltaService.getAll())
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> save(@RequestBody MtrTipoInformacionNoticia tipoInformacionNoticia){
        return mtrTipoInformacionNoticiaDeltaService.grabar(tipoInformacionNoticia);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> update(@RequestBody MtrTipoInformacionNoticia tipoInformacionNoticia){
        return mtrTipoInformacionNoticiaDeltaService.update(tipoInformacionNoticia);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map> delete(@RequestBody MtrTipoInformacionNoticia tipoInformacionNoticia){
        return mtrTipoInformacionNoticiaDeltaService.delete(tipoInformacionNoticia);
    }


}