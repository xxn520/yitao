package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.AnnouncementRepository;
import com.yitao.core.model.Announcement;
import com.yitao.core.service.AbstractCrudController;
import com.yitao.core.vo.AnnouncementParams;
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
@Path(Constants.ADMIN_PATH + "announcement")
@RolesAllowed("ROLE_ADMIN")
public class AnnouncementAdminController extends AbstractCrudController<AnnouncementRepository, AnnouncementParams, Announcement, Long>{

    @Override
    @DELETE
    @Path("{id:\\d+}")
    @Transactional
    public Long destroy(@PathParam("id") Long id) {
        this.repository.delete(id);;
        return 1L;
    }

}
