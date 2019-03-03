package com.view.web;

import com.database.service.UserService;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean
@ViewScoped
public class AuthBean implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;
    private boolean flag;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = getHash(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public void authenticate() {
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println("authenticate()");
        if (userService.containsUserByMailAndPass(email, password)) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("email", email);
            context.execute("PF('dlg').hide()");

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Неверный пароль или адрес эл. почты", null));
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "";
    }

    public boolean containsUser() {
        if (SessionUtils.getSession().getAttribute("email") != null) {
            return true;
        }
        return false;
    }

    private String getHash(String str) {
        MessageDigest md5;
        StringBuilder hexString = new StringBuilder();

        try {

            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());

            byte messageDigest[] = md5.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }

        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }

        return hexString.toString();
    }

}
