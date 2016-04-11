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
    @FormParam("content")
    private String content;

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

}
