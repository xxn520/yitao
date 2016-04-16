package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.BrandRepository;
import com.yitao.core.model.Brand;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.BrandParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by m2mbob on 16/4/16.
 */
@Controller
@Path(Constants.ADMIN_PATH + "brand")
@RolesAllowed("ROLE_ADMIN")
public class BrandController extends AbstractCrudController<BrandRepository, BrandParams, Brand, Long>{

    /* (non-Javadoc)
	 * @see com.yunpeng.core.service.AbstractCrudController#destroy(java.io.Serializable)
	 */
    @Override
    @DELETE
    @Path("{id:\\d+}")
    @Transactional
    public Long destroy(@PathParam("id") Long id) {
        this.repository.delete(id);;
        return 1L;
    }

}
