package com.beaconfire.week9day4user.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class CookieUtil {
	public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure,
			Integer maxAge, String domain) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(secure);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(maxAge);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);
	}

	public static String getValue(HttpServletRequest httpServletRequest, String cookieName) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, cookieName);
		return cookie != null ? cookie.getValue() : null;
	}
}
