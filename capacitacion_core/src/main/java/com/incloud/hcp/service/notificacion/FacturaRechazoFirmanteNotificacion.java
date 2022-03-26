package com.incloud.hcp.service.notificacion;

import com.incloud.hcp.domain.FacFactura;
import com.incloud.hcp.domain.MtrProveedor;
import com.incloud.hcp.domain.MtrUsuarioFacturacion;
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
public class FacturaRechazoFirmanteNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateFacturaRechazadaFirmante.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Factura";

    public void enviar(MailSetting mailSetting,
                       MtrUsuarioFacturacion mtrUsuarioFirmante,
                       MtrUsuarioFacturacion mtrUsuarioCupa,
                       MtrProveedor proveedor,
                       String concepto,
                       FacFactura facFactura) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

        String realName = "";
        realName = mtrUsuarioCupa.getNombres() + " " + mtrUsuarioCupa.getApellidos();

        //if is \r \n| \n to code HTML
        if (Optional.ofNullable(realName).isPresent())
            realName = realName.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            realName = "";

        if (Optional.ofNullable(concepto).isPresent())
            concepto = concepto.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        else
            concepto = "";

        // BEGIN: CODIGO NAIX
        String usuarioFirmante = mtrUsuarioFirmante.getNombres() + " " + mtrUsuarioFirmante.getApellidos();
        String destino = mtrUsuarioCupa.getEmail();
        VelocityContext context = new VelocityContext();
        context.put("usuarioFirmante", usuarioFirmante);
        context.put("usuarioPublicacionName", realName);
        context.put("codigoFactura", facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        context.put("concepto", concepto);
        context.put("proveedor", proveedor.getRazonSocial());
        context.put("url",this.urlPortal);

        String content = this.getContentFromTemplateAndContext(context, TEMPLATE);
        this.emailNotificacionGeneral.enviarCorreoSap(destino, ASUNTO, content);
        // END: CODIGO NAIX
    }

}
