package com.incloud.hcp.service._gproveedor.notificacion;


import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.service.notificacion.EmailNotificacionGeneral;
import com.incloud.hcp.service.notificacion.Mail;
import com.incloud.hcp.service.notificacion.MailSetting;
import com.incloud.hcp.service.notificacion.NotificarMail;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActualizacionDeDatosNotificacion extends NotificarMail {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String LOGO_VENDOR = "com/incloud/hcp/image/vendor_logo.png";
    private final String LOGO_SAN_MARTIN = "com/incloud/hcp/image/sm_logo.png";
    private final String TEMPLATE = "com/incloud/hcp/templates/portal/TmpActualizacionDeDatos.html";
    private final String ASUNTO = "IProvider - Actualización de datos del proveedor";

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;

    public void enviar(MailSetting mailSetting, Proveedor proveedor, String mailAdministrador){
        Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        context.put("nombreProveedor", proveedor.getRazonSocial());
        context.put("ruc", proveedor.getRuc());
        context.put("url",this.urlPortal);
        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

//        String content = Optional.ofNullable(TEMPLATE)
//                .map(url -> url + "")
//                .map(template -> {
//                    int i = 0;
//                    return getContentMail(context, template);
//                })
//                .orElse("");

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(mailAdministrador, ASUNTO, content);

    }

}
