package com.incloud.hcp.service.notificacion;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain.CerCertificado;
import com.incloud.hcp.domain.MtrAprobador;
import com.incloud.hcp.domain.MtrEstado;
import com.incloud.hcp.domain.MtrProveedor;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class CertificadoNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Logger log = LoggerFactory.getLogger(CertificadoNotificacion.class);

    //private final String TEMPLATE = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateCertificadoNotificacion.html";
    private final String TEMPLATE = "com/incloud/hcp/templates/TemplateCertificadoNotificacion.html";
    private final String ASUNTO = "Iprovider – Certificado pendiente de aprobación";
    //fdfd
    //private final String TEMPLATELIBERACION = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateLiberacion.html";
    private final String TEMPLATELIBERACION = "com/incloud/hcp/templates/TemplateLiberacion.html";
    private final String ASUNTOLIBERACION = "Liberación Nota de Pedido";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    @Autowired
    private AppParametriaDeltaRepository appParametriaDeltaRepository;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    public void enviar(MailSetting mailSetting, MtrProveedor proveedor, UserSession user, MtrEstado estado, CerCertificado certificado, String destinatarios) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */

        String RealName = user.getFirstName() + " " + user.getLastName();

        //if is \r \n| \n to code HTML
        RealName = RealName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        String Descripcion = (estado.getDescripcion() != null ? estado.getDescripcion() : "").replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", (RealName != null ? RealName : ""));
        variables.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        variables.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        variables.put("estado", Descripcion);
        variables.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destinatarios, null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", (RealName != null ? RealName : ""));
        context.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        context.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        context.put("estado", Descripcion);
        context.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(destinatarios, ASUNTO, content);
        // END: CODIGO NAIX
    }

    public void enviarFirma(MailSetting mailSetting, MtrProveedor proveedor, MtrAprobador mtrAprobador, MtrEstado estado, CerCertificado certificado) throws IOException {

        Charset.forName("UTF-8").newEncoder();
        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */
        String realName = mtrAprobador.getNombre()  + " " + mtrAprobador.getApellidos();

        //if is \r \n| \n to code HTML
        realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        String Descripcion = (estado.getDescripcion() != null ? estado.getDescripcion() : "").replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", (realName != null ? realName : ""));
        variables.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        variables.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        variables.put("estado", Descripcion);
        variables.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(mtrAprobador.getEmail(), null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", (realName != null ? realName : ""));
        context.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        context.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        context.put("estado", Descripcion);
        context.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(mtrAprobador.getEmail(), ASUNTO, content);
        // END: CODIGO NAIX
    }


    public void enviarMail(MailSetting mailSetting, String realName, MtrProveedor proveedor, MtrEstado estado, CerCertificado certificado, String destinatarios) throws IOException {

        Charset.forName("UTF-8").newEncoder();
        log.error("enviarMail :: realName " +  realName);
        log.error("enviarMail :: destinatarios " +  destinatarios);
        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */
        /*= mtrAprobador.getNombre() != null ? mtrAprobador.getNombre() : " ";
        realName = realName + mtrAprobador.getApellidos() != null ? mtrAprobador.getApellidos() : "";
        realName = realName.trim();*/


        //if is \r \n| \n to code HTML
        realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        String Descripcion = (estado.getDescripcion() != null ? estado.getDescripcion() : "").replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", (realName != null ? realName : ""));
        variables.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        log.error("enviarMail :: proveedor.getRazonSocial() " +  proveedor.getRazonSocial());
        variables.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        log.error("enviarMail :: proveedor.getRuc() " +  proveedor.getRuc());
        variables.put("estado", Descripcion);
        log.error("enviarMail :: estado " +  Descripcion);
        variables.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));
        log.error("enviarMail :: certificado.getCodigoCertificado() " +  certificado.getCodigoCertificado());

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destinatarios, null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", (realName != null ? realName : ""));
        context.put("nombreProveedor", (proveedor.getRazonSocial() != null ? proveedor.getRazonSocial() : ""));
        context.put("ruc", (proveedor.getRuc() != null ? proveedor.getRuc() : ""));
        context.put("estado", Descripcion);
        context.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(destinatarios, ASUNTO, content);
        // END: CODIGO NAIX
    }


    public void enviarMailLiberacion(MailSetting mailSetting, String nroPedido, String nombreProveedor, String destinatarios) throws IOException {

        Charset.forName("UTF-8").newEncoder();
        //log.error("enviarMail :: realName " +  realName);
        log.error("Inicio ---- enviarMailLoiberacion :: destinatarios " +  destinatarios);
        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATELIBERACION)));
        String content = new String(template.getBytes("UTF-8"));
        */
        /*= mtrAprobador.getNombre() != null ? mtrAprobador.getNombre() : " ";
        realName = realName + mtrAprobador.getApellidos() != null ? mtrAprobador.getApellidos() : "";
        realName = realName.trim();*/


        //if is \r \n| \n to code HTML
        //realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        //String Descripcion = (estado.getDescripcion() != null ? estado.getDescripcion() : "").replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        //variables.put("nombreProveedor", (realName != null ? realName : ""));
        variables.put("nombreProveedor", (nombreProveedor != null ? nombreProveedor: ""));
        log.error("enviarMailLoiberacion nombreProveedor ::" +  nombreProveedor);
        variables.put("nroPedido", (nroPedido != null ? nroPedido : ""));
        log.error("enviarMailLoiberacion nroPedido :: nroPedido " +  nroPedido);
        //variables.put("estado", Descripcion);
       // log.error("enviarMail :: estado " +  Descripcion);
        //variables.put("codigocertificado", (certificado.getCodigoCertificado() != null ? certificado.getCodigoCertificado() : ""));
        //log.error("enviarMail :: certificado.getCodigoCertificado() " +  certificado.getCodigoCertificado());

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destinatarios, null, ASUNTOLIBERACION, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al enviarMailLoiberacion ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("nombreProveedor", (nombreProveedor != null ? nombreProveedor: ""));
        context.put("nroPedido", (nroPedido != null ? nroPedido : ""));
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATELIBERACION);
        //AppParametria paramFechaInicial = this.appParametriaDeltaRepository.obtenerParametroPorModuloYLabel("EMAIL_AUXILIAR", "LIBERACION_PEDIDO");
        /*if(paramFechaInicial != null && !StringUtils.isBlank(paramFechaInicial.getValue1()))
            destinatarios = paramFechaInicial.getValue1().trim();*/
        this.emailNotificacionGeneral.enviarCorreoSap(destinatarios, ASUNTO, content);
        // END: CODIGO NAIX

        log.error("Fin ---- enviarMailLoiberacion :: destinatarios ");

    }

}
