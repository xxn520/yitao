package com.yitao.core.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author iDay
 *
 */
public class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    /**
     *
     */
    public RestAuthenticationEntryPoint() {
        super();
        this.setRealmName("Realm");
    }

    public RestAuthenticationEntryPoint(String realmName) {
        super();
        this.setRealmName(realmName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.www.
     * BasicAuthenticationEntryPoint
     * #commence(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=\""
                + getRealmName() + "\"");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON);
        objectMapper.writeValue(response.getOutputStream(), new Error(String.valueOf(200000 + HttpServletResponse.SC_UNAUTHORIZED), authException.getLocalizedMessage()));
    }
}

