/**
 * 
 */
package com.yigou.core.controller.admin;

import com.yigou.core.Constants;
import com.yigou.core.dao.UserGroupRepository;
import com.yigou.core.model.UserGroup;
import com.yigou.core.service.AbstractCrudController;
import com.yigou.core.vo.UserGroupParams;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "user-group")
@RolesAllowed("ROLE_SUPERVISOR")
public class UserGroupAdminController extends AbstractCrudController<UserGroupRepository, UserGroupParams, UserGroup, Long> {
	
}
