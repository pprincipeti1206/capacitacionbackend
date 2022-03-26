package com.incloud.hcp.service.notificacion;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.HttpURLConnection;
import java.util.Map;

@Component
public class EmailNotificacionGeneral extends NotificarMail {

    private final Logger log = LoggerFactory.getLogger(EmailNotificacionGeneral.class);
    private final String CORREO_SOPORTE = "soportesap@coga.pe";

    //private final String TEMPLATE = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateCertificadoNotificacion.html";
    // private final String ASUNTO = "IProvider - Nota de Pedido";
    //fdfd
    // private final String TEMPLATELIBERACION = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateLiberacion.html";
    // private final String ASUNTOLIBERACION = "Liberación Nota de Pedido";

    @Value("${destination.email}")
    protected String destinationEmail;

    @Value("${activar.envio.correo.soportesap}")
    protected boolean activarSoporte;

    public void enviarCorreoSap(String paraCorreo, String asuntoCorreo, String bodyCorreo) {
        HttpURLConnection urlConnection = null;

        bodyCorreo = bodyCorreo.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");;
        try {
            //bodyCorreo = bodyCorreo.replaceAll("\"", "'");
            //bodyCorreo = bodyCorreo.replaceAll("#","#/");
            log.error("Ingresando enviarCorreoSap 01a - bodyCorreo: " + bodyCorreo);
            //bodyCorreo = bodyCorreo.replaceAll("\\r", "");
            //log.debug("Ingresando enviarCorreoSap 01b - bodyCorreo: " + bodyCorreo);
            //bodyCorreo = bodyCorreo.replaceAll("\r", "");
            //log.debug("Ingresando enviarCorreoSap 01c - bodyCorreo: " + bodyCorreo);
            //bodyCorreo = bodyCorreo.replaceAll("\\r|\\n", "");
            log.error("Ingresando enviarCorreoSap 01c - bodyCorreo: " + bodyCorreo);

            //bodyCorreo = bodyCorreo.replace(System.getProperty("line.separator"), " ");
            //bodyCorreo = HtmlEscapers.htmlEscaper().escape(bodyCorreo);
            Context ctx = new InitialContext();
            ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx.lookup(
                    "java:comp/env/connectivityConfiguration");
            log.error("Ingresando enviarCorreoSap 02 - configuration: " + configuration);
            // Get destination configuration for "destinationName"
            DestinationConfiguration destConfiguration = configuration.getConfiguration(this.destinationEmail);
            if (destConfiguration == null) {
                return;
            }
            log.error("Ingresando enviarCorreoSap 03 - destConfiguration: " + destConfiguration);

            // Get the destination URL
            String valueURL = destConfiguration.getProperty("URL");
            //URL url = new URL(valueURL);
            //log.debug("Ingresando enviarCorreoSap 04 - url: " + url);

            //String proxyType = destConfiguration.getProperty("ProxyType");
            //Proxy proxy = getProxy(proxyType);
            //log.debug("Ingresando enviarCorreoSap 05 - proxyType: " + proxyType);
            //log.debug("Ingresando enviarCorreoSap 05b - proxy: " + proxy);

            //urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection = (HttpURLConnection) url.openConnection(proxy);

            // Insert the required header in the request for on-premise destinations
            //injectHeader(urlConnection, proxyType);
            log.error("Ingresando enviarCorreoSap 09 - valueURL: " + valueURL);
            Map<String, String> mapProperties = destConfiguration.getAllProperties();
            for (Map.Entry<String, String> entry : mapProperties.entrySet())
            {
                log.error("Ingresando enviarCorreoSap 06b properties - " + entry.getKey() + "/" + entry.getValue());

            }
            //String cloudConnectorLocationId = destConfiguration.getProperty("CloudConnectorLocationId");
            log.error("Ingresando enviarCorreoSap 09");

            // Copy content from the incoming response to the outgoing response
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/xml");
            if (activarSoporte) {
                paraCorreo = paraCorreo + "," + CORREO_SOPORTE;
            }
            log.error("Ingresando enviarCorreoSap 09 correos " + paraCorreo);

            String variablePrueba = "";
            variablePrueba = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
            variablePrueba = variablePrueba + "xmlns:sen=\"http://www.example.org/sendMailIprovider/\">";
            variablePrueba = variablePrueba + "<soapenv:Header/>";
            variablePrueba = variablePrueba + "<soapenv:Body>";
            variablePrueba = variablePrueba + "<sen:NewOperation>";
            variablePrueba = variablePrueba + "<auxiliar>auxi</auxiliar>";
            variablePrueba = variablePrueba + "<subject>" + asuntoCorreo + "</subject>";
            variablePrueba = variablePrueba + "<body><![CDATA[" + bodyCorreo + "]]></body>";
            int count = 0;
            if (paraCorreo != null) {
                String[] to = paraCorreo.split(",");
                if (to != null && to.length > 0) {
                    for (String t : to) {
                        if (!t.isEmpty()) {
                            count++;
                            variablePrueba = variablePrueba + "<to" + count + ">" + t.trim()  +"</to" + count +">";
                        }
                    }
                }
            }
            //variablePrueba = variablePrueba + "<to1>ranulfillo369@gmail.com</to1>";
            //variablePrueba = variablePrueba + "<to2>pprincipe@csticorp.biz</to2>";
            variablePrueba = variablePrueba + "</sen:NewOperation>";
            variablePrueba = variablePrueba + "</soapenv:Body>";
            variablePrueba = variablePrueba + "</soapenv:Envelope>";

            log.error("Ingresando enviarCorreoSap 09 bodyResponse - variablePrueba: " + variablePrueba);
            RequestBody body = RequestBody.create(mediaType, variablePrueba);


            Request request = new Request.Builder()
                    .url(valueURL)
                    .post(body)
                    .addHeader("content-type", "text/xml")
                    .addHeader("Authorization", "Basic cHByaW5jaXBlLmNvbnNAZ21haWwuY29tOkluaWNpbzAxJCQ=")
                    .build();
            log.error("Ingresando enviarCorreoSap 09");
            //Response response = client.newCall(request).execute();

            try (Response response = client.newCall(request).execute()) {
                String strr = response.body().string();
                log.error("" + strr + response.message());

                log.error( "Response Server - Body " + response.body().toString() );
                log.error( "Response Server - Code - " + response.code() );
                log.error( "Response Server - Message - " + response.message() );

            }

            log.error("Ingresando enviarCorreoSap 10");

            //InputStream instream = urlConnection.getInputStream();
            //OutputStream outstream = response.getOutputStream();
            //copyStream(instream, outstream);
            //ResponseBody response_=response.body();
            //response_.string();
        }
        catch(Exception e) {
            log.error("Ingresando enviarCorreoSap ERROR FIN");
            e.printStackTrace();

            // Connectivity operation failed
            String errorMessage = "Connectivity operation failed with reason: "
                    + e.getMessage()
                    + ". See "
                    + "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
                    + "local environment in case your environment uses "
                    + "an HTTP proxy for the outbound Internet "
                    + "communication.";
            log.error("Connectivity operation failed", e);

        }
        log.error("Ingresando enviarCorreoSap OK FIN");
    }

