/**
 * 
 */
package com.yigou.core.jersey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.mvc.Viewable;

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
public class CommonExceptionMapper implements ExceptionMapper<Exception> {
	Log log = LogFactory.getLog(this.getClass());

	@Override
	public Response toResponse(Exception exception) {
		log.error("error", exception);
		Map<String, Object> map = new HashMap<>();
		map.put(exception.getMessage(), exception);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Viewable("/500", map)).build();
	}
}
