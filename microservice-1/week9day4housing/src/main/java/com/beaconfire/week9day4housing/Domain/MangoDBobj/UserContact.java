package com.beaconfire.week9day4housing.Domain.MangoDBobj;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection="ContactInfo")
public class UserContact implements Serializable{
	@Id
	public String _id;
	
	public String email;
	
	public Integer userId;
	
	public String addr;
	
	public String avatar;
	
	public String phoneNumber;
	
	public Integer maxFloatDays;
	
	public Integer maxVacationDays;
	
	public Integer usedFloatDays;
	
	public Integer usedVacationDays;
	
	public List<EmergencyContact> emergencyContact;
}
