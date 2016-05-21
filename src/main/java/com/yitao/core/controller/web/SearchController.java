package com.yitao.core.controller.web;

import com.yitao.core.Constants;
import com.yitao.core.model.Product;
import com.yitao.core.service.AbstractService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;

/**
 * Created by m2mbob on 16/5/20.
 */
@Controller
@Path(Constants.API_PATH + "search")
public class SearchController extends AbstractService{

    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("/{keyword}")
    public Page<Product> Search(@PathParam("keyword") String content,
                                @QueryParam("page") @DefaultValue("0") int page) {
        FullTextEntityManager fullTextEntityManager = Search
                .getFullTextEntityManager(em);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();
        FullTextQuery query = fullTextEntityManager.createFullTextQuery(
                qb.keyword().onFields("name", "introduce")
                        .matching(content).createQuery(), Product.class);
        PageRequest pageRequest = new PageRequest(page, 10);
        query.setMaxResults(pageRequest.getPageSize());
        query.setFirstResult(pageRequest.getOffset());
        return new PageImpl<Product>(query.getResultList(), pageRequest,
                query.getResultSize());
    }

}
