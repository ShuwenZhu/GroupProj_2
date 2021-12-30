package com.beaconfire.week9day4housing.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaconfire.week9day4housing.DAO.UserContactRepository;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.DefaultTimesheet;
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

	public void updateDefault(Integer userId, String s1, String s2, String s3, String s4, String s5, String e1,
			String e2, String e3, String e4, String e5) {
		
		UserContact user = findByUserId(userId).get();
		user.setDefaultTimesheet(DefaultTimesheet.builder()
				.startingTimes(Arrays.asList(s1,s2,s3,s4,s5))
				.endingTimes(Arrays.asList(e1,e2,e3,e4,e5))
				.build());
		userContactRepository.save(user);
	}
}
