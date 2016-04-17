package com.yitao.core.vo;

import com.yitao.core.model.Biz;
import com.yitao.core.model.Category;
import com.yitao.core.model.Discount;
import com.yitao.core.model.Product;

import javax.ws.rs.FormParam;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by m2mbob on 16/4/16.
 */
public class ProductParams extends ModelParams<Product>{

    @FormParam("categories")
    private Set<Long> categories;
    @FormParam("biz_id")
    private Long bizId;

    @Override
    public Product getModel() {
        this.model.setCategories(new HashSet<>());
        for (Long category : categories) {
            this.model.getCategories().add(new Category(category));
        }
        this.model.setBiz(new Biz(bizId));
        if(this.model.getId()==null){
            this.model.setDiscount(new Discount());
        }
        return this.model;
    }

}
