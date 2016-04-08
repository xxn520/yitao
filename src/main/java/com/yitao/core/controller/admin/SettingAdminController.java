/**
 * 
 */
package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.SystemVariableRepository;
import com.yitao.core.model.SystemVariable;
import com.yitao.core.service.AbstractService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iDay
 *
 */
@Controller
@Path(Constants.ADMIN_PATH + "setting")
@RolesAllowed("ROLE_ADMIN")
public class SettingAdminController extends AbstractService {

	@Inject
	private SystemVariableRepository variableRepository;
	
	@GET
	public List<SystemVariable> find() {
		return this.variableRepository.findAll();
	}
	
	@POST
	@Transactional
	public List<SystemVariable> create(MultivaluedMap<String, String> params) {
		List<SystemVariable> list = new ArrayList<>();
		for (String key : params.keySet()) {
			list.add(new SystemVariable(key, params.getFirst(key)));
		}
		this.variableRepository.save(list);
		return list;
	}
}
