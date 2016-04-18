package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.DiscountRepository;
import com.yitao.core.model.Discount;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.DiscountParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by m2mbob on 16/4/18.
 */
@Controller
@Path(Constants.ADMIN_PATH + "discount")
@RolesAllowed("ROLE_ADMIN")
public class DiscountAdminController extends AbstractService{

    @Inject
    private DiscountRepository discountRepository;


    @GET
    @Path("{id:\\d+}")
    public Discount findOne(@PathParam("id") Long id){
        Discount discount = this.discountRepository.findOne(id);
        if (discount == null) {
            throw new WebApplicationException();
        }
        return discount;
    }

    @POST
    @Transactional
    public Discount create(@BeanParam DiscountParams params) {
        return this.discountRepository.save(params.getModel());
    }

    @PUT
    @Path("{id:\\d+}")
    @Transactional
    public Discount update(@BeanParam DiscountParams params) {
        return this.discountRepository.save(params.getModel());
    }

}
