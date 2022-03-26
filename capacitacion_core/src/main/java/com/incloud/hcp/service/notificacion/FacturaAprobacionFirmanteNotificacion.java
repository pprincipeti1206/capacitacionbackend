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
public class FacturaAprobacionFirmanteNotificacion extends NotificarMail {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TEMPLATE1 = "com/incloud/hcp/templates/TemplateFacturaAprobacionFirmante.html";

    private final String TEMPLATE2 = "com/incloud/hcp/templates/TemplateFacturaAprobacion0Firmante.html";

    // BEGIN: CODIGO NAIX
    @Autowired
    private EmailNotificacionGeneral emailNotificacionGeneral;
    // END: CODIGO NAIX

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    private final String ASUNTO = "IProvider - Factura";

    public void enviar(MailSetting mailSetting,
                       MtrUsuarioFacturacion mtrUsuarioFirmante,
                       MtrUsuarioFacturacion mtrUsuarioFirmanteSgte,
                       MtrProveedor proveedor,
                       String concepto,
                       FacFactura facFactura) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE1;

        String realName = "";
        realName = mtrUsuarioFirmanteSgte.getNombres() + " " + mtrUsuarioFirmanteSgte.getApellidos();

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

        String destino = mtrUsuarioFirmanteSgte.getEmail();
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 destino: " + destino);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 usuarioFirmante: " + usuarioFirmante);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 usuarioPublicacionName: " + realName);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 concepto: " + realName);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 codigoFactura: " + facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 concepto: " + concepto);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 proveedor: " + proveedor.getRazonSocial());

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

        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion FIN");
    }

    public void enviarAprobador0(MailSetting mailSetting,
                       MtrUsuarioFacturacion mtrUsuarioFirmante,
                       MtrUsuarioFacturacion mtrUsuarioFirmanteSgte,
                       MtrProveedor proveedor,
                       String concepto,
                       FacFactura facFactura) throws IOException {

        Charset.forName("UTF-8").newEncoder();

        String TEMPLATE = TEMPLATE2;

        String realName = "";
        realName = mtrUsuarioFirmanteSgte.getNombres() + " " + mtrUsuarioFirmanteSgte.getApellidos();

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

        String destino = mtrUsuarioFirmanteSgte.getEmail();
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 destino: " + destino);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 usuarioFirmante: " + usuarioFirmante);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 usuarioPublicacionName: " + realName);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 concepto: " + realName);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 codigoFactura: " + facFactura.getTipoFactura() + "-" + facFactura.getSerieFactura() + "-" + facFactura.getNumeroFactura());
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 concepto: " + concepto);
        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion 00 proveedor: " + proveedor.getRazonSocial());

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

        logger.error("Ingresando FacturaAprobacionFirmanteNotificacion FIN");
    }

}
