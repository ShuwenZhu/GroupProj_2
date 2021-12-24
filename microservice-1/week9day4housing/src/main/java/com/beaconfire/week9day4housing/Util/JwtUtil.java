package com.beaconfire.week9day4housing.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.beaconfire.week9day4housing.Config.JwtConfig;
import com.beaconfire.week9day4housing.Domain.User;
import com.fasterxml.jackson.core.JacksonException;
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

	public static String generateToken(String signingKey, String subject, int expiredSeconds) {
		long now = System.currentTimeMillis();

		JwtBuilder builder = Jwts.builder().setSubject(subject).setIssuedAt(new Date(now))
				.setExpiration(new Date(now + expiredSeconds * 1000)).signWith(SignatureAlgorithm.HS256, signingKey);

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
}
