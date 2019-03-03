package com.view.web;

import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean(name="loginRegister")
@SessionScoped
public class LoginRegisterView implements Serializable {

     private String page;
        public void open() {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("resizable", false);
            options.put("draggable", true);
            options.put("modal", true);
            options.put("contentWidth", 1500);
            options.put("contentHeight", 600);

            RequestContext.getCurrentInstance().openDialog("/loginRegister", options, null);
        }



    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {

        return page;
    }
}
