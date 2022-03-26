package com.incloud.hcp._gproveedor.wsdl.inside;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Administrador on 09/11/2017.
 */
@Service
public class InSiteServiceImpl implements InSiteService, InSiteConstant {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public InSiteResponse getConsultaRuc(String ruc) throws InSiteException {
        try {
            logger.error("getConsultaRuc 00 ruc: " + ruc);
            ServiceRUCLocator l = new ServiceRUCLocator();
            ServiceRUCPortType type = l.getServiceRUCPort();
            String data = type.consultaRUC(ruc, USER_DEFAULT, LICENSE_DEFAULT, FORMAT_DEFAULT);
            logger.error("getConsultaRuc 01 data: " + data);

            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            logger.error("getConsultaRuc 01a array: " + array.toJSONString());
            logger.error("getConsultaRuc 01b array: " + array.toString());

            Map<String, Object> map = new HashMap<>();

            for (int i = 0; i < array.size(); i++) {
                Map<String, Object> values = (Map) array.get(i);
                Set<String> keys = values.keySet();
                keys.stream().forEach(key -> {
                    map.put(key, values.get(key));
                });
            }
            logger.error("getConsultaRuc 02 map: " + map.toString() );

            InSiteResponse response = new InSiteResponse();

            response.setRuc(Optional.ofNullable((String)map.get(RUC)).orElse(""));
            logger.error("getConsultaRuc 03 response: " + response.toString() );
            response.setCondicion(Optional.ofNullable((String)map.get(CONDICION))
                    .map(s -> s.equals("HABIDO"))
                    .orElse(Boolean.FALSE));
            logger.error("getConsultaRuc 04 response: " + response.toString() );
            response.setEstado(Optional.ofNullable((String)map.get(ACTIVO))
                    .map(s -> s.equals("ACTIVO"))
                    .orElse(Boolean.FALSE));
            logger.error("getConsultaRuc 05 response: " + response.toString() );
            response.setUbigeo(Optional.ofNullable((String)map.get(UBIGEO)).orElse(""));
            logger.error("getConsultaRuc 06 response: " + response.toString() );
            response.setDireccion(Optional.ofNullable((String)map.get(DIRECCION_02)).orElse(""));
            logger.error("getConsultaRuc 07 response: " + response.toString() );
            if (StringUtils.isBlank(response.getDireccion())) {
                response.setDireccion(Optional.ofNullable((String)map.get(DIRECCION)).orElse(""));
            }
            logger.error("getConsultaRuc 08 response: " + response.toString() );


            response.setRazonSocial(Optional.ofNullable((String)map.get(RAZON_SOCIAL)).orElse(""));
            response.setRegion(Optional.ofNullable((String)map.get(REGION)).orElse(""));
            response.setProvincia(Optional.ofNullable((String)map.get(PROVINCIA)).orElse(""));
            response.setDistrito(Optional.ofNullable((String)map.get(DISTRITO)).orElse(""));
            logger.error("getConsultaRuc 09 response: " + response.toString() );

            response.setActividadEconomica(Optional.ofNullable((String)map.get(ACTIVIDAD_ECONOMICA)).orElse(""));
            response.setCodigoSistemaEmisionElect(Optional.ofNullable((String)map.get(SISTEMA_EMISION_ELECTRONICA)).orElse(""));
            response.setCodigoComprobantePago(Optional.ofNullable((String)map.get(COMPROBANTE_PAGO)).orElse(""));
            response.setCodigoPadron(Optional.ofNullable((String)map.get(PADRON)).orElse(""));
            response.setFechaInicioActiSunat(Optional.ofNullable((String)map.get(FECHA_INICIO_ACTIVIDAD)).orElse(""));
            logger.error("getConsultaRuc 10 response: " + response.toString() );

            if (response.getRuc().isEmpty()) {
                throw new InSiteException("El RUC consultado no existe");
            }
            return response;
        } catch (Exception ex) {
            logger.error("Error al consultar el numero de ruc en el 'Servicio de Consulta'", ex);
            throw new InSiteException(ex.getMessage());
        }
    }
}
