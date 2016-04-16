package com.yitao.core.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
@Cacheable
public class Discount extends BaseModel{

    @FormParam("discount")
    @Column(scale=2)
    private BigDecimal discount;//折扣后价格
    @FormParam("startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;//开始时间
    @FormParam("endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;//结束时间
    private boolean flag;//是否过期
    @FormParam("introduction")
    @Column(length=1000)
    private String introduction;//折扣描述
    @OneToMany(mappedBy = "discount")
    @OrderBy("createdDate desc")
    @JsonManagedReference
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Product> products;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
