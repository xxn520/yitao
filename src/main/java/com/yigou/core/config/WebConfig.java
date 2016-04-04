/**
 * 
 */
package com.yigou.core.config;

import com.yigou.core.jersey.*;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.internal.ErrorTemplateExceptionMapper;
import org.glassfish.jersey.server.mvc.internal.MvcBinder;

import java.util.Map;

/**
 * @author iDay
 *
 */
public class WebConfig extends ResourceConfig {
	
	/**
	 * 
	 */
	public WebConfig(Map<String, String> properties) {
		super();
		this.packages("com.yigou")
			.setProperties(properties)
			.register(HttpMethodOverrideFilter.class)
			.register(CORSResponseFilter.class)
			.register(MvcFilter.class)
			.register(MultiPartFeature.class)
			.register(FreemarkerMvcFeature.class)
			.register(WebApplicationExceptionMapper.class)
			.register(AccessDeniedExceptionMapper.class)
			.register(CommonExceptionMapper.class)
			.register(HibernateJacksonContextResolver.class)
			.register(ErrorTemplateExceptionMapper.class)
            .register(new MvcBinder());
	}

}
