package com.incloud.hcp.util;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

    /**
     * Checks if is collection empty.
     *
     * @param collection the collection
     * @return true, if is collection empty
     */
    private static boolean isCollectionEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }


    public static String obtieneMensajeErrorException(Exception e) {
        String retorno = "";
        if (Optional.ofNullable(e.getCause()).isPresent()) {
            if (Optional.ofNullable(e.getCause().getCause()).isPresent()) {
                if (Optional.ofNullable(e.getCause().getCause().getMessage()).isPresent()) {
                    retorno = e.getCause().getCause().getMessage();
                }

            }
            if (Optional.ofNullable(e.getCause().getMessage()).isPresent()) {
                if (Optional.ofNullable(retorno).isPresent()) {
                    retorno = retorno + " / " + e.getCause().getMessage();
                }
                return retorno;
            }
        }

        if (StringUtils.isNotBlank(e.getMessage()))
            return e.getMessage();
        if (StringUtils.isNotBlank(e.getLocalizedMessage()))
            return e.getLocalizedMessage();
        retorno = e.toString();
        return retorno;
    }

    public static String obtieneMensajeErrorExceptionDepurado(Exception e) {
        String error = obtieneMensajeErrorException(e);
//        if (StringUtils.isNotBlank(error)) {
//            int buscar = StringUtils.indexOf(error, "Exception:");
//            if (buscar > 0) {
//                error = StringUtils.right(error, buscar + 1);
//            }
//        }
        return error;
    }

    public static String obtenerSiguienteMesEjercicio(Integer ejercicio, String mes ) {
        Integer nMes = new Integer(mes);
        Integer nAnno = ejercicio;
        if (nMes.intValue() < 12) {
            nMes += 1;
        }
        else {
            nMes = 1;
            nAnno ++;
        }
        String mesProceso = StringUtils.leftPad(nMes.toString().trim(), 2, '0');
        String ejercicioProceso = nAnno.toString().trim();
        return mesProceso + "/" + ejercicioProceso;
    }


    public static Boolean validarEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);

        if (!mather.find()) {
            return false;
        }

        return true;
    }


}