package com.incloud.hcp._security;

import com.incloud.hcp.exception.ServiceException;
import com.sap.security.um.user.User;

import javax.servlet.http.HttpServletRequest;


public interface SystemLoggedUser {

    UserSession getUserSession() throws ServiceException;

    User getUserSCP(HttpServletRequest request);

}
