package com.incloud.hcp.service._framework.email.impl;

import com.incloud.hcp.service._framework.email.EmailService;
import com.incloud.hcp.service._framework.email.bean.EMailResponse;
import com.incloud.hcp.service._framework.email.bean.EmailParams;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConditionalOnProperty(name = "sinEmail.enabled")
@Component("emailService")
public class EmailFicticioServiceImpl implements EmailService {

    public EMailResponse sendMessage(EmailParams mail, Map<String, Object> model)  {
        EMailResponse response = new EMailResponse();
        response.setMessage("mail send to : SIN EMAIL" );
        response.setStatus(Boolean.TRUE);
        return response;
    }

}