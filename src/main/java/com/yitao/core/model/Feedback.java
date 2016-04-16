package com.yitao.core.model;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
public class Feedback extends BaseModel{

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @FormParam("detail")
    private String detail;
    @FormParam("contact")
    private String contact;
    @FormParam("handled")
    @DefaultValue("false")
    private boolean handled;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }
}
