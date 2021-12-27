package com.beaconfire.week9day4composite.Domain.MangoDBobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Timesheet {
	public String date;
	public String startTime;
	public String endTime;
	public boolean isFloating;
	public boolean isHoliday;
	public boolean isVacation;
}