    public void send( String to, String asunto, String body ) {
        try {

            String valueURL = "https://l400313-iflmap.hcisbp.br1.hana.ondemand.com/cxf/iproviderMail";

            // Copy content from the incoming response to the outgoing response
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/xml");

            String trama = "";
            trama = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
            trama = trama + "xmlns:sen=\"http://www.example.org/sendMailIprovider/\">";
            trama = trama + "<soapenv:Header/>";
            trama = trama + "<soapenv:Body>";
            trama = trama + "<sen:NewOperation>";
            trama = trama + "<auxiliar>auxi</auxiliar>";
            trama = trama + "<subject>" + asunto + "</subject>";
            trama = trama + "<body><![CDATA[" + body + "]]></body>";
            int count = 0;
            if (to != null) {
                String[] toList = to.split(",");
                if (toList != null && toList.length > 0) {
                    for (String t : toList) {
                        if (!t.isEmpty()) {
                            count++;
                            trama = trama + "<to" + count + ">" + t.trim()  +"</to" + count +">";
                        }
                    }
                }
            }
            trama = trama + "<to1>ranulfillo369@gmail.com</to1>";
            //variablePrueba = variablePrueba + "<to2>pprincipe@csticorp.biz</to2>";
            trama = trama + "</sen:NewOperation>";
            trama = trama + "</soapenv:Body>";
            trama = trama + "</soapenv:Envelope>";

            RequestBody requestBody = RequestBody.create(mediaType, trama);

            Request request = new Request.Builder()
                    .url(valueURL)
                    .post( requestBody )
                    .addHeader("content-type", "text/xml")
                    .addHeader("Authorization", "Basic cHByaW5jaXBlLmNvbnNAZ21haWwuY29tOkluaWNpbzAxJCQ=")
                    .build();
            log.error("Ingresando enviarCorreoSap 09");
            //Response response = client.newCall(request).execute();

            try (Response response = client.newCall(request).execute()) {
                String strr = response.body().string();
                log.error("" + strr + response.message());

                log.error( "Response Server - Body " + response.body().toString() );
                log.error( "Response Server - Code - " + response.code() );
                log.error( "Response Server - Message - " + response.message() );

            }

            log.error("Ingresando enviarCorreoSap 10");

        }
        catch(Exception e) {
            log.error("Ingresando enviarCorreoSap ERROR FIN");
            e.printStackTrace();

            // Connectivity operation failed
            String errorMessage = "Connectivity operation failed with reason: "
                    + e.getMessage()
                    + ". See "
                    + "logs for details. Hint: Make sure to have an HTTP proxy configured in your "
                    + "local environment in case your environment uses "
                    + "an HTTP proxy for the outbound Internet "
                    + "communication.";
            log.error("Connectivity operation failed", e);

        }
        log.error("Ingresando enviarCorreoSap OK FIN");
    }

}
