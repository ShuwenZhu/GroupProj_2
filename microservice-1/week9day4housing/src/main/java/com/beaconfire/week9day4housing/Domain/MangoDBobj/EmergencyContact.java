package com.beaconfire.week9day4housing.Domain.MangoDBobj;

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
public class EmergencyContact {
	
	public String firstName;
	
	public String lastName;
	
	public String contactNumber;
}
