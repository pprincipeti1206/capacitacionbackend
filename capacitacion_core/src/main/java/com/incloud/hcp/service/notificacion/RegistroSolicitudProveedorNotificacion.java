package com.incloud.hcp.service.notificacion;

import com.incloud.hcp.domain._gproveedor.domain.PreRegistroProveedor;
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
public class RegistroSolicitudProveedorNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateRegistroSolicitudProveedorNotificacion.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Registro Solicitud Proveedor";

    public void enviar(PreRegistroProveedor pre,
                       String emailDestinatario,
                       String nombreDestinatario
                       ) throws IOException {

        logger.error("RegistroSolicitudProveedorAprobadoNotificacion :  " + pre);
        logger.error("RegistroSolicitudProveedorAprobadoNotificacion_emailDestinatario :  " + emailDestinatario);
        logger.error("RegistroSolicitudProveedorAprobadoNotificacion_nombreDestinatario :  " + nombreDestinatario);

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

        String razonSocial = "";
        razonSocial = pre.getRazonSocial();


        if (Optional.ofNullable(razonSocial).isPresent())
            razonSocial = razonSocial.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            razonSocial = "";

        //String razonSocial = "";
        //razonSocial = pre.getRazonSocial();


        if (Optional.ofNullable(nombreDestinatario).isPresent())
            nombreDestinatario = nombreDestinatario.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            nombreDestinatario = "";

       /* if (Optional.ofNullable(concepto).isPresent())
            concepto = concepto.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            concepto = "";*/

        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        //context.put("usuarioPublicacionName", realName);
        //context.put("codigoFactura", facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        context.put("usuarioNotificacion", nombreDestinatario);
        context.put("nombrePreProveedor", razonSocial);
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(emailDestinatario, ASUNTO, content);
        // END: CODIGO NAIX
    }

}
