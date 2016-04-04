/**
 * 
 */
package com.yigou.core.security;

import com.yigou.core.model.User;
import com.yigou.core.vo.LoginUser;
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
	
	public String getIp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication == null || !authentication.isAuthenticated()) {
	      return null;
	    }
	    
	    String details = authentication.getDetails().toString();
	    
	    return details.substring(details.indexOf("RemoteIpAddress: ")+"RemoteIpAddress: ".length(), details.indexOf(";"));
	}

}
