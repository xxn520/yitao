package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.CategoryRepository;
import com.yitao.core.model.Category;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.CategoryParams;
import com.yitao.core.vo.PageImpl;
import com.yitao.core.vo.PageParams;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "category")
@RolesAllowed("ROLE_ADMIN")
public class CategoryAdminController extends AbstractCrudController<CategoryRepository, CategoryParams, Category, Long>{

    @Override
    @GET
    public Page<Category> find(@BeanParam PageParams pageParams, @Context UriInfo uri) {
        Long parentId = NumberUtils.toLong(uri.getQueryParameters().getFirst("parent_id"));
        Page<Category> page = super.find(pageParams, uri);
        if (parentId > 0) {
            page = new PageImpl<>(page, pageParams, this.repository.findOne(parentId));
        }
        return page;
    }

    @Path("/parent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Page<Category> find(@BeanParam PageParams pageParams) {
        return this.repository.findByParentIsNull(pageParams);
    }
}
