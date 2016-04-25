package com.yitao.core.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author iDay
 */
public class RestAccessDeniedHandler extends AccessDeniedHandlerImpl {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.web.access.AccessDeniedHandlerImpl#handle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON);
        objectMapper.writeValue(response.getOutputStream(), new Error(String.valueOf(200000 + HttpServletResponse.SC_FORBIDDEN), accessDeniedException.getLocalizedMessage()));
    }

}
