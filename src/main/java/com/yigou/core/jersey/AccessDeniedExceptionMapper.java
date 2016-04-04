/**
 * 
 */
package com.yigou.core.jersey;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.security.access.AccessDeniedException;

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
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

	@Override
	public Response toResponse(AccessDeniedException exception) {
		Map<String, Object> map = new HashMap<>();
		return Response.status(Status.FORBIDDEN).entity(new Viewable("/403", map)).build();
	}

}
