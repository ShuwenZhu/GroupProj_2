package com.beaconfire.week9day4composite.Domain.MangoDBobj;

import java.io.Serializable;
import java.util.List;

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
public class UserContact implements Serializable{

	public String _id;
	
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
