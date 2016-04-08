/**
 * 
 */
package com.yitao.core.jersey;

import com.yitao.core.config.WebConfig;
import com.yitao.core.util.YitaoUtil;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.mvc.internal.TemplateHelper;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.util.Map;

/**
 * @author iDay
 *
 */
@Priority(Priorities.ENTITY_CODER)
public class MvcTemplateMethodInterceptor implements WriterInterceptor {
	@Context
	private WebConfig config;

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.glassfish.jersey.server.mvc.internal.TemplateMethodInterceptor#
	 * aroundWriteTo(javax.ws.rs.ext.WriterInterceptorContext)
	 */
	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		final Template template = TemplateHelper.getTemplateAnnotation(context.getAnnotations());
		if (template == null && MediaType.TEXT_HTML_TYPE.equals(context.getMediaType())) {
			processNoTemplate(context);
		}
		context.proceed();
	}

	@SuppressWarnings("unchecked")
	protected void processNoTemplate(WriterInterceptorContext context) {
		String templatePath = (String) config.getProperty(FreemarkerMvcFeature.TEMPLATE_BASE_PATH);
		final Object entity = context.getEntity();
		if (entity instanceof Map) {
			String path = YitaoUtil.mvcPath(this.request.getServletPath());
			String realPath = this.request.getServletContext().getRealPath(templatePath + path + ".ftl");
			if (realPath == null) {
				return;
			}
			context.setType(Viewable.class);
			context.setEntity(new Viewable(path, entity));
		}
	}

}
