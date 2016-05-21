package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.MessageRepository;
import com.yitao.core.model.Message;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by m2mbob on 16/5/21.
 */
@Controller
@Path(Constants.API_PATH + "message")
public class MeassageController extends AbstractService{

    @Inject
    private MessageRepository repository;

    @GET
    public Page<Message> find(@BeanParam PageParams pageParams, @QueryParam("uid") Long uid){
        return repository.findByMsgtoId(uid, pageParams);
    }

}
