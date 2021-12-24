package com.beaconfire.week9day4user.Domain.MangoDBobj;

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
@Document(collection="TimesheetManagementDB")
public class TimesheetRecord implements Serializable {
	
	@Id
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
