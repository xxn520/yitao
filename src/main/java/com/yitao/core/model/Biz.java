package com.yitao.core.model;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.annotations.Longitude;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Entity
@Indexed
@Cacheable
public class Biz extends BaseModel{

    @FormParam("name")
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class))
    private String name;
    @FormParam("phone")
    private String phone;
    @FormParam("lng")
    @Longitude
    private double lng;// 经度
    @FormParam("lat")
    @Latitude
    private double lat;// 纬度
    @FormParam("address")
    @Field(analyzer = @org.hibernate.search.annotations.Analyzer(impl = SmartChineseAnalyzer.class))
    private String address;
    @FormParam("coverPhoto")
    private String coverPhoto;// 封面照片

    public Biz() {
    }

    public Biz(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
