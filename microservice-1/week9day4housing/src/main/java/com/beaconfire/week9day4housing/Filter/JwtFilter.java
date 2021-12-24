package com.beaconfire.week9day4housing.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.beaconfire.week9day4housing.Domain.User;
import com.beaconfire.week9day4housing.Util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Value("${services.auth}")
	private String authService;

	@Autowired
	private ObjectMapper mapper;

	private static final String USER_KEY = "user";

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		// when CORS, the browser will send a preflight request to server with 'OPTIONS'
		// method
		if ("OPTIONS".compareToIgnoreCase(httpServletRequest.getMethod()) == 0) {
			// if bring the cookie, we need to specify the correct 'Origin'
			// when we CORS, Origin means where the request come from
			httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

			// allow the request with cookie which contains JWT-TOKEN and JSESSIONID
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

			// allow the request header contain the content-type option
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type");

			httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		User user = JwtUtil.getUser(httpServletRequest, mapper);
		if (user == null) {
			httpServletRequest.getSession().removeAttribute(USER_KEY);

			httpServletResponse.sendRedirect(
					String.format(authService, httpServletRequest.getServerName(), httpServletRequest.getRequestURL()));
//		} else {
//			User user2 = (User) httpServletRequest.getSession().getAttribute(USER_KEY);
//			if (user2 == null || user2.getId() != user.getId()) {
//				user.setAdmin(homeService.isAdmin(user.getId()));
//
//				Employee employee = homeService.getEmployeeByUserId(user.getId());
//				if (employee != null) {
//					user.setEmployeeId(employee.getId());
//				}
//
//				httpServletRequest.getSession().setAttribute(USER_KEY, user);
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
//		}
	}

	public static User getUser(HttpServletRequest httpServletRequest) {
		return (User) httpServletRequest.getSession().getAttribute(USER_KEY);
	}
}
