package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.BizRepository;
import com.yitao.core.model.Biz;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.BizParams;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Path;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "biz")
@RolesAllowed("ROLE_ADMIN")
public class BizAdminController extends AbstractCrudController<BizRepository, BizParams, Biz, Long>{
}
