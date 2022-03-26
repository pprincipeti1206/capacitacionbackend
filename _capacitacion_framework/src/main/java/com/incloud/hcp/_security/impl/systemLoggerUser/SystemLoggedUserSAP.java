package com.incloud.hcp._security.impl.systemLoggerUser;

import com.incloud.hcp._security.SystemLoggedUser;
import com.incloud.hcp._security.UserSession;
import com.incloud.hcp.exception.ServiceException;
import com.sap.security.um.user.UnsupportedUserAttributeException;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;


@Component("systemLoggedUser")
@Profile("!devlocal")
public class SystemLoggedUserSAP implements SystemLoggedUser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//    public User getUserSCP(HttpServletRequest request) {
//        String keyAttribute = "com.sap.security.auth.login.User." + request.getUserPrincipal().getName().toLowerCase();
//        return (User) request.getSession().getAttribute(keyAttribute);
//    }

    public UserSession getUserSession() throws ServiceException {
        try {

            InitialContext ctx = new InitialContext();
            UserProvider userProvider;
            userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");

            User u = userProvider.getCurrentUser();
            //logger.error("getUserSession user: " + u.toString());
            UserSession session = new UserSession();
            session.setRuc(this.getAttributeOfUser(u, "login_name"));
            session.setMail(this.getAttributeOfUser(u, "mail"));
            session.setDisplayName(this.getAttributeOfUser(u, "display_name"));
            session.setFirstName(this.getAttributeOfUser(u, "first_name"));
            session.setLastName(this.getAttributeOfUser(u, "last_name"));
            session.setId(this.getAttributeOfUser(u, "id"));
            logger.error("getUserSession session: " + session.toString());
            return session;
        } catch (Exception ex) {
            logger.error("Error al obtener el usuario de la sesión", ex);
            throw new ServiceException("Error al obtener el usuario de la sesión: " + ex.getMessage());
        }

    }

    public User getUserSCP(HttpServletRequest request) {
        String keyAttribute = "com.sap.security.auth.login.User." + request.getUserPrincipal().getName().toLowerCase();
        return (User) request.getSession().getAttribute(keyAttribute);
    }

    private String getAttributeOfUser(User user, String key) {
        try {
            if (user == null) {
                return null;
            }
            return user.getAttribute(key);
        } catch (UnsupportedUserAttributeException ex) {
            logger.error("Error al leer el atributo " + key + " de la sesión", ex);
        }
        return null;
    }
}
