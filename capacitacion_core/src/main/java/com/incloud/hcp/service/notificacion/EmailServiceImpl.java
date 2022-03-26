package com.incloud.hcp.service.notificacion;


import com.google.common.html.HtmlEscapers;
import com.sap.cloud.account.TenantContext;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;
import okhttp3.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;


public class EmailServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    protected final String CORREO_FROM = "irequest@csticorp.biz";
    protected final String LOGO_COPEINCA = "com/incloud/hcp/image/cope_logo.jpg";
    protected final String TEMPLATE_INCIDENCA = "com/incloud/hcp/template/TmpIncidencia.html";
    protected final String TEMPLATE_MATERIAL = "com/incloud/hcp/template/TmpMaterial.html";

    @Autowired
    public JavaMailSender emailSender;

    @Value("${destination.email}")
    protected String destinationEmail;

    @Resource
    private TenantContext tenantContext;

    private static final String ON_PREMISE_PROXY = "OnPremise";
    private static final int COPY_CONTENT_BUFFER_SIZE = 1024;

    private Proxy getProxy(String proxyType) {
        String proxyHost = null;
        int proxyPort;
        if (ON_PREMISE_PROXY.equals(proxyType)) {
            log.debug("Ingreso getProxy - " + ON_PREMISE_PROXY);
            // Get proxy for on-premise destinations
            proxyHost = System.getenv("HC_OP_HTTP_PROXY_HOST");
            proxyPort = Integer.parseInt(System.getenv("HC_OP_HTTP_PROXY_PORT"));
        } else {
            // Get proxy for internet destinations
            log.debug("Ingreso getProxy - INTERNET" );
            proxyHost = System.getProperty("http.proxyHost");
            proxyPort = Integer.parseInt(System.getProperty("http.proxyPort"));
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    }

    private void injectHeader(HttpURLConnection urlConnection, String proxyType) {
        if (ON_PREMISE_PROXY.equals(proxyType)) {
            log.debug("Ingreso injectHeader - " + ON_PREMISE_PROXY);
            // Insert header for on-premise connectivity with the consumer subaccount name
            urlConnection.setRequestProperty("SAP-Connectivity-ConsumerAccount", tenantContext.getAccountName());
        }
    }

    private void copyStream(InputStream inStream, OutputStream outStream) throws IOException {
        byte[] buffer = new byte[COPY_CONTENT_BUFFER_SIZE];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
    }

    protected void enviarCorreoSap(String paraCorreo, String asuntoCorreo, String bodyCorreo) {
        HttpURLConnection urlConnection = null;
        try {
            //bodyCorreo = bodyCorreo.replaceAll("\"", "'");
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
            log.error("Ingresando enviarCorreoSap 09 correos " + paraCorreo);

            String variablePrueba = "";
            variablePrueba = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
            variablePrueba = variablePrueba + "xmlns:sen=\"http://www.example.org/sendMailOdm/\">";
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
            //variablePrueba = variablePrueba + "<to1>pavelprincipe@gmail.com</to1>";
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
                    .addHeader("Authorization", "Basic c2p1YXJlekBjc3RpY29ycC5iaXo6UGEkJHcwcmQ=")
                    .build();
            log.error("Ingresando enviarCorreoSap 09");
            Response response = client.newCall(request).execute();
            log.error("Ingresando enviarCorreoSap 10");

            //InputStream instream = urlConnection.getInputStream();
            //OutputStream outstream = response.getOutputStream();
            //copyStream(instream, outstream);
            //ResponseBody response_=response.body();
            //response_.string();
        }
        catch(Exception e) {
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
    }

    protected JSONObject devuelveJsonCorreo(String paraCorreo, String asuntoCorreo, String body) {
        try{
            //log.debug("Informacion - devuelveJsonCorreo body previo: " + body);
            body  = HtmlEscapers.htmlEscaper().escape(body);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("para", paraCorreo);
            jsonObject.put("asunto", asuntoCorreo);
            jsonObject.put("correo", body);
            log.debug("Informacion - devuelveJsonCorreo: " + jsonObject);
            return jsonObject;
        }
        catch(Exception e) {
            return null;
        }
    }


    public VelocityEngine getVelocityEngine() {
        VelocityEngine velocity = new VelocityEngine();
        velocity.setProperty(Velocity.RESOURCE_LOADER, "classpath");
        velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocity.setProperty("input.encoding", "UTF-8");
        velocity.init();
        return velocity;
    }

    public String getContentMail(VelocityContext context, String template) {
        VelocityEngine velocity = getVelocityEngine();
        Template t = velocity.getTemplate(template);
        StringWriter w = new StringWriter();
        t.merge(context, w);
        return w.toString();
    }



    public void sendResponseIncidencia(MailSetting mailSetting, String textoIncidencia, String tipoIncidencia, String nombres, String odm, String
            embarcacion, String sistema, String equipo, String descOdm, String actividad) {


        //Mail mail = new Mail();
        VelocityContext context = new VelocityContext();

        String txtOdm = "";
        if (odm != null && !odm.equalsIgnoreCase(""))
            txtOdm = Long.parseLong(odm) + "";

        /*context.put("textoIncidencia", textoIncidencia);
        context.put("tipoIncidencia",tipoIncidencia);
        context.put("nombres",nombres);
        context.put("odm",txtOdm);*/
        //
        /*context.put("embarcacion",embarcacion);
        context.put("sistema",sistema);
        context.put("equipo",equipo);
        context.put("descOdm",descOdm);
        context.put("actividad",actividad);*/
        String body = "<html>";
        body = body + "<head>";
        body = body + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>";
        body = body + "</head>";
        body = body + "<body style='width: 600px;margin: auto;padding: 0;font-family: Arial;font-size: 14px;color: #333'>";
        body = body + "<table cellspacing='0' cellpadding='0' width='600px' class='header' ";
        body = body + "style='border-bottom-style: solid;border-bottom-color: #F0AB00;border-bottom-width: 8px;padding-top: 13px; padding-bottom: 12px'>";
        body = body + "<tr style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<td style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<table cellspacing='0' cellpadding='0'>";
        body = body + "<tr style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<td class='logo' style='border-collapse: collapse;width: 61px;padding: 0;margin: 0'>";
        body = body + "<img src='https://mir-s3-cdn-cf.behance.net/projects/404/df647053268567.Y3JvcCwyMzY3LDE4NTMsMCw0Ng.png'  width='100'  height='100' ";
        body = body + "style='border: 1px black;height: auto;line-height: 100%;outline: none;text-decoration: none'/>";
        body = body + "</td>";
        body = body + "</tr>";
        body = body + "</table>";
        body = body + "</td>";
        body = body + "</tr>";
        body = body + "</table>";
        body = body + "<table cellpadding='0' cellspacing='0' width='600px'>";
        body = body + "<tr style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<td class='colophon' style='border-collapse: collapse;width: 600px;padding: 0;margin: 0;font-size: 11px;color: #888;line-height: 13px'>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Sistema:</b> " + sistema;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Embarcaci&oacute;n:</b> " + embarcacion;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Equipo:</b> " + equipo;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Descripci&oacute;n de la ODM:</b> " + descOdm;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Remitente:</b> " + nombres;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Descripci&oacute;n de la Operaci&oacute;n:</b> " + actividad;
        body = body + "</p>";
        body = body + "<p style='font-family: Arial;font-size: 11px;line-height: 13px;color: #888'>";
        body = body + "<b>Texto:</b> " + textoIncidencia;
        body = body + "</p>";
        body = body + "</td>";
        body = body + "</tr>";
        body = body + "</table>";
        body = body + "</body>";
        body = body + "</html>";
        body = body + "";

        /*String body = Optional.ofNullable(TEMPLATE_INCIDENCA)
                .map(url -> url + "")
                .map(template -> {
                    int i = 0;
                    return getContentMail(context, template);
                })
                .orElse("")*/
        //mail.setMailSetting(mailSetting);
        try {
            String asunto = "Incidencia de Actividad Nro. Odm " + txtOdm;
            ///mail.enviar(mailSetting.getEmailTo(), null, asunto, content);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            //helper.setFrom(mailSetting.getEmailFrom());
            //helper.setTo(mailSetting.getEmailTo());
            // String asunto = "Incidencia de Actividad";
            helper.setSubject(asunto);
            helper.setText(body, true);

            //emailSender.send(message);
            //logger.error("correo pprincipe" + mailSetting.getEmailTo());
            // this.enviarCorreoSap(mailSetting.getEmailTo(), asunto, body);

        } catch (Exception ex) {
            logger.error("Error al enviar notificacion al usuario aprobador ", ex);
        }


        //============================================================================================
        /*VelocityContext context = new VelocityContext();
        context.put("textoIncidencia", textoIncidencia);
        context.put("tipoIncidencia",tipoIncidencia);

        String body = Optional.ofNullable(TEMPLATE_INCIDENCA)
                .map(url -> url + "")
                .map(template -> {
                    int i = 0;
                    return getContentMail(context, template);
                })
                .orElse("");
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(mailSetting.getEmailFrom());
            helper.setTo(mailSetting.getEmailTo());
            String asunto = "Incidencia de Actividad";
            helper.setSubject(asunto);
            helper.setText(body, true);

            //emailSender.send(message);
            logger.error("correo pprincipe" + mailSetting.getEmailTo());
            this.enviarCorreoSap(mailSetting.getEmailTo(), asunto, body);
        }catch(Exception e){
            throw new RuntimeException(e);
        }*/
    }



    public void sendResponseMaterial(MailSetting mailSetting, String usuario, String odm) {
        //Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        //context.put("usuario", usuario);
        //context.put("odm", odm);
        //context.put("listaMaterial", lista);
        /*Optional<Usuario> oUsuario = Optional.of(aprobador).map(AprobadorSolicitud::getUsuario);
        String name = oUsuario.map(usuario -> String.join(" ", usuario.getNombre(), usuario.getApellido()))
                .orElse("Desconocido");

        Optional<Proveedor> oProveedor = Optional.ofNullable(solicitud)
                .map(SolicitudBlacklist::getProveedor);

        String ruc = oProveedor.map(Proveedor::getRuc).orElse("Desconocido");*/

        /*context.put("nombreAprobador", name);
        context.put("nombreProveedor", oProveedor.map(Proveedor::getRazonSocial).orElse("Desconocido"));
        context.put("rucProveedor", ruc);*/

        /*Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));*/

        /*Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_COPEINCA))
                .ifPresent(cid -> context.put("cope_logo", cid));*/

        /*String body = Optional.ofNullable(TEMPLATE_MATERIAL)
                .map(url -> url + "")
                .map(template -> {
                    int i = 0;
                    return getContentMail(context, template);
                })
                .orElse("");*/

        String body = "<html>";
        body = body + "<head>";
        body = body + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>";
        body = body + "</head>";
        body = body + "<body style='width: 600px;margin: auto;padding: 0;font-family: Arial;font-size: 14px;color: #333'>";
        body = body + "<table cellspacing='0' cellpadding='0' width='600px' class='header' style='border-bottom-style: solid;border-bottom-color: #F0AB00;border-bottom-width: 8px;padding-top: 13px; padding-bottom: 12px'>";
        body = body + "<tr style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<td style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<table cellspacing='0' cellpadding='0'>";
        body = body + "<tr style='border-collapse: collapse;width: 600px;padding: 0;margin: 0'>";
        body = body + "<td class='logo' style='border-collapse: collapse;width: 61px;padding: 0;margin: 0'>";
        body = body + "<img src='https://mir-s3-cdn-cf.behance.net/projects/404/df647053268567.Y3JvcCwyMzY3LDE4NTMsMCw0Ng.png' width='100' height='100' style='border: 1px black;height: auto;line-height: 100%;outline: none;text-decoration: none'/>";
        body = body + "</td>";
        body = body + "</tr>";
        body = body + "</table>";
        body = body + "</td>";
        body = body + "</tr>";
        body = body + "</table>";
        body = body + "<div>";
        body = body + "<p>Se requiere su apoyo en generar la devoluci&oacute;n (reingreso) de los siguientes materiales correspondientes a la ODM Nro. " + odm + "</p>";
        body = body + "<table style='width:100%;border:1px solid black'>";
        body = body + "<thead>";
        body = body + "<tr>";
        body = body + "<th>Material</th>";
        body = body + "<th>Descripci&oacute;n</th>";
        body = body + "<th>Cantidad</th>";
        body = body + "</tr>";
        body = body + "</thead>";
        body = body + "<tbody>";
        /*for(MaterialCustom material: lista) {
            body = body + "<tr>";
            body = body + "<td align='middle'>" + material.getCodigoMaterialSap() + "</td>";
            body = body + "<td align='middle'>" + material.getDescripcionMaterial()+ "</td>";
            body = body + "<td align='middle'>" + material.getCantidadMaterialDevuelto() + "</td>";
            body = body + "</tr>";
        }*/
        body = body + "</tbody>";
        body = body + "</table>";
        body = body + "</div>";
        body = body + "<p>Por favor coordinar con el responsable de la ODM&#44; el usuario " + usuario + " a fin de hacer efectiva la devoluci&oacute;n f&iacute;sica de los materiales.</p>";
        body = body + "<p>Gracias&#44;</p>";
        body = body + "<p>" + usuario +"</p>";
        body = body + "<p>&Aacute;rea de Mantenimiento.</p>";
        body = body + "<p>Este es un correo autogenerado por favor no responder a esta direcci&oacute;n.</p>";
        body = body + "</body>";
        body = body + "</html>";
        //mail.setMailSetting(mailSetting);
        try {
            // String asunto = "Devolución de Materiales ODM " + odm;
            //mail.enviar(mailSetting.getEmailTo(), null, asunto, content);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            //helper.setFrom(mailSetting.getEmailFrom());
            //helper.setTo(mailSetting.getEmailTo());
            String asunto = "Devolución de Materiales ODM " + odm;
            helper.setSubject(asunto);
            helper.setText(body, true);

            //emailSender.send(message);
            //logger.error("correo pprincipe" + mailSetting.getEmailTo());
            //this.enviarCorreoSap(mailSetting.getEmailTo(), asunto, body);

        } catch (Exception ex) {
            logger.error("Error al enviar notificacion al usuario aprobador ", ex);
        }



        //======================================================================
        /*VelocityContext context = new VelocityContext();
        context.put("usuario", usuario);
        context.put("odm", odm);
        context.put("listaMaterial", lista);

        String body = Optional.ofNullable(TEMPLATE_MATERIAL)
                .map(url -> url + "")
                .map(template -> {
                    int i = 0;
                    return getContentMail(context, template);
                })
                .orElse("");
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(mailSetting.getEmailFrom());
            helper.setTo(mailSetting.getEmailTo());
            String asunto = "Devolución de Materiales ODM " + odm;
            helper.setSubject(asunto);
            helper.setText(body, true);

            //emailSender.send(message);
            logger.error("correo pprincipe" + mailSetting.getEmailTo());
            this.enviarCorreoSap(mailSetting.getEmailTo(), asunto, body);
        }catch(Exception e){
            throw new RuntimeException(e);
        }*/
    }


}

