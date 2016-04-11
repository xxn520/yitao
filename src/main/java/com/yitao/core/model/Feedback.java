package com.yitao.core.model;

import javax.persistence.*;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
@Cacheable
public class Feedback extends BaseModel{

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @FormParam("content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
