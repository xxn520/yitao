/**
 * 
 */
package com.yitao.core.service;

import com.yitao.core.dao.AccountRepository;
import com.yitao.core.model.Account;
import com.yitao.core.vo.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author iDay
 *
 */
@Service
public class AccountService extends AbstractService implements UserDetailsService {
	
	@Inject
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findOne(username);
		if (account == null) {
			throw new UsernameNotFoundException("username is not found");
		}
		return new LoginUser(account);
	}

}
