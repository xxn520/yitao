package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.CategoryRepository;
import com.yitao.core.dao.ProductRepository;
import com.yitao.core.model.Category;
import com.yitao.core.model.Product;
import com.yitao.core.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Iterator;
import java.util.List;

/**
 * Created by m2mbob on 16/4/28.
 */
@Controller
@Path(Constants.API_PATH + "category")
public class CategoryController extends AbstractService{

    @Inject
    private CategoryRepository repository;

    @Inject
    private ProductRepository productRepository;

    @GET
    public List<Category> roots() {
        return this.repository.findByParentIsNull();
    }

    @GET
    @Path("/parent/{id:\\d+}")
    public List<Category> findByParent(@PathParam("id") Long id){
        return repository.findByParentId(id);
    }

    @GET
    @Path("{id:\\d+}")
    public Category findById(@PathParam("id") Long id){
        return repository.findOne(id);
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

    @GET
    @Path("/getSimilar/{productId:\\d+}")
    public List<Product> getSimilarItem(@PathParam("productId") Long productId) {
        Iterator it = this.productRepository.findOne(productId).getCategories().iterator();
        if (it.hasNext()) {
            return this.productRepository
                    .findByCategoryId(new PageRequest(0, 4), productId, ((Category) it.next()).getParent().getId())
                    .getContent();
        } else {
            return null;
        }

    }

}
