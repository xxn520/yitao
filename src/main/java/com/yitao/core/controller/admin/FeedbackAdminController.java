package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.FeedbackRepository;
import com.yitao.core.model.Feedback;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.FeedbackParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by m2mbob on 16/4/11.
 */
@Controller
@Path(Constants.ADMIN_PATH + "feedback")
@RolesAllowed("ROLE_ADMIN")
public class FeedbackAdminController extends AbstractCrudController<FeedbackRepository, FeedbackParams, Feedback, Long>{

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
