package com.incloud.hcp.service._framework.email;

import com.incloud.hcp.service._framework.email.bean.EMailResponse;
import com.incloud.hcp.service._framework.email.bean.EmailParams;

import java.util.Map;

public interface EmailService {

    EMailResponse sendMessage(EmailParams mail, Map<String, Object> model);
}
