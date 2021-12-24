package com.beaconfire.week9day4housing.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.beaconfire.week9day4housing.Domain.User;


public class AdminFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		User user = JwtFilter.getUser(httpServletRequest);
		if (!user.isAdmin()) {
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}
}
