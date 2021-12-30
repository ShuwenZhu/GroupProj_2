package com.beaconfire.week9day4housing.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaconfire.week9day4housing.DAO.UserContactRepository;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.DefaultTimesheet;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.EmergencyContact;
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

	public void UpdateUserPersonal(Integer userId, String phoneNumber, String email, String addr, String em1f,
			String em1l, String em1p, String em2f, String em2l, String em2p) {
		UserContact user = findByUserId(userId).get();
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);
		user.setAddr(addr);
		user.setEmergencyContact(Arrays.asList(
				EmergencyContact.builder().firstName(em1f).lastName(em1l).contactNumber(em1p).build(),
				EmergencyContact.builder().firstName(em2f).lastName(em2l).contactNumber(em2p).build()
				));
		userContactRepository.save(user);
	}
}
