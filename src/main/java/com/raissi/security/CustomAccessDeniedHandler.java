package com.raissi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Custom Access denied handler, called in the spring-security config
 *
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
 
	public CustomAccessDeniedHandler() {
	}
 
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {
	   response.sendRedirect(request.getContextPath()+"/accessdenied");
	   request.getSession().setAttribute("message",
		"You do not have permission to access this page!");
 
	}
 
}
