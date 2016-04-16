package com.yitao.core.model;

import javax.persistence.*;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
public class Announcement extends BaseModel{

    @FormParam("title")
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @FormParam("detail")
    private String detail;
    @FormParam("contact")
    private String contact;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
