package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.BizRepository;
import com.yitao.core.dao.ProductRepsository;
import com.yitao.core.model.Product;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.PageImpl;
import com.yitao.core.vo.PageParams;
import com.yitao.core.vo.ProductParams;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "product")
@RolesAllowed("ROLE_ADMIN")
public class ProductAdminController extends AbstractCrudController<ProductRepsository, ProductParams, Product, Long>{

    @Inject
    private BizRepository bizRepository;

    @Override
    @GET
    public Page<Product> find(@BeanParam PageParams pageParams, @Context UriInfo uri) {
        Long bizId = NumberUtils.toLong(uri.getQueryParameters().getFirst("biz_id"));
        Page<Product> page = super.find(pageParams, uri);
        if (bizId > 0) {
            page = new PageImpl<>(page, pageParams, this.bizRepository.findOne(bizId));
        }
        return page;
    }

    @Override
    @DELETE
    @Path("{id:\\d+}")
    @Transactional
    public Long destroy(@PathParam("id") Long id) {
        this.repository.delete(id);;
        return 1L;
    }
}
