package com.yitao.core.vo;

import com.yitao.core.model.Category;

import javax.ws.rs.FormParam;

/**
 * Created by m2mbob on 16/4/16.
 */
public class CategoryParams extends ModelParams<Category>{

    @FormParam("parent_id")
    private Long parent_id;

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public Category getModel() {
        if(parent_id!=null){
            this.model.setParent(new Category(parent_id));
        }
        return super.getModel();
    }
}
