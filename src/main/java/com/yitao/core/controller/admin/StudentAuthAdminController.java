package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.StudentAuthRepository;
import com.yitao.core.model.StudentAuth;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.StudentAuthParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by m2mbob on 16/4/18.
 */
@Controller
@Path(Constants.ADMIN_PATH + "studentAuth")
@RolesAllowed("ROLE_ADMIN")
public class StudentAuthAdminController extends AbstractService{

    @Inject
    private StudentAuthRepository studentAuthRepository;

    @GET
    @Path("{id:\\d+}")
    public StudentAuth findOne(@PathParam("id") Long id){
        StudentAuth studentAuth = this.studentAuthRepository.findOne(id);
        if (studentAuth == null) {
            throw new WebApplicationException();
        }
        return studentAuth;
    }

    @PUT
    @Path("{id:\\d+}")
    @Transactional
    public StudentAuth update(@BeanParam StudentAuthParams params) {
        return this.studentAuthRepository.save(params.getModel());
    }

}
