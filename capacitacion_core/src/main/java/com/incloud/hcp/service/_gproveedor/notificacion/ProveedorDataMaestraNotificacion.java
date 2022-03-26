package com.incloud.hcp.service._gproveedor.notificacion;


import com.incloud.hcp._security.UserSession;
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
public class ProveedorDataMaestraNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String LOGO_VENDOR = "com/incloud/hcp/image/vendor_logo.png";
    private final String LOGO_SAN_MARTIN = "com/incloud/hcp/image/sm_logo.png";
    private final String TEMPLATE = "com/incloud/hcp/templates/portal/TmpProveedorDataMaestraAprobado.html";
    private final String TEMPLATERECHAZO = "com/incloud/hcp/templates/portal/TmpProveedorDataMaestraRechazado.html";

    private final String TEMPLATE_COMPRADOR = "com/incloud/hcp/templates/portal/TmpProveedorDataMaestraAprobadoComprador.html";
    private final String TEMPLATERECHAZO_COMPRADOR = "com/incloud/hcp/templates/portal/TmpProveedorDataMaestraRechazadoComprador.html";

    private final String ASUNTOAPROBADO = "IProvider - Proveedor validado por Data Maestra";
    private final String ASUNTORECHAZO = "IProvider - Proveedor rechazado Data Maestra";

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;

    public void enviar(MailSetting mailSetting, Proveedor proveedor, String estado, String motivoRechazo) {

        Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        context.put("contacto", proveedor.getContacto());
        context.put("url", this.urlPortal);
        String asunto="",plantilla="";

        if(estado.equals("APR")){
            asunto=ASUNTOAPROBADO;
            plantilla=TEMPLATE;
        }else{
            asunto=ASUNTORECHAZO;
            plantilla=TEMPLATERECHAZO;
            context.put("motivoRechazo", motivoRechazo);
        }


        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = this.getContentFromTemplateAndContext(context, plantilla);
        this.emailNotificacionGeneral.enviarCorreoSap(proveedor.getEmail(), asunto, content);

    }

    public void enviarComprador(Proveedor proveedor, String estado, UserSession userSession, String motivoRechazo) {

        Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        String nombres = userSession.getFirstName() + " " + userSession.getLastName();
        context.put("contacto", nombres);
        context.put("razonSocial", proveedor.getRazonSocial());
        context.put("ruc", proveedor.getRuc());
        context.put("url", this.urlPortal);
        String asunto="",plantilla="";

        if(estado.equals("APR")){
            asunto=ASUNTOAPROBADO;
            plantilla=TEMPLATE_COMPRADOR;
        }else{
            asunto=ASUNTORECHAZO;
            plantilla=TEMPLATERECHAZO_COMPRADOR;
            context.put("motivoRechazo", motivoRechazo);
        }


        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = this.getContentFromTemplateAndContext(context, plantilla);
        this.emailNotificacionGeneral.enviarCorreoSap(userSession.getMail(), asunto, content);

    }


}
