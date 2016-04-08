/**
 * 
 */
package com.yitao.core.jersey;

import com.yitao.core.Constants;
import com.yitao.core.config.WebConfig;
import com.yitao.core.security.SpringSecurityAuditorAware;
import com.yitao.core.service.SystemVariablesHolder;
import com.yitao.core.util.YitaoUtil;
import com.yitao.core.vo.Request;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iDay
 *
 */
@Priority(Priorities.HEADER_DECORATOR)
public class MvcFilter implements ContainerResponseFilter {
	@Context
	private WebConfig config;
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	private ApplicationContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.
	 * container.ContainerRequestContext,
	 * javax.ws.rs.container.ContainerResponseContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void filter(final ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		String templatePath = (String) config.getProperty(FreemarkerMvcFeature.TEMPLATE_BASE_PATH);
		context = WebApplicationContextUtils.findWebApplicationContext(this.request.getServletContext());
		MediaType mediaType = responseContext.getMediaType();
		if (mediaType != null && !MediaType.TEXT_HTML_TYPE.equals(mediaType)) {
			return;
		}

		final Object entity = responseContext.getEntity();
		Map<String, Object> newEntity;
		if (entity == null || responseContext.getStatusInfo() == Status.NOT_FOUND) {
			// 请求的内容不存在，直接访问页面, 如果有tempate参数，直接跳转到tempate指向的模板
			String path = YitaoUtil.mvcPath(this.request.getServletPath());
			if (StringUtils.equals(path, Constants.ROOT_PATH)) {
				response.sendRedirect("/index.html");
				return;
			} else if (StringUtils.equals(path+"/", Constants.ADMIN_PATH)) {
				response.sendRedirect("/admin/index.html");
				return;
			}
			String realPath = this.request.getServletContext().getRealPath(templatePath + path + ".ftl");

			if (entity != null && (realPath == null || !new File(realPath).exists())) {
				newEntity = (Map<String, Object>) ((Viewable) entity).getModel();
			} else {
				newEntity = new HashMap<>();
				responseContext.setEntity(new Viewable(path, newEntity));
				responseContext.setStatusInfo(Status.OK);
			}
		} else if (responseContext.getStatusInfo() == Status.OK) {
			if (!(entity instanceof Map)) {
				newEntity = new HashMap<String, Object>();
				newEntity.put(Constants.MVC_FILTER_MODEL_KEY, entity);
				responseContext.setEntity(newEntity);
			} else {
				newEntity = (Map<String, Object>) entity;
			}
		} else {
			// 请求出错
			newEntity = (Map<String, Object>) ((Viewable) entity).getModel();
		}
		setCommonVariables(newEntity);// 设置常用参数

		// 如果非GET操作，跳转到GET请求
		if (responseContext.getStatusInfo() == Status.OK
				&& (HttpMethod.POST.equals(requestContext.getMethod().toUpperCase())
						|| HttpMethod.PUT.equals(requestContext.getMethod().toUpperCase())
						|| HttpMethod.DELETE.equals(requestContext.getMethod().toUpperCase()))
				&& request.getAttribute(Constants.ERROR_MESSAGE_KEY) == null) {
			String url = request.getParameter(Constants.REQUEST_PARAM_REDIRECT_KEY);
			if (StringUtils.isEmpty(url)) {	// 通过request attribute设置重定向参数
				url = (String) request.getAttribute(Constants.REQUEST_PARAM_REDIRECT_KEY);
			}
			if (StringUtils.isEmpty(url)) {
				String quertString = request.getQueryString();
				url = StringUtils.isEmpty(quertString) ? request.getRequestURI()
						: request.getRequestURI() + "?" + quertString;
			}
			response.sendRedirect(url);
			return;
		}
	}

	private void setCommonVariables(Map<String, Object> newEntity) {
		Request req = new Request(request);
		newEntity.put(Constants.MVC_FILTER_REQUEST_KEY, req);
		newEntity.put(Constants.MVC_FILTER_CURRENT_USER_KEY,
				context.getBean(SpringSecurityAuditorAware.class).getCurrentAuditor());
		newEntity.put(Constants.MVC_FILTER_APP_KEY,
				context.getBean(SystemVariablesHolder.class).getSystemVariablesMap());
		String path = request.getRequestURI().toString();
		newEntity.put(Constants.MVC_FILTER_LOCATION, path);
	}

}
