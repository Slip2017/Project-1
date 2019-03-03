package com.view.web;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 * @author  Shynkarenko Eduard
 *
 */

public class SessionUtils {
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
}
