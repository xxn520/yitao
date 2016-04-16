package com.yitao.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * Created by m2mbob on 16/4/8.
 */
@Entity
@Cacheable
@Indexed
public class Category extends BaseModel{

    @FormParam("name")
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class) )
    private String name;
    @FormParam("coverPhoto")
    private String coverPhoto;
    @ManyToOne
    @JsonBackReference
    private Category parent;
    @OneToMany(mappedBy = "parent")
    @OrderBy("createdDate desc")
    @JsonManagedReference
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Category> children;

    public Category() {
        super();
    }

    public Category(String name, String coverPhoto) {
        this.name = name;
        this.coverPhoto = coverPhoto;
    }

    public Category(Long id) {
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
