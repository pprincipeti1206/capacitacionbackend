package com.incloud.hcp._security.impl.systemLoggerUser;

import com.incloud.hcp._security.SystemLoggedUser;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.exception.ServiceException;
import com.sap.security.um.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component("systemLoggedUser")
@Profile("devlocal")
public class SystemLoggedUserDevLocal implements SystemLoggedUser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${sm.portal.nameId}")
    private String nameIdScp;

    @Value("${sm.portal.dev}")
    private Boolean isDev;

    @Value("${sm.portal.nameDisplay}")
    private String nameDisplay;

    @Value("${sm.portal.email}")
    private String email;

    @Value("${sm.portal.firstName}")
    private String firstName;

    @Value("${sm.portal.lastName}")
    private String lastName;


    public UserSession getUserSession() throws ServiceException {
           UserSession session = new UserSession();
           session.setDisplayName(this.nameDisplay);
           session.setId(this.nameIdScp);
           session.setMail(this.email);
           session.setRuc(this.nameIdScp);
           session.setFirstName(firstName);
           session.setLastName(lastName);
           return session;

    }

    public User getUserSCP(HttpServletRequest request) {
        return null;
    }


}
