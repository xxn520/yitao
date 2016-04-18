package com.yitao.core.vo;

import com.yitao.core.model.StudentAuth;

import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/18.
 */
public class StudentAuthParams extends ModelParams<StudentAuth>{

    @FormParam("status")
    private int status;

    @Override
    public StudentAuth getModel() {
        this.model.setStatus(StudentAuth.Status.values()[status]);
        return super.getModel();
    }
}
