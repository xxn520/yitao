/**
 * 
 */
package com.yigou.core.model;

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
	@Enumerated
	@Column(updatable = false)
	private SystemVariableType type;
	@Column(updatable = false)
	private String source;

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
	 * @param name
	 * @param value
	 * @param type
	 */
	public SystemVariable(String name, String value, SystemVariableType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	/**
	 * @param name
	 * @param value
	 * @param type
	 * @param source
	 */
	public SystemVariable(String name, String value, SystemVariableType type, String source) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.source = source;
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

	/**
	 * @return the type
	 */
	public SystemVariableType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(SystemVariableType type) {
		this.type = type;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	public static enum SystemVariableType {
		TEXT, RADIO, SELECT, TEXTAREA, CHECKBOX;
	}

}
