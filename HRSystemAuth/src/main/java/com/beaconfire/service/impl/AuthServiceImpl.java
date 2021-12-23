package com.beaconfire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beaconfire.dao.UserDAO;
import com.beaconfire.domain.User;
import com.beaconfire.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserDAO userDAO;

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	@Transactional
	public User getUser(String username_email) {
		if (isValidEmailAddress(username_email)) {
			return userDAO.getUserByEmail(username_email);
		} else {
			return userDAO.getUserByName(username_email);
		}
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}
}
