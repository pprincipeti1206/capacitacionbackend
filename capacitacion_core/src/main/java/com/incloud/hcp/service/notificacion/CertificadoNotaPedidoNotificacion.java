package com.incloud.hcp.service.notificacion;

import com.incloud.hcp.domain.CerNotaPedido;
import com.incloud.hcp.domain.MtrProveedor;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

/* librerias naix */
/////////////////////

@Component
public class CertificadoNotaPedidoNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*private final String TEMPLATE1 = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateCertificadoNotaPedidoNotificacionAprobado.html";
    private final String TEMPLATE2 = "webapps/cogaiproviderbackend/WEB-INF/classes/templates/TemplateCertificadoNotaPedidoNotificacionDenegado.html";*/

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateCertificadoNotaPedidoNotificacionAprobado.html";
    private final String TEMPLATE2 = "com/incloud/hcp/templates/TemplateCertificadoNotaPedidoNotificacionDenegado.html";
    private final String TEMPLATE3 = "com/incloud/hcp/templates/TemplateErrorSap.html";


    private final String ASUNTO = "IProvider - Nota de Pedido";
    private final String ASUNTO2 = "Error creación nota de pedido ";
    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    public void enviar(MailSetting mailSetting, MtrProveedor proveedor, String destino, String concepto, CerNotaPedido cerNotaPedido, int nplantilla, int nrealName) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;
        if (nplantilla == 2) TEMPLATE = TEMPLATE2;

        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */
        String realName = "";

        if (nrealName == 2)
            realName = proveedor.getRazonSocial();
        else {
            realName = cerNotaPedido.getNombreCompletoCompra();
            if (!Optional.ofNullable(realName).isPresent()) {
                realName = cerNotaPedido.getEmailUserCompra();
            }
            if (!Optional.ofNullable(realName).isPresent()) {
                realName = cerNotaPedido.getUserCompra();
            }
        }

        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(realName).isPresent())
            realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            realName = "";

        concepto = concepto.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", realName);
        variables.put("codigopedido", (cerNotaPedido.getCodigoNotaPedidoSap() != null ? cerNotaPedido.getCodigoNotaPedidoSap() : ""));
        variables.put("concepto", concepto);
        variables.put("proveedor", proveedor.getRazonSocial());

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destino, null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */

        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", realName);
        context.put("codigopedido", (cerNotaPedido.getCodigoNotaPedidoSap() != null ? cerNotaPedido.getCodigoNotaPedidoSap() : ""));
        context.put("concepto", concepto);
        context.put("proveedor", proveedor.getRazonSocial());
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        logger.error("valor del content en el email: " + content);
        this.emailNotificacionGeneral.enviarCorreoSap(destino, ASUNTO, content);
        // END: CODIGO NAIX
    }

    public void enviarFirma(
            MailSetting mailSetting,
            MtrProveedor proveedor,
            String destino,
            String concepto,
            CerNotaPedido cerNotaPedido,
            int nplantilla,
            String realName) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;
        if (nplantilla == 2) TEMPLATE = TEMPLATE2;

        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */

        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(realName).isPresent())
            realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            realName = "";

        concepto = concepto.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", realName);
        variables.put("codigopedido", (cerNotaPedido.getCodigoNotaPedidoSap() != null ? cerNotaPedido.getCodigoNotaPedidoSap() : ""));
        variables.put("concepto", concepto);
        variables.put("proveedor", proveedor.getRazonSocial());

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destino, null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", realName);
        context.put("codigopedido", (cerNotaPedido.getCodigoNotaPedidoSap() != null ? cerNotaPedido.getCodigoNotaPedidoSap() : ""));
        context.put("concepto", concepto);
        context.put("proveedor", proveedor.getRazonSocial());
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(destino, ASUNTO, content);
        // END: CODIGO NAIX

    }

    public void enviarNotaPedidoErrorSap(
            String numPedido,
            String texto,
            String destino
    ) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE3;


        // COMENTARIO NAIX
        /*
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE)));
        String content = new String(template.getBytes("UTF-8"));
        */

        //if is \r \n| \n to code HTML

        // COMENTARIO NAIX
        /*
        Map<String, String> variables = new HashMap<>();
        variables.put("usuarioPublicacionName", realName);
        variables.put("codigopedido", (cerNotaPedido.getCodigoNotaPedidoSap() != null ? cerNotaPedido.getCodigoNotaPedidoSap() : ""));
        variables.put("concepto", concepto);
        variables.put("proveedor", proveedor.getRazonSocial());

        content = variables.
                keySet().stream().
                reduce(content,
                        (acc, e) -> acc.replaceAll("\\$\\{" + e + "\\}",
                                variables.get(e)));

        Mail mail = new Mail();
        mail.setMailSetting(mailSetting);
        try {
            mail.enviar(destino, null, ASUNTO, content);
        } catch (EmailException ex) {
            logger.error("Error al enviar notificación al administrador ", ex);
        }
        */
        // BEGIN: CODIGO NAIX
        logger.error("enviarNotaPedidoErrorSap - inicio");
        VelocityContext context = new VelocityContext();
        context.put("textoError", texto);
        context.put("url",this.urlPortal);

        String asuntox = ASUNTO2 + " - " + numPedido;
        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        logger.error("enviarNotaPedidoErrorSap - content " + content);
        logger.error("enviarNotaPedidoErrorSap - " + numPedido + " - " + destino);
        this.emailNotificacionGeneral.enviarCorreoSap(destino, asuntox, content);
        // END: CODIGO NAIX

    }


}
