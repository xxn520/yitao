/**
 * 
 */
package com.yigou.core.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author iDay
 *
 */
public interface Initializer {
	
	@Transactional
	void init();
	
}
