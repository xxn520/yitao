/**
 * 
 */
package com.yigou.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author iDay
 *
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public abstract class AbstractService {

	public <T> Specification<T> specification(UriInfo uri) {
		return new QuerySpecification<T>(uri.getQueryParameters());
	}

	private static class QuerySpecification<T> implements Specification<T> {
		private MultivaluedMap<String, String> map;
		private Predicate predicate;

		/**
		 * @param map
		 */
		public QuerySpecification(MultivaluedMap<String, String> map) {
			this.map = map;
		}


		@SuppressWarnings("unchecked")
		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			if (this.predicate != null) {	// 同样的查询条件只做一次拼装
				return this.predicate;
			}
			List<Predicate> list = new ArrayList<Predicate>();
			for (Map.Entry<String, List<String>> entry : this.map.entrySet()) {
				String value = entry.getValue().get(0).trim();
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				Path<? extends Object> path;
				try {
					if (entry.getKey().contains("_")) {	//子对象关联查询，通过'_'来连接子对象
						String[] names = entry.getKey().split("_");
						path = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							path = path.get(names[i]);
						}
					} else {
						path = root.get(entry.getKey());
					}
				} catch (Exception e) { // 过滤掉无效的查询参数
					continue;
				}
				if (path.getJavaType().getName().equals("java.lang.String")) {
					if (StringUtils.contains(value, "*")) {
						list.add(cb.like((Path<String>) path, StringUtils.replace(value, "*", "%")));
					} else {
						list.add(cb.equal((Path<String>) path, value));
					}
				} else if (path.getJavaType().getName().equals("boolean")) {
					list.add(cb.equal(path, Boolean.valueOf(value)));
				} else {
					try {
						// 利用反射获取属性对象实例
						Method method = path.getJavaType().getMethod("valueOf", String.class);
						list.add(cb.equal(path, method.invoke(null, value)));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e) {
						// TODO 抛出对象不存在valueOf方法的异常
						e.printStackTrace();
					}
				}
			}
			if (!list.isEmpty()) {
				this.predicate = cb.and(list.toArray(new Predicate[list.size()]));
				return this.predicate;
			}
			this.predicate = cb.conjunction();
			return this.predicate;
		}
	}

}
