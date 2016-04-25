package com.yitao.core.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author iDay
 *
 */
public class Rest403ForbiddenEntryPoint extends Http403ForbiddenEntryPoint {

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.Http403ForbiddenEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON);
        objectMapper.writeValue(response.getOutputStream(), new Error(String.valueOf(200000 + HttpServletResponse.SC_FORBIDDEN), authException.getLocalizedMessage()));
    }

}

