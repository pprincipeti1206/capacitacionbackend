package com.incloud.hcp.utils;

import java.util.TimeZone;

/**
 * Created by Bazalar Carlos on 27/08/2017.
 */
public class Constants {

    public static final String PATRON_ACENTOS = "[^\\p{ASCII}]";
    public static final String USER_ANONYMOUS = "anonymousUser";
    public static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    public static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
    public static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
    public static final String PATTERN =
            "^" + ATOM + "+(\\." + ATOM + "+)*@"
                    + DOMAIN
                    + "|"
                    + IP_DOMAIN
                    + ")$";

    public static final String ZONATIME_DEFAULT = TimeZone.getDefault().toString();
    public static final String ZONATIME_LIMA = "America/Lima";

    public static final String TIPO_DATO_NUMERICO = "N";
    public static final String TIPO_DATO_CARACTER = "C";

    public static final String IZQUIERDA = "I";
    public static final String DERECHA = "D";

    public static final String ARCHIVO_FIJO = "F";
    public static final String ARCHIVO_VARIABLE = "V";
    public static final String ENVIO_RED = "1";
    public static final String ENVIO_FTP = "2";
    public static final String ENVIO_MIXTO = "3";
    public static final String FORMATEAR_ALFANUMERICOS = "A";
    public static final String FORMATEAR_TODOS = "T";
    public static final String CARPETA_SALIDA = "1";
    public static final String CARPETA_HISTORICO = "2";
    public static final String DELIMITADOR_EXTENSION_ARCHIVO = ".";
    public static final String DELIMITADOR_NOMBRE_ARCHIVO = "_";
    public final static String STRING_TABULADOR = "\\t";
    public final static String STRING_TABULADOR_XML = "\t";
    public final static String STRING_COMILLA = "\"";
    public final static String STRING_ENCABEZADO_XML = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>";
    public final static String STRING_IGUAL = "=";
    public final static char CHAR_TABULADOR = '\t';
    public final static char CHAR_NULO = '\0';

    public static final String EXTENSION_TMP = "TMP";
    public static final String EXTENSION_ZIP = "ZIP";
    public static final String NO = "N";
    public static final String SI = "S";
    public static final String N = "N";
    public static final String S = "S";
    public static final String X = "X";

    public static String OK = "0";
    public static String ERROR = "1";
    public static String OK_MESSAGE = "OK";
    public static String ERROR_MESSAGE = "ERROR";
    public static String IDIOMA_DEFAULT_ES = "es";

    public static String ESTADO_ACTIVO = "1";
    public static String ESTADO_INACTIVO = "0";

    public static int PAGINATION_DEFAUTL_PAGE_FIRST = 1;
    public static int PAGINATION_DEFAUTL_PAGE_SIZE = 10;

    public static final String P12_FILE_EXTENSION = "P12";
    public static final String PFX_FILE_EXTENSION = "PFX";
    public static final String JPG_FILE_EXTENSION = "JPG";

    public static final String[] BACKGROUND_COLOR_GRAPH =  {
        "rgba(220,220,220,0.8)",
        "rgba(151,187,205,1)",
        "rgba(169, 3, 41, 0.7)",
        "#949FB1",
        "#4D5360",
        "rgba(151,187,205,0.5)",
        "#7DC27D",
        "#0e90d2",
        "#f7ecb5",
        "#00b3ee"
    };

    public static final String[] HOVER_BACKGROUND_COLOR_GRAPH = {
        "rgba(220,220,220,0.7)",
        "rgba(151,187,205,0.8)",
        "rgba(169, 3, 41, 0.7)",
        "#A8B3C5",
        "#616774",
        "rgba(151,187,205,0.75)",
        "#7DC27D",
        "#0e90d2",
        "#f7ecb5",
        "#00b3ee"
    };

    /** The Constant BLANK. */
    public static final String BLANK = "";

    /** The Constant COLON. */
    public static final String COLON = " : ";

    /** The Constant DASH. */
    public static final String DASH = " - ";

       /** The Constant ENTRY. */
    public static final String ENTRY = "Entry";

    /** The Constant EXIT. */
    public static final String EXIT = "Exit";


    public static final String CODIGO_AGRUPADO_NOTA_PEDIDO = "NP";
    public static final String CODIGO_AGRUPADO_CERTIFICADO = "CE";
    public static final String CODIGO_AGRUPADO_FACTURA = "FA";
    public static final String CODIGO_AGRUPADO_FACTURA_PUBLICADO = "FP";
    public static final String TIPO_MATERIAL = "M";
    public static final String TIPO_SERVICIO = "S";

    public static String CERO = "0";
    public static String UNO = "1";

    public static String NP_CERRADA = "CERRADA";
    public static String NP_ABIERTA = "ABIERTA";
    public static String TIPO_NP_CD = "NP_CD";
    public static final String MENSAJE_SISTEMA_APAGADO = "El Sistema está en Modo Apagado, no se puede realizar ninguna Operación de Grabación";

    public static final String RECHAZADO = "R";
    public static final String APROBADO = "A";
    public static final String EN_PROCESO = "P";
    public static final String LETRA_ERROR = "E";
    public static final String ERROR_ABAP = "Problemas en el proceso SAP ABAP";
    public static final String ERROR_JCO_JAVA = "Problemas en el proceso en la conexión SAP o Timeout SAP expirado";

    public static final String SIMBOLO_MONEDA_USD = "USD";
    public static final String SIGLA_PERU_MONEDA = "PEN";
    public static final String SIGLA_PERU_MONEDA_ALTERNATIVO = "NSO";

}
