package com.beaconfire.week9day4composite.Domain.MangoDBobj;

import java.io.Serializable;
import java.util.List;

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
public class TimesheetRecord implements Serializable {
	
	public String _id;
	
	public Integer userId;
	
	public String weekEnding;
	
	public Integer billingHour;
	
	public Integer compensatedHour;
	
	public String attachment;
	
	public boolean hasAttachment;
	
	public String submissionStatus;
	
	public String approvalStatus;
	
	public List<Timesheet> timesheet;

}
