package com.incloud.hcp.rest._gproveedor;

import com.incloud.hcp.domain._gproveedor.domain.CondicionPago;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository._gproveedor.CondicionPagoReposity;
import com.incloud.hcp.rest._gproveedor._framework.AppRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/condicion-pago")
public class CondicionPagoRest extends AppRest {

    @Autowired
    private CondicionPagoReposity condicionPagoReposity;

    @RequestMapping(value = "",
            method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CondicionPago>> getListaCondicionPago() throws PortalException {
        List listaTipo = this.condicionPagoReposity.findAll();
        return ResponseEntity.ok().body(listaTipo);
    }

}
