package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp._gproveedor.jco.consultaProveedor.dto.ConsultaProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.consultaProveedor.service.JCOConsultaProveedorService;
import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.proveedor.service.JCOProveedorService;
import com.incloud.hcp._gproveedor.jco.ubigeo.dto.UbigeoRFCResponseDto;
import com.incloud.hcp._gproveedor.jco.ubigeo.service.JCOUbigeoService;
import com.incloud.hcp.util.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/_jcoRest")
public class _JcoRest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private JCOUbigeoService jcoUbigeoService;

    @Autowired
    private JCOConsultaProveedorService jcoConsultaProveedorService;

    @Autowired
    private JCOProveedorService jcoProveedorService;


    @ApiOperation(value = "Crear Proveedor RFC", produces = "application/json")
    @PostMapping(value = "/crearProveedor/{idProveedor}/{usuarioSap}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity <ProveedorRFCResponseDto> crearProveedorSap(
            @PathVariable Integer idProveedor,
            @PathVariable String usuarioSap) throws URISyntaxException {
        log.debug("Lista Centro Almacen : {}");
        try {
            return Optional.ofNullable(this.jcoProveedorService.grabarProveedor(idProveedor,usuarioSap))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Crear UbigeoSAP", produces = "application/json")
    @PostMapping(value = "/crearUbigeo", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity <UbigeoRFCResponseDto> crearUbigeo() throws URISyntaxException {
        log.debug("Lista Ubigeos : {}");
        try {
            return Optional.ofNullable(this.jcoUbigeoService.listarandActualizarUbigeoRFC())
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    @ApiOperation(value = "Lista Proveedores RFC", produces = "application/json")
    @GetMapping(value = "/listaProveedoresByRFC/{nroAcreedor}/{fechaInicio}/{fechaFin}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity <ConsultaProveedorRFCResponseDto> listarProveedoresRFC(@PathVariable String nroAcreedor, @PathVariable String fechaInicio, @PathVariable String fechaFin) throws URISyntaxException {
        log.debug("Lista Unidad Medida  : {}");
        try {
            return Optional.ofNullable(this.jcoConsultaProveedorService.listaProveedorByRFC(nroAcreedor,fechaInicio,fechaFin,""))
                    .map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = Utils.obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }


}
