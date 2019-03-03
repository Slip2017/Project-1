package com.view.web;

import com.database.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author  Shynkarenko Eduard
 *
 */
@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    private String name;
    private String email;
    private String password;
    private String phone;
    private UIComponent component1;
    private UIComponent component2;
    private boolean flag;

    public RegisterBean() {

    }

    public UIComponent getComponent1() {
        return component1;
    }

    public UIComponent getComponent2() {
        return component2;
    }

    public void setComponent1(UIComponent component1) {
        this.component1 = component1;
    }

    public void setComponent2(UIComponent component2) {
        this.component2 = component2;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = getHash(password);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {

        return flag;
    }

    public void register() {

        if (userService.containsUserByMail(email)) {
            FacesContext.getCurrentInstance().addMessage("registerform:email1", new FacesMessage(FacesMessage.SEVERITY_WARN, "Пользователь с таким адресом эл. почты уже существует!", null));
            return;
        }

        if (userService.containsUserByPhone(phone)) {
            FacesContext.getCurrentInstance().addMessage("registerform:phone", new FacesMessage(FacesMessage.SEVERITY_WARN, "Пользователь с таким номером телефона уже существует!", null));
            return;
        }

        Long id = userService.addUser(name, email, password, phone);

        if (id != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Вы успешно зарегистрированы", null));
        }

        setEmail("");
        setName("");
        setPassword("");
        setPhone("");

        setFlag(true);
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

