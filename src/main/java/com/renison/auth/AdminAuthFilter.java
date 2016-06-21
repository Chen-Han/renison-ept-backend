package com.renison.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AdminAuthFilter extends GenericFilterBean {

	@Value("${ept.admin.token.header}")
	private String tokenHeader;

	@Autowired
	private JwtUtil tokenUtils;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = httpRequest.getHeader(this.tokenHeader);
		AdminTokenPayload adminTokenPayload = tokenUtils.verifyLoginToken(authToken, AdminTokenPayload.class);
		// we can add more sophisticated logic here
		if (adminTokenPayload == null || !adminTokenPayload.getRole().equals("ADMIN")) {
			throw new ServletException("Invalid token.");
		}

		chain.doFilter(request, response);
	}

}
