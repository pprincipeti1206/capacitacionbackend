package com.incloud.hcp.service.notificacion;

import com.incloud.hcp.service.dto.CerFirmaSinAprobarCorreoDto;
import com.incloud.hcp.service.dto.CerFirmaSinAprobarDto;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Component
public class CertificadoSinAprobarNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateCertificadoSinAprobar.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Certificados Pendientes de Aprobación";

    public void enviar(CerFirmaSinAprobarCorreoDto bean) throws IOException {

        logger.error("CerFirmaSinAprobarCorreoDto enviar 00");
        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

        String realName = "";
        realName = bean.getMtrAprobador().getNombre() + " " + bean.getMtrAprobador().getApellidos();
        List<CerFirmaSinAprobarDto> lista  = bean.getCerCertificadoList();

        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(realName).isPresent())
            realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            realName = "";

        String destino = bean.getMtrAprobador().getEmail();
        VelocityContext context = new VelocityContext();
        context.put("usuarioPublicacionName", realName);
        context.put("lista", lista);
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(destino, ASUNTO, content);
        logger.error("CerFirmaSinAprobarCorreoDto enviar 03");
    }

}
