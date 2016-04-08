/**
 * 
 */
package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.AccountRepository;
import com.yitao.core.model.Account;
import com.yitao.core.security.SpringSecurityAuditorAware;
import com.yitao.core.service.AbstractService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "reset")
@RolesAllowed("ROLE_ADMIN")
public class ResetAdminController extends AbstractService {

	@Inject
	private SpringSecurityAuditorAware auditorAware;
	@Inject
	private AccountRepository accountRepository;
	@Inject
	private PasswordEncoder passwordEncoder;

	@GET
	public Account findOne() {
		Account account = this.accountRepository.findByUser(auditorAware.getCurrentAuditor());
		return account;
	}

	@POST
	@Transactional
	public Account update(@FormParam("oldPassword") String oldPassword, @FormParam("password") String password,
			@Context HttpServletRequest request) {
		Account account = this.accountRepository.findByUser(auditorAware.getCurrentAuditor());
		if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
			request.setAttribute(Constants.ERROR_MESSAGE_KEY, "旧密码不正确哟。");
			return account;
		}
		account.setPassword(this.passwordEncoder.encode(password));
		this.accountRepository.save(account);
		return account;
	}

}
