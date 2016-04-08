/**
 * 
 */
package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.config.AppProperties;
import com.yitao.core.dao.UserGroupRepository;
import com.yitao.core.model.UserGroup;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.UserGroupParams;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "user-group")
@RolesAllowed("ROLE_ADMIN")
public class UserGroupAdminController extends AbstractCrudController<UserGroupRepository, UserGroupParams, UserGroup, Long> {

    @Inject
    private AppProperties properties;

    @GET
    @Path("authorities")
    public List<String> authorities() {
        return properties.getAuthorities();
    }

}
