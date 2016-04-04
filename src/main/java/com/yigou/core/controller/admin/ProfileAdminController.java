/**
 * 
 */
package com.yigou.core.controller.admin;

import com.yigou.core.Constants;
import com.yigou.core.dao.UserRepository;
import com.yigou.core.model.User;
import com.yigou.core.security.SpringSecurityAuditorAware;
import com.yigou.core.service.AbstractService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "profile")
@RolesAllowed("ROLE_ADMIN")
public class ProfileAdminController extends AbstractService {
	@Inject
	private UserRepository userRepository;
	@Inject
	private SpringSecurityAuditorAware auditorAware;
	
	@GET
	public User findOne() {
		return auditorAware.getCurrentAuditor();
	}
	
	@POST
	@Transactional
	public User update(@BeanParam User user) {
		User u = this.auditorAware.getCurrentAuditor();
		u.setUsername(user.getUsername());
		u.setAvatar(user.getAvatar());
		this.userRepository.save(u);
		return u;
	}
	
}
