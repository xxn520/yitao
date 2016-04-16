package com.yitao.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
public class Message extends BaseModel{

    @FormParam("title")
    private String title;
    @FormParam("detail")
    @Column(length = 1000)
    private String detail;
    @Enumerated
    private Type msgtype;
    @ManyToOne
    private User msgto;

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

    public Type getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Type msgtype) {
        this.msgtype = msgtype;
    }

    public User getMsgto() {
        return msgto;
    }

    public void setMsgto(User msgto) {
        this.msgto = msgto;
    }

    public static enum Type {
        SYSTEM_MSG, PRIVATE_MSG, COLLECT_PRODUCT_MSG;
    }

}
