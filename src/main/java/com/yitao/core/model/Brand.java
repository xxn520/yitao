package com.yitao.core.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
@Cacheable
public class Brand extends BaseModel {

    @FormParam("name")
    private String name;
    @FormParam("coverPhoto")
    private String coverPhoto;
    @FormParam("order")
    private int order;  //排序

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
