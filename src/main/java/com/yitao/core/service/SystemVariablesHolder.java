/**
 * 
 */
package com.yitao.core.service;

import com.yitao.core.config.AppProperties;
import com.yitao.core.dao.SystemVariableRepository;
import com.yitao.core.model.SystemVariable;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author iDay
 * 系统参数缓存器，系统载入时自动加载所有系统参数，在客户端请求时，系统参数将存入模板渲染引擎中
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
@Aspect
public class SystemVariablesHolder {

	@Inject
	private SystemVariableRepository systemVariableRepository;
	@Inject
	private AppProperties app;
	private Map<String, String> systemVariablesMap;

	@PostConstruct
	public void post() {
		List<SystemVariable> systemVariables;
		systemVariables = this.systemVariableRepository.findAll();
		if (systemVariables.isEmpty()) {
			for (Entry<String, String> entry : app.getProperties().entrySet()) {
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				SystemVariable variable = new SystemVariable(key, value);
				systemVariables.add(variable);
			}
			systemVariablesMap = app.getProperties();
		} else {
			systemVariablesMap = new HashMap<>();
			for (SystemVariable systemVariable : systemVariables) {
				systemVariablesMap.put(systemVariable.getName(), systemVariable.getValue());
			}
		}
	}

	/**
	 * @return the systemVariablesMap
	 */
	public Map<String, String> getSystemVariablesMap() {
		return systemVariablesMap;
	}

	/**
	 * @param systemVariablesMap
	 *            the systemVariablesMap to set
	 */
	public void setSystemVariablesMap(Map<String, String> systemVariablesMap) {
		this.systemVariablesMap = systemVariablesMap;
	}

	/**
	 * @param key
	 * @return
	 * @see Map#get(Object)
	 */
	public String get(String key) {
		return systemVariablesMap == null ? null : systemVariablesMap.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		return systemVariablesMap.put(key, value);
	}
	
	@After("execution(* *..SystemVariableRepository.save(..)) || execution(* *..SystemVariableRepository.delete*(..))")
	public void afterSystemVariablesChanged() {
		this.post();
	}

}
