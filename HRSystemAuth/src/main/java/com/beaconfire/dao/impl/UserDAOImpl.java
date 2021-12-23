package com.beaconfire.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.beaconfire.dao.AbstractHibernateDAO;
import com.beaconfire.dao.UserDAO;
import com.beaconfire.domain.User;

@Repository
public class UserDAOImpl extends AbstractHibernateDAO<User> implements UserDAO {

	public UserDAOImpl() {
		setClazz(User.class);
	}

	@Override
	public User getUserByName(String username) {
		List<User> users = this.findByField("username", username);
		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public User getUserByEmail(String email) {
		List<User> users = this.findByField("email", email);
		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public void saveUser(User user) {
		this.persist(user);
	}
}
