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
    @FormParam("sort")
    private int sort;  //排序

    public Brand() {
    }

    public Brand(Long id) {
        super(id);
    }

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
