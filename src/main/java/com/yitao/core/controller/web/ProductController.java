package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.ProductRepository;
import com.yitao.core.dao.UserRepository;
import com.yitao.core.model.Product;
import com.yitao.core.model.User;
import com.yitao.core.service.AbstractService;
import com.yitao.core.service.UserService;
import com.yitao.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Set;

/**
 * Created by m2mbob on 16/4/21.
 */
@Controller
@Path(Constants.API_PATH + "product")
public class ProductController extends AbstractService{

    @Inject
    private ProductRepository productRepsository;

    @Inject
    private UserService userService;

    @Inject
    private UserRepository userRepository;

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

    @GET
    public Page<Product> findByCategory(@QueryParam("cid") Long cid, @BeanParam PageParams pageParams){
        return productRepsository.findByCategoryId(pageParams, cid);
    }

    @GET
    @Path("{id:\\d+}")
    public Product findOne(@PathParam("id") Long id){
        return productRepsository.findOne(id);
    }

    @GET
    @Path("/star")
    public boolean isStar(@QueryParam("productId") Long pid,
                           @QueryParam("userId") Long uid){
        Set<Product> products =  userService.findOne(uid).getProducts();
        for (Product product : products){
            if(product.getId()==pid){
                return true;
            }
        }
        return false;
    }

    @POST
    @Path("/star")
    @Transactional
    public boolean star(@FormParam("productId") Long pid,
                     @FormParam("userId") Long uid){
        User user = userRepository.findOne(uid);
        Set<Product> products =  user.getProducts();
        Product product = productRepsository.findOne(pid);
        products.add(product);
        user.setProducts(products);
        userRepository.save(user);
        return true;
    }

    @POST
    @Path("/cancelStar")
    @Transactional
    public boolean cancelStar(@FormParam("productId") Long pid,
                     @FormParam("userId") Long uid){
        User user = userRepository.findOne(uid);
        Set<Product> products =  user.getProducts();
        Product product = productRepsository.findOne(pid);
        products.remove(product);
        user.setProducts(products);
        userRepository.save(user);
        return true;
    }

    @GET
    @Path("/stars")
    public Set<Product> findStars(@QueryParam("uid") Long uid){
        return userService.findOne(uid).getProducts();
    }

}
