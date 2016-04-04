/**
 * 
 */
package com.yigou.core.vo;

import com.yigou.core.model.User;
import com.yigou.core.model.UserGroup;

import javax.ws.rs.FormParam;

/**
 * @author iDay
 *
 */
public class UserParams extends ModelParams<User> {
	
	@FormParam("group")
	private Long groupId;

	/* (non-Javadoc)
	 * @see com.yunpeng.core.vo.ModelParams#getModel()
	 */
	@Override
	public User getModel() {
		this.model.setGroup(new UserGroup(groupId));
		return this.model;
	}

}
