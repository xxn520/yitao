package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.StudentAuthRepository;
import com.yitao.core.dao.UserRepository;
import com.yitao.core.model.StudentAuth;
import com.yitao.core.model.User;
import com.yitao.core.service.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by m2mbob on 16/4/27.
 */
@Controller
@Path(Constants.API_PATH + "user")
public class UserController extends AbstractService{

    @Inject
    private UserRepository repository;

    @Inject
    private StudentAuthRepository studentAuthRepository;

    @PUT
    @Path("{id:\\d+}")
    @Transactional
    public User update(@PathParam("id") Long id,
                       @FormParam("nickname") String nickname,
                       @FormParam("email") String email,
                       @FormParam("avatar") String avatar) {
        User user = repository.findOne(id);
        user.setEmail(email);
        user.setNickname(nickname);
        if(!StringUtils.isEmpty(avatar)){
            user.setAvatar(avatar);
        }
        return this.repository.save(user);
    }

    @PUT
    @Path("studentAuth/{id:\\d+}")
    @Transactional
    public StudentAuth auth(@PathParam("id") Long id,
                     @FormParam("sid") String sid,
                     @FormParam("name") String name){
        StudentAuth auth = studentAuthRepository.findOne(id);
        auth.setName(name);
        auth.setSid(sid);
        return studentAuthRepository.save(auth);
    }

}
