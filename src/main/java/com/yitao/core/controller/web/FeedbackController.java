package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.FeedbackRepository;
import com.yitao.core.model.Feedback;
import com.yitao.core.service.AbstractService;
import com.yitao.core.vo.FeedbackParams;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by m2mbob on 16/4/28.
 */
@Controller
@Path(Constants.API_PATH + "feedback")
public class FeedbackController extends AbstractService{

    @Inject
    private FeedbackRepository repository;

    @POST
    @Transactional
    public Feedback create(@BeanParam FeedbackParams params) {
        return this.repository.save(params.getModel());
    }

}
