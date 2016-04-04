/**
 * 
 */
package com.yigou.core.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author iDay
 *
 */
@Service
public abstract class YigouInitializer extends AbstractService implements Initializer {


	@Override
	@PostConstruct
	public void init() {
		doInit();
	}

	protected abstract void doInit();

}
