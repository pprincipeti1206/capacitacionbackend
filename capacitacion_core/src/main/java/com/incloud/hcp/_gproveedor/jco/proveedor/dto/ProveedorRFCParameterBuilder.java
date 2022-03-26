package com.incloud.hcp._gproveedor.jco.proveedor.dto;


import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.domain._gproveedor.domain.ProveedorCuentaBancaria;
import com.incloud.hcp.utils._gproveedor.AppUtil;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProveedorRFCParameterBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ProveedorRFCParameterBuilder.class);
    private static final String I_SOCIEDAD = "COGA";
    private static final String I_ORG_COMPRAS = "OLIM";
    //private static final String RAMO = "Y002";
    private static final String VALOR_DEFAULT="X";
    private static final String VIAS_PAGO_N="BWEGHKUVYA";
    private static final String VIAS_PAGO_E="";
    private static final String CCI="CC";
    private static final String CASOCIADA_FACTURA="4212000";
    private static final String CASOCIADA_RH="4212000";
    private static final String PROVEEDOR_NACIONAL="PROV";




    private ProveedorRFCParameterBuilder() {
    }

    public static void build(
            JCoFunction jCoFunction,
            Proveedor beanProveedor,
            List<ProveedorCuentaBancaria> listaCuentaBancaria,
            String usuarioSap) {
        logger.error("Mapeando tabla");

        JCoParameterList input = jCoFunction.getImportParameterList();
        input.setValue("I_SOCIEDAD", I_SOCIEDAD);
        input.setValue("I_ORG_COMPRAS", I_ORG_COMPRAS);
        input.setValue("I_GRUPO_CTAS", beanProveedor.getTipoProveedor().getCodigoSap());


        JCoStructure jcoEstructuraGen = jCoFunction.getImportParameterList().getStructure("I_DATOS_GENERALES");

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
        String grupoEsquemaProveedor="";
        String viasPago="";
        String tipoNif="";
        String claseImpuesto="";
        String ctaAsociada="";

        String tipoProveedor=beanProveedor.getTipoProveedor().getCodigoSap().toUpperCase();
        if(tipoProveedor.equalsIgnoreCase(PROVEEDOR_NACIONAL)) {
            viasPago=VIAS_PAGO_N;
            grupoEsquemaProveedor="";
            tipoNif="40";
            claseImpuesto=Optional.ofNullable(beanProveedor.getTipoPersona().toUpperCase())
                    .map(codigo -> {
                        switch (beanProveedor.getTipoPersona()) {
                            case "J":
                                return "PJ";
                            case "N":
                                return "PN";
                            default:
                                return "";
                        }
                    }).orElse("");
            claseImpuesto="";

            if(claseImpuesto=="PJ"){
                ctaAsociada=CASOCIADA_FACTURA;
            }else{
//                jcoEstructuraGen.setValue("NUM_DNI",beanProveedor.getRuc().substring(2,10));
//                jcoEstructuraGen.setValue("NOMBRE3",apellidos);
//                jcoEstructuraGen.setValue("NOMBRE4",nombres);
                if(beanProveedor.getTipoComprobante().getCodigoTipoComprobante().equalsIgnoreCase("FA")){
                    ctaAsociada=CASOCIADA_FACTURA;
                }else{
                    ctaAsociada=CASOCIADA_RH;
                }

            }

            String datosContacto1=separarCadenas(beanProveedor.getNombreRepresentanteLegal().trim().toUpperCase()," ");
            String nombresContacto1=datosContacto1.split(",")[0].toUpperCase();
            String apellidosContacto1=datosContacto1.split(",")[1].toUpperCase();

            String datosContacto2=separarCadenas(beanProveedor.getNombrePersonaCreditoCobranza().trim().toUpperCase()," ");
            String nombresContacto2=datosContacto2.split(",")[0].toUpperCase();
            String apellidosContacto2=datosContacto2.split(",")[1].toUpperCase();

            //jcoEstructuraGen.setValue("NOMB_CONTACTO",longitudCadenaContactos(nombresContacto1,35));
            //jcoEstructuraGen.setValue("NOMB_PILA", longitudCadenaContactos(apellidosContacto1,35));
            //jcoEstructuraGen.setValue("TELEFONO_CONTACTO", "");
            //jcoEstructuraGen.setValue("MAIL_CONTACTO",beanProveedor.getEmailRepresentanteLegal().trim());
            //jcoEstructuraGen.setValue("DNI_CONTACTO",beanProveedor.getNroDocumRepresentanteLegal().trim());
            //jcoEstructuraGen.setValue("DEP_CONTACTO","0010");
            //jcoEstructuraGen.setValue("FUNC_CONTACTO","51");

            //jcoEstructuraGen.setValue("NOMB_PILA2",longitudCadenaContactos(apellidosContacto2,35));
            //jcoEstructuraGen.setValue("NOMB_CONTACTO2",longitudCadenaContactos(nombresContacto2,35));
            //jcoEstructuraGen.setValue("TELEFONO_CONTACTO2","");
            //jcoEstructuraGen.setValue("MAIL_CONTACTO2",beanProveedor.getEmailPersonaCreditoCobranza().trim());
            //jcoEstructuraGen.setValue("DNI_CONTACTO2",beanProveedor.getNroDocumPersonaCreditoCobranza().trim());
            //jcoEstructuraGen.setValue("DEP_CONTACTO2", "0009");
            //jcoEstructuraGen.setValue("FUNC_CONTACTO2", "44");
        }
        else {
            grupoEsquemaProveedor="";
            viasPago=VIAS_PAGO_E;
            tipoNif="99";
            claseImpuesto="";
            if(beanProveedor.getTipoComprobante().getCodigoTipoComprobante().equalsIgnoreCase("FA")){
                ctaAsociada=CASOCIADA_FACTURA;
            }else{
                ctaAsociada=CASOCIADA_RH;
            }
        }


        jcoEstructuraGen.setValue("DIRECCION", direccion1.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION2", direccion2.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION3", direccion3.toUpperCase());
        jcoEstructuraGen.setValue("DIRECCION4", direccion4.toUpperCase());
        jcoEstructuraGen.setValue("TELEFONO", beanProveedor.getTelefono().toUpperCase());
        jcoEstructuraGen.setValue("EMAIL", beanProveedor.getEmail().trim().toUpperCase());
        jcoEstructuraGen.setValue("NUM_IDENT_FISC", beanProveedor.getRuc().toUpperCase());
        jcoEstructuraGen.setValue("TIPO_NIF", tipoNif);


        ///Datos de Intelocutor CodigoSap Usuario 1100000045
        jcoEstructuraGen.setValue("FUNC_INTER","ZJ");
        jcoEstructuraGen.setValue("COD_INTER",usuarioSap.trim());
        //////fin//////
        jcoEstructuraGen.setValue("CLASE_IMPUESTO", claseImpuesto);
        jcoEstructuraGen.setValue("RECARGO_EQUIV", "");
        jcoEstructuraGen.setValue("PERSONA_FISICA", "");
        //jcoEstructuraGen.setValue("RAMO", RAMO);


        JCoStructure jcoEstructuraSociedad = jCoFunction.getImportParameterList().getStructure("I_DATOS_SOCIEDAD");

        jcoEstructuraSociedad.setValue("CTA_ASOCIADA",ctaAsociada);
        //jcoEstructuraSociedad.setValue("GRUPO_TESOR",beanProveedor.getCodigoGrupoTesoreria());
        jcoEstructuraSociedad.setValue("COND_PAGO",beanProveedor.getCondicionPago().getCodigoSap());
        jcoEstructuraSociedad.setValue("GRUPO_TOLER","");
        jcoEstructuraSociedad.setValue("VERIF_FRA_DOB",VALOR_DEFAULT);
        jcoEstructuraSociedad.setValue("VIA_PAGO",viasPago);


        JCoStructure jcoEstructuraOrgCompra = jCoFunction.getImportParameterList().getStructure("I_DATOS_ORG_COMP");
        jcoEstructuraOrgCompra.setValue("MONEDA_PEDIDO",beanProveedor.getMoneda().getCodigoMoneda().trim());
        jcoEstructuraOrgCompra.setValue("COND_PAGO",beanProveedor.getCondicionPago().getCodigoSap());
        jcoEstructuraOrgCompra.setValue("GRP_ESQUEMA_PROV",grupoEsquemaProveedor);
        jcoEstructuraOrgCompra.setValue("VERIF_FACT_EM",VALOR_DEFAULT);
        jcoEstructuraOrgCompra.setValue("PED_AUTOMATICO",VALOR_DEFAULT);
        jcoEstructuraOrgCompra.setValue("VER_FACT_REL_SER","");
        jcoEstructuraOrgCompra.setValue("CONC_BONIF_ESPE","");
        jcoEstructuraOrgCompra.setValue("CONT_CONFIRMA","");
        jcoEstructuraOrgCompra.setValue("GRUPO_COMPRA",beanProveedor.getCodigoGrupoCompra());


        JCoStructure jcoEstructuraEstProveedor = jCoFunction.getImportParameterList().getStructure("I_ESTADO_PROVEEDOR");
        jcoEstructuraEstProveedor.setValue("INICIO_BLOQUEO","");
        jcoEstructuraEstProveedor.setValue("FIN_BLOQUEO","");
        jcoEstructuraEstProveedor.setValue("HOMOLOGACION","");
        jcoEstructuraEstProveedor.setValue("CHECK01","");
        jcoEstructuraEstProveedor.setValue("CHECK02","");
        jcoEstructuraEstProveedor.setValue("CHECK03","");
        jcoEstructuraEstProveedor.setValue("CHECK04","");
        jcoEstructuraEstProveedor.setValue("CHECK05","");
        jcoEstructuraEstProveedor.setValue("CHECK06","");
        jcoEstructuraEstProveedor.setValue("CHECK07","");
        jcoEstructuraEstProveedor.setValue("CHECK08","");
        jcoEstructuraEstProveedor.setValue("CHECK09","");
        jcoEstructuraEstProveedor.setValue("CHECK10","");
        jcoEstructuraEstProveedor.setValue("SENSIBLE","");


        ///Cuenta Bancaria
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR");
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR - listaCuentaBancaria size: " + listaCuentaBancaria.size());
        logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR - listaCuentaBancaria: " + listaCuentaBancaria.toString());

        JCoTable jcoTableCtaBancaria = jCoFunction.getImportParameterList().getTable("I_CUENTA_BANCARIA");
        /*if(tipoProveedor.equalsIgnoreCase("PNAC")) {*/

        for (int i = 0; i < listaCuentaBancaria.size(); i++) {
                ProveedorCuentaBancaria proveedorCuentaBancaria = listaCuentaBancaria.get(i);
                logger.error("INGRESANDO A LAS CUENTAS BANCARIAS DEL PROVEEDOR proveedorCuentaBancaria: " + proveedorCuentaBancaria.toString());

                jcoTableCtaBancaria.appendRow();
                jcoTableCtaBancaria.setRow(i);
                jcoTableCtaBancaria.setValue("PAIS", pais );
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
                jcoTableCtaBancaria.setValue("NOMBRE_BANCO", "");
                jcoTableCtaBancaria.setValue("DIRECC_BANCO", "");
                jcoTableCtaBancaria.setValue("CODIGO_ABA", "");
                jcoTableCtaBancaria.setValue("CODIGO_SWIFT", "");
                jcoTableCtaBancaria.setValue("CODIGO_IBAN", "");
            }

        /*}*/
        JCoTable jcoTableRetencion = jCoFunction.getImportParameterList().getTable("I_RETENCIONES");
        if(tipoProveedor.equalsIgnoreCase("PNAC")) {
        if(claseImpuesto=="PJ"){

            jcoTableRetencion.appendRow();
            jcoTableRetencion.setRow(0);
            //jcoTableRetencion.setValue("TIPO_RETEN", "DB");
            //jcoTableRetencion.setValue("INDIC_RETEN", "00");
            jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
            logger.error("PADRON" + beanProveedor.getCodigoPadron());
            //--- String tipoRetencionI="";
            //--- if(beanProveedor.getCodigoPadron().equalsIgnoreCase("NINGUNO")){
            //---     tipoRetencionI="I3";
            //--- }else{
            //---     tipoRetencionI="I0";
            //--- }
            //--- jcoTableRetencion.appendRow();
            //--- jcoTableRetencion.setRow(1);
            //jcoTableRetencion.setValue("TIPO_RETEN", "I6");
            //jcoTableRetencion.setValue("INDIC_RETEN", tipoRetencionI);
            jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
        }else{
            if(beanProveedor.getTipoComprobante().getCodigoTipoComprobante().equalsIgnoreCase("FA")){
                jcoTableRetencion.appendRow();
                jcoTableRetencion.setRow(0);
                //jcoTableRetencion.setValue("TIPO_RETEN", "DB");
                //jcoTableRetencion.setValue("INDIC_RETEN", "00");
                jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
                logger.error("PADRON" + beanProveedor.getCodigoPadron());
                //-- String tipoRetencionI="";
                //-- if(beanProveedor.getCodigoPadron().equalsIgnoreCase("NINGUNO")){
                //--     tipoRetencionI="I3";
                //-- }else{
                //--     tipoRetencionI="I0";
                //-- }
                jcoTableRetencion.appendRow();
                jcoTableRetencion.setRow(1);
                //jcoTableRetencion.setValue("TIPO_RETEN", "I6");
                //jcoTableRetencion.setValue("INDIC_RETEN", tipoRetencionI);
                jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
            }else{
                jcoTableRetencion.appendRow();
                jcoTableRetencion.setRow(0);
                //jcoTableRetencion.setValue("TIPO_RETEN", "R4");
                //jcoTableRetencion.setValue("INDIC_RETEN", "R0");
                jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
            }
        }
        }else{
            jcoTableRetencion.appendRow();
            jcoTableRetencion.setRow(0);
            //jcoTableRetencion.setValue("TIPO_RETEN", "ND");
            //jcoTableRetencion.setValue("INDIC_RETEN", "N0");
            jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
            jcoTableRetencion.appendRow();
            jcoTableRetencion.setRow(1);
            //jcoTableRetencion.setValue("TIPO_RETEN", "NI");
            //jcoTableRetencion.setValue("INDIC_RETEN", "N0");
            jcoTableRetencion.setValue("WT_SUBJCT", VALOR_DEFAULT);
        }
        logger.error("FIN MAPEO PROVEEDOR");

    }

    public static String separarCadenas(String cadena,String aux){
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

    public static String longitudCadenaContactos(String cadena,int l){

        String cadenaReturn="";
        if(cadena.length()<=l){
            cadenaReturn=cadena;
        }else{
            if(cadena.length()>l){
                cadenaReturn=cadena.substring(0,34);
            }
        }


        return cadenaReturn;
    }


}
