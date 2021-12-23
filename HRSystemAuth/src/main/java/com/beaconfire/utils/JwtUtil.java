package com.beaconfire.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beaconfire.config.JwtConfig;
import com.beaconfire.domain.User;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	public static String generateToken(String signingKey, String subject) {
		Date now = new Date(System.currentTimeMillis());

		JwtBuilder builder = Jwts.builder().setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,
				signingKey);

		return builder.compact();
	}

	public static String getSubject(HttpServletRequest httpServletRequest, String jwtCookieName, String signingKey) {
		String token = CookieUtil.getValue(httpServletRequest, jwtCookieName);
		if (token == null) {
			return null;
		}
		try {
			return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
		} catch (JwtException e) {
			return null;
		}
	}

	public static User getUser(HttpServletRequest httpServletRequest, ObjectMapper mapper) {
		try {
			String userJson = getSubject(httpServletRequest, JwtConfig.JWT_TOKEN_COOKIE_NAME, JwtConfig.SIGNING_KEY);
			if (userJson == null) {
				return null;
			}
			return mapper.readValue(userJson, User.class);
		} catch (JacksonException e) {
			return null;
		}
	}

	public static void setUser(HttpServletResponse httpServletResponse, ObjectMapper mapper, User user)
			throws JsonProcessingException {
		String token = generateToken(JwtConfig.SIGNING_KEY, mapper.writeValueAsString(user));
		CookieUtil.create(httpServletResponse, JwtConfig.JWT_TOKEN_COOKIE_NAME, token, false, -1, null);
	}

	public static void removeUser(HttpServletResponse httpServletResponse) {
		CookieUtil.create(httpServletResponse, JwtConfig.JWT_TOKEN_COOKIE_NAME, "", false, 0, null);
	}

	public static String getEmail(String token) {
		try {
			return Jwts.parser().setSigningKey(JwtConfig.SIGNING_EMAIL_KEY).parseClaimsJws(token).getBody()
					.getSubject();
		} catch (JwtException e) {
			return null;
		}
	}
}
