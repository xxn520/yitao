package com.yitao.core.model;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/8.
 */
@Entity
@Indexed
public class Category extends BaseModel{

    @FormParam("name")
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class) )
    private String name;
    @FormParam("cover")
    private String cover;

    public Category() {
        super();
    }

    public Category(String name, String cover) {
        this.name = name;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
