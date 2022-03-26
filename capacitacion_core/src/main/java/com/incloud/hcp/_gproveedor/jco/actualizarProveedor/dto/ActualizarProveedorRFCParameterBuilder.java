package com.incloud.hcp._gproveedor.jco.actualizarProveedor.dto;

import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCParameterBuilder;
import com.incloud.hcp.domain._gproveedor.domain.Banco;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import com.incloud.hcp.domain._gproveedor.domain.Ubigeo;
import com.incloud.hcp.utils._gproveedor.AppUtil;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ActualizarProveedorRFCParameterBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ProveedorRFCParameterBuilder.class);
    private static final String CCI="CC";
    private static final String PROVEEDOR_NACIONAL="PROV";


    private ActualizarProveedorRFCParameterBuilder() {
    }

    public static void build(
            JCoFunction jCoFunction,
            Proveedor beanProveedor,
            List<ProveedorCuentaBancaria> listaCuentaBancaria,
            String usuarioSap) {
        logger.error("Mapeando tabla");

        JCoStructure jcoEstructuraGen   = jCoFunction.getImportParameterList().getStructure("I_DATOS_GENERALES");

        jCoFunction.getImportParameterList().setValue( "I_LIFNR", beanProveedor.getAcreedorCodigoSap() );
        jcoEstructuraGen.setValue("CONCEP_BUSQUED",beanProveedor.getRuc());

        String razonSocial = beanProveedor.getRazonSocial().trim().toUpperCase();
        razonSocial = AppUtil.reemplazarCaracteresEspeciales(razonSocial);

        if (razonSocial.length() <= 40) {
            jcoEstructuraGen.setValue("NOMBRE", razonSocial.toUpperCase());
        }
        else {
            if (razonSocial.length() > 40 && razonSocial.length() <= 80) {
                String razonSocial01 = razonSocial.substring(0, 39);
                String razonSocial02 = razonSocial.substring(40, razonSocial.length());
                jcoEstructuraGen.setValue("NOMBRE",razonSocial01.toUpperCase());
                jcoEstructuraGen.setValue("NOMBRE2",razonSocial02.toUpperCase());
            } else {
                String razonSocial01 = razonSocial.substring(0, 39);
                String razonSocial02 = razonSocial.substring(40, 80);
                String razonSocial03 = razonSocial.substring(80, razonSocial.length());
                jcoEstructuraGen.setValue("NOMBRE",razonSocial01.toUpperCase());
                jcoEstructuraGen.setValue("NOMBRE2",razonSocial02.toUpperCase());
                jcoEstructuraGen.setValue("NOMBRE3",razonSocial03.toUpperCase());
            }
        }

        String datosPersonaNatural=separarCadenas(razonSocial,"/");
        String nombres=datosPersonaNatural.split(",")[0];
        String apellidos=datosPersonaNatural.split(",")[1];

        logger.error("RAZON SOCIAL: "+razonSocial);

        String pais = Optional.ofNullable(beanProveedor.getPais())
                .map(tp -> tp.getCodigoUbigeoSap())
                .map(c -> c.trim()).get();
        String region = "00";

        logger.error("REGION"+region.toString());
        String provincia = Optional.ofNullable(beanProveedor.getProvincia())
                .map(tp -> Optional.ofNullable(tp.getDescripcion()).orElse(""))
                .map(c -> c.trim()).get();
        String distrito = Optional.ofNullable(beanProveedor.getDistrito())
                .map(tp -> Optional.ofNullable(tp.getDescripcion()).orElse(""))
                .map(c -> c.trim()).get();
        jcoEstructuraGen.setValue("PAIS", pais.toUpperCase());
        jcoEstructuraGen.setValue("REGION", region.toUpperCase());
        jcoEstructuraGen.setValue("POBLACION", provincia.toUpperCase());
        jcoEstructuraGen.setValue("DISTRITO", distrito.toUpperCase());
        jcoEstructuraGen.setValue("COD_POSTAL",Optional.ofNullable(beanProveedor.getCodigoPostal().toUpperCase().trim()).orElse(""));
        String direccion = "";
        String direccion1="";
        String direccion2="";
        String direccion3="";
        String direccion4="";
        if (Optional.ofNullable(beanProveedor.getDireccionFiscal()).isPresent()) {
            direccion = beanProveedor.getDireccionFiscal().toUpperCase();
            direccion = AppUtil.reemplazarCaracteresEspeciales(direccion);
            int longitud = direccion.length();
            if(longitud<=60){
                direccion1=direccion.substring(0,direccion.length());
            }
            else if (longitud > 60 && longitud <= 120) {
                logger.error("DIRECCION 2");
                direccion1=direccion.substring(0,60);
                direccion2=direccion.substring(60,direccion.length());
            }else if(longitud > 120 && longitud <= 180){
                logger.error("DIRECCION 3");
                direccion1=direccion.substring(0,60);
                direccion2=direccion.substring(60,120);
                direccion3=direccion.substring(120,direccion.length());
            }else{
                logger.error("DIRECCION 4");
                direccion1=direccion.substring(0,60);
                direccion2=direccion.substring(60,120);
                direccion3=direccion.substring(120,180);
                direccion4=direccion.substring(180,direccion.length());
            }
        }
        logger.error("DIRECION: " + direccion);
        logger.error("DIRECION: " + direccion2);
        String tipoNif="";
        String claseImpuesto="";

        String tipoProveedor=beanProveedor.getTipoProveedor().getCodigoSap().toUpperCase();


        jcoEstructuraGen.setValue("DIRECCION", direccion1.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION2", direccion2.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION3", direccion3.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION4", direccion4.toUpperCase());
        jcoEstructuraGen.setValue("TELEFONO", beanProveedor.getTelefono().toUpperCase());
        jcoEstructuraGen.setValue("EMAIL", beanProveedor.getEmail().trim().toUpperCase());


        ///Cuenta Bancaria
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR");
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR - listaCuentaBancaria size: " + listaCuentaBancaria.size());
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR - listaCuentaBancaria: " + listaCuentaBancaria.toString());

        //--- Obtenemos el país del proveedor
        Ubigeo paisProveedor = beanProveedor.getPais();

        JCoTable jcoTableCtaBancaria = jCoFunction.getImportParameterList().getTable("I_CUENTA_BANCARIA");
        /*if(tipoProveedor.equalsIgnoreCase("PNAC")) {*/

        for (int i = 0; i < listaCuentaBancaria.size(); i++) {

                ProveedorCuentaBancaria proveedorCuentaBancaria = listaCuentaBancaria.get(i);

                //--- Obtenemos los datos del banco
                Banco banco = proveedorCuentaBancaria.getBanco();


                logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR proveedorCuentaBancaria: " + proveedorCuentaBancaria.toString());

                jcoTableCtaBancaria.appendRow();
                jcoTableCtaBancaria.setRow(i);
                jcoTableCtaBancaria.setValue("PAIS", paisProveedor.getCodigoUbigeoSap() );
                jcoTableCtaBancaria.setValue("ENTIDAD_BANCARIA", proveedorCuentaBancaria.getBanco().getClaveBanco());
                jcoTableCtaBancaria.setValue("MONEDA", proveedorCuentaBancaria.getMoneda().getCodigoMoneda());
                jcoTableCtaBancaria.setValue("NRO_CUENTA", proveedorCuentaBancaria.getNumeroCuenta());
                jcoTableCtaBancaria.setValue("CCI", CCI);
                jcoTableCtaBancaria.setValue("REFERENCIA", proveedorCuentaBancaria.getNumeroCuentaCci());

                if (razonSocial.length() <= 35) {
                    jcoTableCtaBancaria.setValue("NOMBRE_TITULAR", razonSocial.toUpperCase());
                } else {
                    if (razonSocial.length() > 35 && razonSocial.length() <= 60) {
                        String razonSocial01 = razonSocial.substring(0, razonSocial.length());
                        jcoTableCtaBancaria.setValue("NOMBRE_TITULAR", razonSocial01.toUpperCase());

                    } else {
                        String razonSocial01 = razonSocial.substring(0, 59);
                        jcoTableCtaBancaria.setValue("NOMBRE_TITULAR", razonSocial01.toUpperCase());

                    }
                }
                jcoTableCtaBancaria.setValue("DIRECC_TITULAR", "");
                jcoTableCtaBancaria.setValue("NOMBRE_BANCO", banco.getDescripcion() );
                jcoTableCtaBancaria.setValue("DIRECC_BANCO", "");
                jcoTableCtaBancaria.setValue("CODIGO_ABA", "");
                jcoTableCtaBancaria.setValue("CODIGO_SWIFT", "");
                jcoTableCtaBancaria.setValue("CODIGO_IBAN", "");
            }

        /*}*/

        logger.error(" INPUT - UPDATE SUPPLIER SAP - I_CUENTA_BANCARIA: " + jCoFunction.getImportParameterList().toJSON() );
        logger.error(" INPUT - UPDATE SUPPLIER SAP - DATOS GENERALES : " + jCoFunction.getImportParameterList().getStructure("I_DATOS_GENERALES").toJSON() );

        logger.error("FIN MAPEO PROVEEDOR");

    }

    private static String separarCadenas(String cadena,String aux){
        String [] razonPartida=cadena.split(" ");
        int longRazonSocial=razonPartida.length;
        String nombres="";
        String apellidos="";
        if(longRazonSocial<=2){
            nombres =razonPartida[0];
            apellidos=razonPartida[longRazonSocial-1];
        }else if(longRazonSocial==3){
            nombres =razonPartida[0];
            apellidos=razonPartida[1]+aux+razonPartida[2];
        }else{
            if(longRazonSocial==4){
                nombres =razonPartida[0]+aux+razonPartida[1];
                apellidos=razonPartida[2]+aux+razonPartida[3];
            }else{
                nombres =razonPartida[0]+aux+razonPartida[1]+aux+razonPartida[2];
                apellidos=razonPartida[3]+aux+razonPartida[4];
            }
        }
        return nombres+","+apellidos;
    }


}
