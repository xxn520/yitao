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
public class Account {
	@Id
	@FormParam("username")
	private String username;
	@FormParam("password")
	private String password;
	@OneToOne
	@JoinColumn(updatable = false)
	private User user;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
