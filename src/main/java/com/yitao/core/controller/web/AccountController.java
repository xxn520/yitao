package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.AccountRepository;
import com.yitao.core.model.Account;
import com.yitao.core.model.User;
import com.yitao.core.security.SpringSecurityAuditorAware;
import com.yitao.core.service.AbstractService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * Created by m2mbob on 16/4/25.
 */
@Controller
@Path(Constants.API_PATH + "account")
public class AccountController extends AbstractService{

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
    public User modifyPassword(@FormParam("username")String username, @FormParam("password")String password){
        Account account = this.accountRepository.findOne(username);
        account.setPassword(passwordEncoder.encode(password));
        return this.accountRepository.save(account).getUser();
    }

}
