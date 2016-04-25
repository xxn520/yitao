package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.AccountRepository;
import com.yitao.core.model.Account;
import com.yitao.core.model.User;
import com.yitao.core.security.SpringSecurityAuditorAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by m2mbob on 16/4/25.
 */
@Controller
@Path(Constants.API_PATH + "account")
public class AccountController {

    @Inject
    private SpringSecurityAuditorAware auditorAware;
    @Inject
    private AccountRepository accountRepository;
    @Inject
    private PasswordEncoder passwordEncoder;

    @GET
    public User show() {
        return auditorAware.getCurrentAuditor();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User modifyPassword(@FormParam("username")String username,
                               @FormParam("password")String password){
        Account account = this.accountRepository.findOne(username);
        account.setPassword(passwordEncoder.encode(password));
        return this.accountRepository.save(account).getUser();
    }

}
