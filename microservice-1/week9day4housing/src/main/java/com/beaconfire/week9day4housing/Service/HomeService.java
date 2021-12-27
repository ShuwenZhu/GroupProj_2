package com.beaconfire.week9day4housing.Service;

import org.springframework.stereotype.Service;


@Service
public class HomeService {
	String employeeNM[] = {"admin","test1"};

	public String getEmployeeByUserId(int id) {
		if (id <0 || id >= employeeNM.length)
			return null;
		return new String(employeeNM[id]);
	}
	
}
