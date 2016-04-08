/**
 * 
 */
package com.yitao.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.ws.rs.FormParam;

/**
 * @author iDay
 *
 */
@Entity
@Cacheable
public class User extends BaseModel {

	@FormParam("username")
	@Column(unique = true)
	private String username;
	@FormParam("realname")
	private String realname;
	@FormParam("nickname")
	private String nickname;
	@FormParam("phone")
	private String phone;
	@FormParam("email")
	private String email;
	@FormParam("avatar")
	private String avatar;
	@ManyToOne
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserGroup group;
	@FormParam("locked")
	private boolean locked;
	@FormParam("enabled")
	private boolean enabled;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 */
	public User(Long id) {
		super(id);
	}

	/**
	 * @param username
	 */
	public User(String username) {
		this.username = username;
	}

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
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname
	 *            the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the group
	 */
	public UserGroup getGroup() {
		return group;
	}

	/**
	 * @param userGroup
	 *            the group to set
	 */
	public void setGroup(UserGroup userGroup) {
		this.group = userGroup;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
