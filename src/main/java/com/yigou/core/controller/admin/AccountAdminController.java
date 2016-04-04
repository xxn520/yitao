/**
 * 
 */
package com.yigou.core.controller.admin;

import com.yigou.core.Constants;
import com.yigou.core.dao.AccountRepository;
import com.yigou.core.model.Account;
import com.yigou.core.model.User;
import com.yigou.core.service.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * @author iDay
 *
 */
@Path(Constants.ADMIN_PATH + "account")
@Controller
@RolesAllowed("ROLE_ADMIN")
public class AccountAdminController extends AbstractService {

	@Inject
	private AccountRepository repository;
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@GET
	public Account find(@QueryParam("uid") long uid) {
		return this.repository.findByUserId(uid);
	}
	
	@POST
	@Transactional
	public Account create(@BeanParam Account account, @FormParam("uid") long uid) {
		if (StringUtils.isNotEmpty(account.getPassword())) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
		}
		account.setUser(new User(uid));
		
		Account a = this.find(uid);
		if (a != null) {
			a.setUsername(account.getUsername());
			a.setPassword(account.getPassword());
		} else {
			a = account;
		}
		this.repository.save(a);
		return a;
	}
	
}
