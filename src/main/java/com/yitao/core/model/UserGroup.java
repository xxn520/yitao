/**
 * 
 */
package com.yitao.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author iDay
 *
 */
@Entity
@Cacheable
public class UserGroup extends BaseModel {

	@FormParam("name")
	@Column(unique = true)
	private String name;
	@ElementCollection(fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<String> authorities;

	/**
	 * 
	 */
	public UserGroup() {
		super();
	}

	/**
	 * @param id
	 */
	public UserGroup(Long id) {
		super(id);
	}

	/**
	 * @param name
	 * @param authorities
	 */
	@SuppressWarnings("serial")
	public UserGroup(String name, String...authorities) {
		this.name = name;
		this.authorities = new HashSet<String>(){{
			addAll(Arrays.asList(authorities));
		}};
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the authorities
	 */
	public Set<String> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

}
