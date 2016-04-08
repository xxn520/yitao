/**
 * 
 */
package com.yitao.core.security;

import com.yitao.core.model.User;
import com.yitao.core.vo.LoginUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author iDay
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Override
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	      return null;
	    }
	    
	    if (authentication.getPrincipal() instanceof LoginUser) {
	    	return ((LoginUser) authentication.getPrincipal()).getUser();
	    }
	    return null;
	}

}
