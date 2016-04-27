/**
 * 
 */
package com.yitao.core.vo;

import com.yitao.core.model.StudentAuth;
import com.yitao.core.model.User;
import com.yitao.core.model.UserGroup;

import javax.ws.rs.FormParam;

/**
 * @author iDay
 *
 */
public class UserParams extends ModelParams<User> {
	
	@FormParam("group")
	private Long groupId;
	@FormParam("studentAuth_id")
	private Long studentAuthId;

	/* (non-Javadoc)
	 * @see com.yunpeng.core.vo.ModelParams#getModel()
	 */
	@Override
	public User getModel() {
        if(groupId!=null) {
            this.model.setGroup(new UserGroup(groupId));
        }
		if(studentAuthId!=null){
			this.model.setStudentAuth(new StudentAuth(studentAuthId));
		}else{
			this.model.setStudentAuth(new StudentAuth());
		}
		return this.model;
	}

}
