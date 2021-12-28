package com.beaconfire.week9day4composite.Domain;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserWEDetail {
	TimesheetRecord record;
	
    public Integer maxFloatDays;
	
	public Integer maxVacationDays;
	
	public Integer usedFloatDays;
	
	public Integer usedVacationDays;
}
