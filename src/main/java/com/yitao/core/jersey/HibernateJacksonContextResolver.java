/**
 * 
 */
package com.yitao.core.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author iday
 * 
 */
@Provider
public class HibernateJacksonContextResolver implements ContextResolver<ObjectMapper> {
	private ObjectMapper objectMapper;

	public HibernateJacksonContextResolver() {
		super();
		Hibernate5Module hibernate5Module = new Hibernate5Module()
				.disable(Feature.FORCE_LAZY_LOADING)
				.disable(Feature.USE_TRANSIENT_ANNOTATION);
		this.objectMapper = new ObjectMapper()
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
				.setPropertyNamingStrategy(new LowerCaseWithUnderscoresStrategy())
				.registerModule(hibernate5Module);

	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return objectMapper;
	}

}
