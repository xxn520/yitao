/**
 * 
 */
package com.yigou.core.dao;

import com.yigou.core.model.UserGroup;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * @author iDay
 *
 */
public interface UserGroupRepository extends HibernateBasedRepository<UserGroup, Long> {

	@QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"), forCounting = true)
	UserGroup findFirstByName(String string);

}
