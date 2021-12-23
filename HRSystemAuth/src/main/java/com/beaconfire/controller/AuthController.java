package com.beaconfire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.beaconfire.domain.User;
import com.beaconfire.service.AuthService;
import com.beaconfire.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AuthController {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private AuthService authService;

	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(HttpServletRequest httpServletRequest, Model model) {
		User user = JwtUtil.getUser(httpServletRequest, mapper);

		if (user != null) {
			model.addAttribute("user", user);
		}

		return "login";
	}

	@PostMapping("/login")
	public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect,
			Model model) throws JsonProcessingException {
		User user = authService.getUser(username);
		if (user == null) {
			model.addAttribute("message", String.format("%s doesn't exist!", username));
			return "login";
		} else if (!user.getUser_password().equals(password)) {
			model.addAttribute("message", "Incorrect password!");
			return "login";
		}

		JwtUtil.setUser(httpServletResponse, mapper, user);
		if (redirect.isEmpty()) {
			return home();
		} else {
			return "redirect:" + redirect;
		}
	}

	@GetMapping("/register")
	public String register(String token, String redirect, Model model) {
		String registerUrl = "register";
		String message = null;

		try {
			String email = JwtUtil.getEmail(token);
			if (email == null) {
				message = "Invalid token";
				return registerUrl;
			}
			model.addAttribute("email", email);

			if (authService.getUser(email) != null) {
				message = String.format("Email '%s' has been registered", email);
				return registerUrl;
			}
		} finally {
			if (message != null) {
				model.addAttribute("message", message);
			}
		}

		return "register";
	}

	@PostMapping("/register")
	public String register(HttpServletResponse httpServletResponse, String username, String password,
			String passwordconfirm, String token, String redirect, Model model) {
		String registerUrl = "register";
		String message = null;

		try {
			String email = JwtUtil.getEmail(token);
			if (email == null) {
				message = "Invalid token";
				return registerUrl;
			}
			model.addAttribute("email", email);

			if (authService.getUser(email) != null) {
				message = String.format("Email '%s' has been registered", email);
				return registerUrl;
			}

			if (username.isEmpty() || password.isEmpty()) {
				message = "Username or Password couldn't be empty";
				return registerUrl;
			}

			if (!password.equals(passwordconfirm)) {
				message = "Password and confirm password does not match";
				return registerUrl;
			}

			if (authService.getUser(username) != null) {
				message = String.format("Username '%s' has been registered", username);
				return registerUrl;
			}

			User user = new User();
			user.setUsername(username);
			user.setUser_password(password);
			user.setEmail(email);

			try {
				authService.saveUser(user);
			} catch (DataAccessException e) {
				message = "Registration failed";
				return registerUrl;
			}

			if (redirect.isEmpty()) {
				return home();
			} else {
				return "redirect:" + redirect;
			}
		} finally {
			if (message != null) {
				model.addAttribute("message", message);
			}
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse httpServletResponse, String redirect) {
		JwtUtil.removeUser(httpServletResponse);
		if (redirect == null || redirect.isEmpty()) {
			return home();
		} else {
			return "redirect:" + redirect;
		}
	}
}
