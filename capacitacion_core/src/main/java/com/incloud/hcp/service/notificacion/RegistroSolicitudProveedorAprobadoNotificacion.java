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
public class RegistroSolicitudProveedorAprobadoNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateRegistroSolicitudAprobadoNotificacion.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Gestiòn Proveedor Aprobado";

    public void enviar(
                       PreRegistroProveedor pre

                       ) throws IOException {
        logger.error("RegistroSolicitudProveedorAprobadoNotificacion :  " + pre);

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

       /* String realName = "";
        realName = proveedor.getRazonSocial();*/

         String nombreDestinatarios = pre.getRazonSocial();
         String  emailDestinatarios = pre.getEmail();
        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(nombreDestinatarios).isPresent())
            nombreDestinatarios = nombreDestinatarios.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            nombreDestinatarios = "";

        /*if (Optional.ofNullable(concepto).isPresent())
            concepto = concepto.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            concepto = "";*/

        // BEGIN: CODIGO NAIX
        VelocityContext context = new VelocityContext();
        //context.put("usuarioPublicacionName", realName);
        //context.put("codigoFactura", facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        context.put("usuarioNotificacion", nombreDestinatarios);
        //context.put("concepto", concepto);
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(emailDestinatarios, ASUNTO, content);
        // END: CODIGO NAIX
    }

}
