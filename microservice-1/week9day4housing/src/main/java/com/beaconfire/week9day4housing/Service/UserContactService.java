package com.beaconfire.week9day4housing.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaconfire.week9day4housing.DAO.UserContactRepository;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.UserContact;

@Service
public class UserContactService {

	@Autowired
    private UserContactRepository userContactRepository;
	
	public List<UserContact> findAll()
	{
		return userContactRepository.findAll();
	}
	
	public Optional<UserContact> findByUserId (Integer userId)
	{
		return userContactRepository.findByUserId(userId);
	}
	
	public void UpdateUserInfo(UserContact userContact)
	{
		userContactRepository.save(userContact);
	}
}
