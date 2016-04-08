/**
 * 
 */
package com.yitao.core.model;

import javax.persistence.*;
import javax.ws.rs.FormParam;

/**
 * @author iDay
 *
 */
@Entity
@Cacheable
public class SystemVariable {

	@Id
	@FormParam("name")
	private String name;
	@FormParam("value")
	private String value;

	/**
	 * 
	 */
	public SystemVariable() {
		super();
	}

	/**
	 * @param key
	 * @param value
	 */
	public SystemVariable(String key, String value) {
		this.name = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setName(String key) {
		this.name = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
