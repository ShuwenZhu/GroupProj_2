package com.beaconfire.service;

import com.beaconfire.domain.User;

public interface AuthService {
	User getUser(String username_email);

	void saveUser(User user);
}
