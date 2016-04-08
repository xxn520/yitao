/**
 * 
 */
package com.yitao.core.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author iDay
 *
 */
@Service
public abstract class YitaoInitializer extends AbstractService implements Initializer {


	@Override
	@PostConstruct
	public void init() {
		doInit();
	}

	protected abstract void doInit();

}
