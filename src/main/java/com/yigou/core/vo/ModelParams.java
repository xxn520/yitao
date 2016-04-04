/**
 * 
 */
package com.yigou.core.vo;

import javax.ws.rs.BeanParam;

/**
 * @author iDay
 *
 */
public class ModelParams<T> implements IModelParams<T> {

	@BeanParam
	protected T model;

	/**
	 * 
	 */
	public ModelParams() {
		super();
	}

	/**
	 * @param model
	 */
	public ModelParams(T model) {
		this.model = model;
	}

	/**
	 * @return the model
	 */
	public T getModel() {
		return model;
	}

}
