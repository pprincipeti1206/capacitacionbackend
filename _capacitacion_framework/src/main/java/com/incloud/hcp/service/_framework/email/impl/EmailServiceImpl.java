package com.incloud.hcp.service._framework.email.impl;

import com.incloud.hcp.service._framework.email.EmailService;
import com.incloud.hcp.service._framework.email.bean.EMailResponse;
import com.incloud.hcp.service._framework.email.bean.EmailParams;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@ConditionalOnProperty(name = "email.enabled")
@Component("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration config;

    public EMailResponse sendMessage(EmailParams mail, Map<String, Object> model)  {
        EMailResponse response = new EMailResponse();
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());

            helper.setTo(mail.getTo());
            helper.setFrom(mail.getFrom());

            if (mail.getContentType() != null) {
                helper.setText(mail.getContentType());
            }
            if (mail.getTemplateFreemaker() != null) {
                Template t = config.getTemplate(mail.getTemplateFreemaker() + ".ftl");
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
                helper.setText(html, true);
            }

            if (mail.getCc() != null) {
                helper.setCc(mail.getCc().trim());
            } else if (mail.getCcArray() != null) {
                helper.setCc(mail.getCcArray());
            } else if (mail.getBcc() != null) {
                helper.setBcc(mail.getBcc().trim());
            } else if (mail.getBccArray() != null) {
                helper.setBcc(mail.getBccArray());
            } else if (mail.getReplyTo() != null) {
                helper.setReplyTo(mail.getReplyTo().trim());
            }

            if (mail.getFileName() != null && (mail.getFile() != null || mail.getInputStreamSource() != null)) {
                if (mail.getFile() != null) {
                    helper.addAttachment(mail.getFileName(), mail.getFile());
                } else if (mail.getInputStreamSource() != null) {
                    helper.addAttachment(mail.getFileName(), mail.getInputStreamSource());
                } else if (mail.getInputStreamSource() != null && mail.getContentType() != null) {
                    helper.addAttachment(mail.getFileName(), mail.getInputStreamSource(), mail.getContentType());
                }
            }

            emailSender.send(message);
            response.setMessage("mail send to : " + mail.getTo());
            response.setStatus(Boolean.TRUE);
        }
        catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }
        return response;

    }

}