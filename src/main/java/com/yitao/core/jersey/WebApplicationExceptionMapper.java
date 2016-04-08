/**
 * 
 */
package com.yitao.core.jersey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iDay
 * 
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	Log log = LogFactory.getLog(WebApplicationExceptionMapper.class);

	@Context
	HttpServletRequest request;

	@Override
	public Response toResponse(WebApplicationException exception) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", exception);
		switch (exception.getResponse().getStatus()) {
		case 404:
			return Response.status(exception.getResponse().getStatus()).entity(new Viewable("/404", map))
					.type(exception.getResponse().getMediaType()).build();
		default:
			log.error(exception);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Viewable("/500", map))
					.type(exception.getResponse().getMediaType()).build();
		}
	}
}
