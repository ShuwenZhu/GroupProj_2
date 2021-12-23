package com.beaconfire.dao;

import com.beaconfire.domain.User;

public interface UserDAO {
	User getUserByName(String username);

	User getUserByEmail(String email);

	void saveUser(User user);
}
