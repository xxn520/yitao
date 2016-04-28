package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.CategoryRepository;
import com.yitao.core.model.Category;
import com.yitao.core.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by m2mbob on 16/4/28.
 */
@Controller
@Path(Constants.API_PATH + "category")
public class CategoryController extends AbstractService{

    @Inject
    private CategoryRepository repository;

    @GET
    public List<Category> roots() {
        return this.repository.findByParentIsNull();
    }

    @GET
    @Path("/tree")
    public List<Category> tree() {
        List<Category> roots = this.roots();
        for (Category category : roots) {
            Page<Category> children = this.repository.findByParentId(category.getId(), new PageRequest(0, 4));
            category.setChildren(children.getContent());
        }
        return roots;
    }

}
