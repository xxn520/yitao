package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.dao.BrandRepository;
import com.yitao.core.model.Brand;
import com.yitao.core.service.AbstractService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by m2mbob on 16/4/24.
 */
@Controller
@Path(Constants.API_PATH + "brand")
public class BrandController extends AbstractService{

    @Inject
    private BrandRepository repository;

    @GET
    public List<Brand> find(){
        return repository.findAll(new Sort(Sort.Direction.DESC, "sort"));
    }

}
