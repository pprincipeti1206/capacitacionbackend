package com.incloud.hcp.utils;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DocumentXmlUtils {

    private final static Log log = LogFactory.getLog(DocumentXmlUtils.class);

    /**
     * Realiza la conversion de String a un documento xml
     * @param s
     * @return
     */
    private static String string2DOM(String s)
    {
        Document tmpX=null;
        DocumentBuilder builder = null;
        String msgerror="";
        try{
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }catch(javax.xml.parsers.ParserConfigurationException error){
            msgerror="Error creando factory  "+error.getMessage();
            return msgerror;
        }
        try{
            tmpX=builder.parse(new ByteArrayInputStream(s.getBytes()));
        }catch(org.xml.sax.SAXException error){
            int posImagen = StringUtils.indexOf(s, "<img>");
            int posTexto = StringUtils.indexOf(s, "<txt>");
            if (posTexto > 0 && posImagen > 0) {
                msgerror="Error Imagen: Los textos deben estar en la parte Superior o Inferior de la Imagen ";
                return msgerror;
            }

            msgerror="Error parseo "+error.getMessage();
            return msgerror;
        }catch(IOException error){
            msgerror="Error generando "+error.getMessage();
            return msgerror;
        }
        return (tmpX!=null?"":msgerror);
    }

    /**
     * Gets the bean to json string.
     *
     * @param beanClass the bean class
     * @return the bean to json string
     */
    public static String getBeanToJsonString(Object beanClass) {
        return new Gson().toJson(beanClass);
    }

    /**
     * Gets the bean to json string.
     *
     * @param beanClasses the bean classes
     * @return the bean to json string
     */
    public static String getBeanToJsonString(Object... beanClasses) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object beanClass : beanClasses) {
            stringBuilder.append(getBeanToJsonString(beanClass));
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }


}
