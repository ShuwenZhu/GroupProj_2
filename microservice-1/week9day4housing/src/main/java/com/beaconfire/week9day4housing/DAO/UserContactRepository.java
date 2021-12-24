package com.beaconfire.week9day4housing.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beaconfire.week9day4housing.Domain.MangoDBobj.UserContact;


public interface UserContactRepository extends MongoRepository<UserContact, String>  {
	public Optional<UserContact> findByUserId(Integer userId);
	public List<UserContact> findAll();
}
