/**
 * 
 */
package com.yitao.core.dao;

import com.yitao.core.model.Account;
import com.yitao.core.model.User;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * @author iDay
 *
 */
public interface AccountRepository extends HibernateBasedRepository<Account, String> {
	
	@QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
	Account findByUser(User user);
	
	@QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
	Account findByUserId(long uid);
	
}
