package com.incloud.hcp.service._gproveedor.notificacion;


import com.incloud.hcp._gproveedor.jco.proveedor.dto.ProveedorRFCResponseDto;
import com.incloud.hcp.bean.custom.SendNotifySupplier;
import com.incloud.hcp.bean.custom.SendNotifyUserDto;
import com.incloud.hcp.domain.AppParametria;
import com.incloud.hcp.domain._gproveedor.domain.Proveedor;
import com.incloud.hcp.enums._gproveedor.EstadoProveedorEnum;
import com.incloud.hcp.exception.PortalException;
import com.incloud.hcp.repository.delta.AppParametriaDeltaRepository;
import com.incloud.hcp.service.notificacion.EmailNotificacionGeneral;
import com.incloud.hcp.service.notificacion.Mail;
import com.incloud.hcp.service.notificacion.NotificarMail;
import com.incloud.hcp.utils.UtilString;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NotificacionFlujoAprobacion extends NotificarMail {

    private final String LOGO_VENDOR = "com/incloud/hcp/image/vendor_logo.png";
    private final String LOGO_SAN_MARTIN = "com/incloud/hcp/image/sm_logo.png";
    private final String TEMPLATE_RECHAZO = "com/incloud/hcp/templates/portal/TmpFlujoAprobacionRechazado.html";
    private final String TEMPLATE_APROBADO_IMPUESTOS = "com/incloud/hcp/templates/portal/TmpFlujoAprobacionAprobadoImpuestos.html";
    private final String TEMPLATE_APROBADO_TESORERIA = "com/incloud/hcp/templates/portal/TmpFlujoAprobacionAprobadoTesoreria.html";

    private final String TEMPLATE_VALIDACION_PENDIENTE = "com/incloud/hcp/templates/portal/TmpValidacionPendiente.html";
    private final String TEMPLATE_ERROR_SUPPLIER_CREATION_SAP = "com/incloud/hcp/templates/portal/TmpErrorSupplierCreationSap.html";

    private final String ASUNTO_RECHAZO_TESORERIA   = "IProvider - Proveedor rechazado por Tesorería";
    private final String ASUNTO_APROBADO_TESORERIA  = "IProvider - Proveedor aprobado por Tesorería";
    private final String ASUNTO_RECHAZO_IMPUESTOS   = "IProvider - Proveedor rechazado por Impuestos";
    private final String ASUNTO_APROBADO_IMPUESTOS  = "IProvider - Proveedor aprobado por Impuestos";

    @Value("${iprovider.portal.url}")
    private  String urlPortal;

    @Autowired private EmailNotificacionGeneral emailNotificacionGeneral;
    @Autowired private AppParametriaDeltaRepository appParametriaDeltaRepository;

    public void sendNotifySupplier( SendNotifySupplier sendNotifySupplier ) {

        //--- Datos
        Proveedor    supplier = sendNotifySupplier.getSupplier();
        String supplierStatus = sendNotifySupplier.getStatusSupplier();

        Mail mail = new Mail();

        VelocityContext context = new VelocityContext();

        String areaIngles;
        String template;
        String asunto;
        String area;

        if( supplierStatus.equals( EstadoProveedorEnum.RECHAZADO_POR_IMPUESTOS.getCodigo() ) ){

            asunto = ASUNTO_RECHAZO_IMPUESTOS;
            template = TEMPLATE_RECHAZO;
            areaIngles = "tax";
            area = "Impuestos";

        }else if( supplierStatus.equals( EstadoProveedorEnum.RECHAZADO_POR_TESORERIA.getCodigo() ) ){

            asunto = ASUNTO_RECHAZO_TESORERIA;
            template = TEMPLATE_RECHAZO;
            areaIngles = "Treasury";
            area = "Tesorería";

        }else if( supplierStatus.equals( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() ) ){

            asunto = ASUNTO_APROBADO_IMPUESTOS;
            template = TEMPLATE_APROBADO_IMPUESTOS;
            areaIngles = "tax";
            area = "Impuestos";

        }else if( supplierStatus.equals( EstadoProveedorEnum.APROBADO_POR_TESORERIA.getCodigo() ) ){

            asunto = ASUNTO_APROBADO_TESORERIA;
            template = TEMPLATE_APROBADO_TESORERIA;
            areaIngles = "Treasury";
            area = "Tesorería";

        }
        else
            throw new PortalException( "Ocurrió un problema al notificar al proveedor. El estado del proveedor no es parte del flujo de aprobaciones." );

        context.put( "motivoRechazo", sendNotifySupplier.getReason() );
        context.put( "contacto", supplier.getContacto() );
        context.put( "area_eng", areaIngles );
        context.put( "area", area );
        context.put( "url", urlPortal );


        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = getContentFromTemplateAndContext( context, template );

        /**
         * Temporalmente comentado por pruebas
         * */
        //emailNotificacionGeneral.enviarCorreoSap( supplier.getEmail(), asunto, content );
        emailNotificacionGeneral.enviarCorreoSap( supplier.getEmail(), asunto, content );
        //--- emailNotificacionGeneral.enviarCorreoSap( "ranulfillo369@gmail.com", asunto, content );

    }

    public void sendNotifyUser( SendNotifyUserDto sendNotifyUser ) {

        //--- Datos
        Proveedor    supplier = sendNotifyUser.getSupplier();
        String supplierStatus = sendNotifyUser.getStatusSupplier();

        AppParametria configToSend;

        Mail mail = new Mail();

        VelocityContext context = new VelocityContext();

        String asunto = "IProvider - Proveedor pendiente de validación";


        if( supplierStatus.equals( EstadoProveedorEnum.REGISTRADO.getCodigo() ) ){

            configToSend =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_1", "1");


        }else if( supplierStatus.equals( EstadoProveedorEnum.APROBADO_DATA_MAESTRA.getCodigo() ) ){

            configToSend =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_2", "1");

        }else if( supplierStatus.equals( EstadoProveedorEnum.APROBADO_POR_IMPUESTOS.getCodigo() ) ){

            configToSend =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Validacion_3", "1");

        }
        else
            throw new PortalException( "Ocurrió un problema al notificar al proveedor. El estado del proveedor no es parte del flujo de aprobaciones." );

        String nombreDestinatarios = UtilString.coalesceTrim( configToSend.getValue2() ).replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "<br />");
        String to = configToSend.getValue1();

        context.put( "NOMBRE_EVALUADORES", nombreDestinatarios );
        context.put( "RAZON_SOCIAL", supplier.getRazonSocial() );
        context.put( "RUC", supplier.getRuc() );
        context.put( "url", urlPortal );


        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = getContentFromTemplateAndContext( context, TEMPLATE_VALIDACION_PENDIENTE);

        emailNotificacionGeneral.enviarCorreoSap( to, asunto, content );

    }

    public void sendNotifyTeamSuport( ProveedorRFCResponseDto input ) {

        Mail mail = new Mail();

        VelocityContext context = new VelocityContext();

        String asunto = "IProvider - Error al crear el proveedor en SAP";

        AppParametria configToSend =  appParametriaDeltaRepository.getByModuloAndLabelAndStatus("GP_CORREOS","Error_Creacion_Proveedor", "1");

        String nombreDestinatarios = UtilString.coalesceTrim( configToSend.getValue2() );
        String to = configToSend.getValue1();
        Proveedor supplier = input.getProveedorSap();

        List<Map<String, Object>> messages = input.getListasapLog().stream()
                .map( message -> {

                    Map<String, Object> mapMessage = new HashMap<>();
                    mapMessage.put( "type"   , message.getTipo()  );
                    mapMessage.put( "class"  , message.getClase() );
                    mapMessage.put( "number" , message.getCode()  );
                    mapMessage.put( "text"   , message.getMesaj() );

                    return mapMessage;

                } ).collect(Collectors.toList());

        context.put( "USUARIOS_SOPORTE", nombreDestinatarios );
        context.put( "RAZON_SOCIAL", supplier.getRazonSocial() );
        context.put( "RUC", supplier.getRuc() );
        context.put( "url", urlPortal );
        context.put( "items", messages );


        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_VENDOR))
                .ifPresent(cid -> context.put("vendor_logo", cid));

        Optional.ofNullable(generateCid(mail.getHtmlMail(), LOGO_SAN_MARTIN))
                .ifPresent(cid -> context.put("sm_logo", cid));

        String content = getContentFromTemplateAndContext( context, TEMPLATE_ERROR_SUPPLIER_CREATION_SAP);


        emailNotificacionGeneral.enviarCorreoSap( to, asunto, content );

    }

}
