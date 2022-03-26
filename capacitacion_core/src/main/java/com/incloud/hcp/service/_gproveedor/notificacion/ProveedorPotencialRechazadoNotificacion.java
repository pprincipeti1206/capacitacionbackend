package com.incloud.hcp.service._gproveedor.notificacion;

import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.domain._gproveedor.domain.PreRegistroProveedor;
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

/**
 * Created by Administrador on 13/11/2017.
 */
@Component
public class ProveedorPotencialRechazadoNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String LOGO_VENDOR = "com/incloud/hcp/image/vendor_logo.png";
    private final String LOGO_SAN_MARTIN = "com/incloud/hcp/image/sm_logo.png";
    private final String TEMPLATE = "com/incloud/hcp/templates/portal/TmpProveedorPotencialAprobado.html";
    private final String TEMPLATERECHAZO = "com/incloud/hcp/templates/portal/TmpProveedorPotencialRechazado.html";
    private final String TEMPLATERECHAZO_COMPRADOR = "com/incloud/hcp/templates/portal/TmpProveedorPotencialRechazadoComprador.html";
    private final String ASUNTO = "IProvider - Proveedor potencial aprobado";
    private final String ASUNTORECHAZO = "IProvider - Proveedor potencial";

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;

    public void enviar(MailSetting mailSetting, PreRegistroProveedor preRegistro) {

        Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        context.put("contacto", preRegistro.getContacto());

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = this.getContentFromTemplateAndContext(context, TEMPLATERECHAZO);
        this.emailNotificacionGeneral.enviarCorreoSap(preRegistro.getEmail(), ASUNTORECHAZO, content);

    }

    public void enviarComprador(PreRegistroProveedor preRegistro, UserSession userSession) {

        Mail mail = new Mail();
        VelocityContext context = new VelocityContext();
        String nombres = userSession.getFirstName() + " " + userSession.getLastName();
        context.put("contacto", nombres);
        context.put("razonSocial", preRegistro.getRazonSocial());
        context.put("ruc", preRegistro.getRuc());

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = this.getContentFromTemplateAndContext(context, TEMPLATERECHAZO_COMPRADOR);
        this.emailNotificacionGeneral.enviarCorreoSap(userSession.getMail(), ASUNTORECHAZO, content);

    }
}
