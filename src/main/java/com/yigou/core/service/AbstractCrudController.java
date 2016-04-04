/**
 * 
 */
package com.yigou.core.service;

import com.yigou.core.dao.HibernateBasedRepository;
import com.yigou.core.vo.IModelParams;
import com.yigou.core.vo.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;

/**
 * @author iDay
 *
 */
public abstract class AbstractCrudController<DAO extends HibernateBasedRepository<T, ID>, M extends IModelParams<T>, T, ID extends Serializable>
		extends AbstractService {
	
	@Inject
	protected DAO repository;

	@GET
	public Page<T> find(@BeanParam PageParams pageParams, @Context UriInfo uri) {
		if (!uri.getQueryParameters().isEmpty()) {
			return this.repository.findAll(this.specification(uri), pageParams);
		} else {
			return this.repository.findAll(pageParams);
		}
	}

	@GET
	@Path("{id:\\d+}")
	public T findOne(@PathParam("id") ID id) {
		T t = this.repository.findOne(id);
		if (t == null) {
			throw new WebApplicationException();
		}
		return t;
	}

	@POST
	@Transactional
	public T create(@BeanParam M params) {
		return this.repository.save(params.getModel());
	}

	@PUT
	@Path("{id:\\d+}")
	@Transactional
	public T update(@BeanParam M params) {
		return this.repository.save(params.getModel());
	}

	@DELETE
	@Path("{id:\\d+}")
	@Transactional
	public ID destroy(@PathParam("id") ID id) {
		// DELETE操作默认不做处理
		return id;
	}

	@GET
	@Path("edit/{id:\\d+}")
	public T edit(@PathParam("id") ID id) {
		return this.repository.findOne(id);
	}

}
