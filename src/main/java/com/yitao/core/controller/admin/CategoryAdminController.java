package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.CategoryRepository;
import com.yitao.core.model.Category;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.CategoryParams;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "category")
@RolesAllowed("ROLE_ADMIN")
public class CategoryAdminController extends AbstractCrudController<CategoryRepository, CategoryParams, Category, Long>{

}
