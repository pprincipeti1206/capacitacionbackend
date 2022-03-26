package com.incloud.hcp.service.notificacion;

import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

@Component
public class GestionProveedorNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateGestionProveedorNotificacion.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Gestión Proveedor";

    public void enviar(
                       String emailDestinatarios,
                       String nombreDestinatarios,
                       String razonSocial
                       ) throws IOException {

        logger.error("GestionProveedorNotificacion_emailDestinatarios :  " + emailDestinatarios);
        logger.error("GestionProveedorNotificacion_nombreDestinatarios :  " + nombreDestinatarios);
        logger.error("GestionProveedorNotificacion_razonSocial :  " + razonSocial);

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

       /* String realName = "";
        realName = proveedor.getRazonSocial();*/


        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(nombreDestinatarios).isPresent())
            nombreDestinatarios = nombreDestinatarios.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            nombreDestinatarios = "";

        if (Optional.ofNullable(razonSocial).isPresent())
            razonSocial = razonSocial.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            razonSocial = "";

        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        //context.put("usuarioPublicacionName", realName);
        //context.put("codigoFactura", facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        context.put("usuarioNotificacion", nombreDestinatarios);
        context.put("nombreProveedor", razonSocial);
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(emailDestinatarios, ASUNTO, content);
        // END: CODIGO NAIX
    }

}
