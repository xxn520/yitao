package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.BizRepository;
import com.yitao.core.model.Biz;
import com.yitao.core.service.AbstractService;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by m2mbob on 16/4/28.
 */
@Controller
@Path(Constants.API_PATH + "biz")
public class BizController extends AbstractService{

    @Inject
    private BizRepository repository;

    @GET
    public List<Biz> find(){
        return repository.findAll();
    }

}
