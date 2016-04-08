/**
 * 
 */
package com.yitao.core.config;

import com.yitao.core.jersey.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.internal.ErrorTemplateExceptionMapper;
import org.glassfish.jersey.server.mvc.internal.MvcBinder;

import javax.inject.Singleton;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.WriterInterceptor;
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
		this.packages("com.yitao")
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
            .register(new MvcBinder())
			.register(new CustomBinder());
	}

	private static class CustomBinder extends AbstractBinder {

		@Override
		protected void configure() {
			bind(MvcTemplateMethodInterceptor.class).to(WriterInterceptor.class).in(Singleton.class);
			bind(DateProvider.class).to(ParamConverterProvider.class).ranked(10);
		}

	}

}
