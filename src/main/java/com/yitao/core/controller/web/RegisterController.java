package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.AccountRepository;
import com.yitao.core.dao.UserGroupRepository;
import com.yitao.core.dao.UserRepository;
import com.yitao.core.model.Account;
import com.yitao.core.model.StudentAuth;
import com.yitao.core.model.User;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.AccountParams;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by m2mbob on 16/4/25.
 */
@Controller
@Path(Constants.API_PATH + "register")
public class RegisterController extends AbstractService{

    @Inject
    private AccountRepository repository;

    @Inject
    private UserGroupRepository userGroupRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @POST
    @Transactional
    public User register(@BeanParam AccountParams params){
        if (!repository.exists(params.getModel().getUsername())) {
            Account account = params.getModel();
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            User user = new User(account.getUsername());
            user.setGroup(userGroupRepository.findFirstByName("用户"));
            user.setStudentAuth(new StudentAuth(StudentAuth.Status.UNAUDITED));
            userRepository.save(user);
            account.setUser(user);
            repository.save(account);
            return account.getUser();
        }else{
            throw new WebApplicationException("这个账号已经存在", Response.Status.fromStatusCode(500));
        }
    }

}
