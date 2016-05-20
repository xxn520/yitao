package com.yitao.core.model;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by m2mbob on 16/4/8.
 */
@Entity
@Indexed
public class Product extends BaseModel{

    @FormParam("name")
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class))
    private String name;
    @FormParam("introduce")
    @Column(length = 1000)
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class))
    private String introduce;                           // 商品介绍
    @FormParam("coverPhoto")
    private String coverPhoto;                          // 封面照片
    @FormParam("price")
    @Column(scale = 2)
    private BigDecimal price;
    @FormParam("health")
    private boolean heath;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> detailPhoto;                   // 详情照片
    @ManyToMany(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @IndexedEmbedded
    private Set<Category> categories;
    @ManyToOne
    private Biz biz;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Discount discount;
    @ManyToOne
    private Brand brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHeath() {
        return heath;
    }

    public void setHeath(boolean heath) {
        this.heath = heath;
    }

    public Set<String> getDetailPhoto() {
        return detailPhoto;
    }

    public void setDetailPhoto(Set<String> detailPhoto) {
        this.detailPhoto = detailPhoto;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Biz getBiz() {
        return biz;
    }

    public void setBiz(Biz biz) {
        this.biz = biz;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Brand getBrand() {
        
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
