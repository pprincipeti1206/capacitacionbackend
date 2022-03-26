package com.incloud.hcp.rest._inicio;

import com.incloud.hcp.rest._framework.BaseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/_cargaInicialDataRest")
public class _CargaInicialDataRest extends BaseRest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String RUTA_DATA_INICIAL_CSV = "data_inicial/csv/";
    private final String SUFIJO_CSV = ".csv";


}
