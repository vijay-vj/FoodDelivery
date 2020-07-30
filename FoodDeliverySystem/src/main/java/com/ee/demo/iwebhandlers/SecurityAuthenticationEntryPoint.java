package com.ee.demo.iwebhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
 
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

 	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

         ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", SC_UNAUTHORIZED));
        ObjectMapper objMapper = new ObjectMapper();

        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        wrapper.setStatus(SC_UNAUTHORIZED);
        wrapper.setContentType(APPLICATION_JSON_VALUE);
        wrapper.getWriter().println(objMapper.writeValueAsString(responseWrapper));
        wrapper.getWriter().flush();
    }
} 