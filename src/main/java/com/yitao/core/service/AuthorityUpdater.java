/**
 * 
 */
package com.yitao.core.service;

import com.yitao.core.config.AppProperties;
import com.yitao.core.dao.UserGroupRepository;
import com.yitao.core.model.UserGroup;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author iDay 默认权限升级
 */
@Service
public class AuthorityUpdater extends YitaoInitializer {
	@Inject
	private UserGroupRepository repository;
	@Inject
	private AppProperties appProperties;

	@Override
	@Transactional
	protected void doInit() {
		UserGroup ug = repository.findFirstByName("管理员");
		if (ug != null) {
			if (appProperties.getAuthorities() != null
					&& !CollectionUtils.isSubCollection(appProperties.getAuthorities(), ug.getAuthorities())) {
				ug.getAuthorities().addAll(appProperties.getAuthorities());
			}
			repository.save(ug);
		}
	}

}
