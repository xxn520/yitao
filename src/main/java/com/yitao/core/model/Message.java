package com.yitao.core.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
@Cacheable
public class Message {

    @FormParam("title")
    private String title;
    @FormParam("content")
    @Column(length = 1000)
    private String content;
    @Enumerated
    private Type type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static enum Type {
        SYSTEM_MSG, PRIVATE_MSG, COLLECT_PRODUCT_MSG;
    }

}
