package com.incloud.hcp.rest.delta;

import com.incloud.hcp.rest.SapRfcRest;
import com.incloud.hcp.service.delta.SapRfcDeltaService;
import com.incloud.hcp.service.dto.MensajeSapDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/integrasap")
public class SapRfcDeltaRest extends SapRfcRest {
    private final Logger log = LoggerFactory.getLogger(SapRfcDeltaRest.class);



    @Autowired
    protected SapRfcDeltaService sapRfcDeltaService;




    @ApiOperation(value = "Servicio de Test", produces = "application/json")
    @RequestMapping(value = "/testRest", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<List<MensajeSapDto>> testRest(@RequestBody MensajeSapDto dto) {
        List<MensajeSapDto> lista = new ArrayList<MensajeSapDto>();
        return Optional.of(lista)
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
