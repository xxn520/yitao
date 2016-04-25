package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.ProductRepsository;
import com.yitao.core.model.Product;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by m2mbob on 16/4/21.
 */
@Controller
@Path(Constants.API_PATH + "product")
public class ProductController extends AbstractService{

    @Inject
    private ProductRepsository productRepsository;

    @GET
    @Path("/recommend")
    public Page<Product> findRecommond(){
        return this.productRepsository.findAll(new PageRequest(0, 5));
    }

    @GET
    @Path("/health")
    public Page<Product> findHealth(@BeanParam PageParams pageParams, @Context UriInfo uri){
        return this.productRepsository.findAll(this.specification(uri), pageParams);
    }

}
