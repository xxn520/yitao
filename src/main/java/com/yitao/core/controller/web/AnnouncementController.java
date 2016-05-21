package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.AnnouncementRepository;
import com.yitao.core.model.Announcement;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.AnnouncementParams;
import com.yitao.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by m2mbob on 16/5/21.
 */
@Controller
@Path(Constants.API_PATH + "announcement")
public class AnnouncementController extends AbstractService{

    @Inject
    private AnnouncementRepository repository;

    @GET
    public Page<Announcement> find(@BeanParam PageParams pageParams){
        return repository.findAll(pageParams);
    }

    @POST
    @Transactional
    public Announcement create(@BeanParam AnnouncementParams params){
        return repository.save(params.getModel());
    }

}
