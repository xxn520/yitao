package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.CategoryRepository;
import com.yitao.core.model.Category;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.CategoryParams;
import com.yitao.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "category")
@RolesAllowed("ROLE_ADMIN")
public class CategoryAdminController extends AbstractCrudController<CategoryRepository, CategoryParams, Category, Long>{

    @Path("/parent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Page<Category> find(@BeanParam PageParams pageParams) {
        return this.repository.findByParentIsNull(pageParams);
    }
}
