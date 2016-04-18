package com.yitao.core.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/8.
 */
@Entity
public class StudentAuth extends BaseModel{

    @FormParam("name")
    private String name;
    @FormParam("sid")
    private String sid;
    @Enumerated
    private Status status;

    public StudentAuth() {
    }

    public StudentAuth(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {
        UNAUDITED, AUDIT_FAIL, AUDIT_SUCCESS;
    }

}
