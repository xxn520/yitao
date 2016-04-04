/**
 * 
 */
package com.yigou.core.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * @author iDay
 *
 */
public class PageParams extends PageRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8508047167524737101L;
	
	/**
	 * @param page
	 * @param size
	 * @param direction
	 * @param properties
	 */
	public PageParams(int page, int size, Direction direction, String... properties) {
		super(page, size, direction, properties);
	}

	/**
	 * @param page
	 * @param size
	 * @param sort
	 */
	public PageParams(int page, int size, Sort sort) {
		super(page, size, sort);
	}

	/**
	 * @param page
	 * @param size
	 */
	public PageParams(@QueryParam("page") int page, @QueryParam("page-size") @DefaultValue("12") int size) {
		super(page, size, Direction.DESC, "id");
	}

}
