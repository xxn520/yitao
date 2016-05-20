package com.yitao.core.controller.admin;

import com.yitao.core.Constants;
import com.yitao.core.dao.*;
import com.yitao.core.service.AbstractService;
import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by m2mbob on 16/4/18.
 */
@Controller
@Path(Constants.ADMIN_PATH + "index")
@RolesAllowed("ROLE_ADMIN")
public class IndexAdminController extends AbstractService{

    @Inject
    private BizRepository bizRepository;
    @Inject
    private ProductRepository productRepsository;
    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private BrandRepository brandRepository;
    @Inject
    private FeedbackRepository feedbackRepository;

    @GET
    public Map<String, Long> list() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("bizs", this.bizRepository.count());
        counts.put("products", this.productRepsository.count());
        counts.put("categories", this.categoryRepository.count());
        counts.put("brands", this.brandRepository.count());
        counts.put("feedbacks", this.feedbackRepository.count());
        return counts;
    }

}
