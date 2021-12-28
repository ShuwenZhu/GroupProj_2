package com.beaconfire.week9day4user.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beaconfire.week9day4user.DAO.TimesheetRepository;
import com.beaconfire.week9day4user.Domain.MangoDBobj.TimesheetRecord;

@Service
public class TimesheetService {
	
	@Autowired
	private TimesheetRepository timesheetRepository;
	
	public List<TimesheetRecord> getAllRecords()
	{
		return timesheetRepository.findAll();
	}
	
	public List<TimesheetRecord> findTimesheetRecordBySubmissionStatus(String sbStatus, String aStatus)
	{
		List<TimesheetRecord> result = timesheetRepository.findTimesheetRecordBySubmissionStatus(sbStatus, aStatus);
		result.sort((A,B)->A.getUserId()-B.getUserId());
		return result;
	}
	
	public boolean update(Integer userId, String weDate, String documentUrl)
	{
		Optional<List<TimesheetRecord>> recordOptional = timesheetRepository.findByUserId(userId);
		if (recordOptional.isPresent())
		{
			List<TimesheetRecord> rl = recordOptional.get().stream()
					.filter(_r -> _r.weekEnding.compareToIgnoreCase(weDate) == 0)
					.collect(Collectors.toList());
			if (rl.size() == 0) return false;
			TimesheetRecord r = rl.get(0);
			r.setAttachment(documentUrl);
			timesheetRepository.save(r);
			return true;
		}
		return false;
	}
	
	public boolean approve(Integer userId, String date, String status)
	{
		Optional<List<TimesheetRecord>> recordOptional = timesheetRepository.findByUserId(userId);
		if (recordOptional.isPresent())
		{
			List<TimesheetRecord> rl = recordOptional.get().stream()
					.filter(_r -> _r.weekEnding.compareToIgnoreCase(date) == 0)
					.collect(Collectors.toList());
			if (rl.size() == 0) return false;
			TimesheetRecord r = rl.get(0);
			r.setApprovalStatus(status);
			timesheetRepository.save(r);
			return true;
		}
		return false;
	}

	public Optional<TimesheetRecord> getRecord(Integer userId, String weDate) {
		Optional<List<TimesheetRecord>> recordOptional = timesheetRepository.findByUserId(userId);
		TimesheetRecord r = null;
		if (recordOptional.isPresent())
		{
			for (TimesheetRecord tr : recordOptional.get())
			{
				System.out.println(tr.getWeekEnding());
			}
			List<TimesheetRecord> rl = recordOptional.get().stream()
					.filter(_r -> _r.weekEnding.compareToIgnoreCase(weDate) == 0)
					.collect(Collectors.toList());
			r = rl.get(0);
		}
		return Optional.ofNullable(r);
	}

	public Optional<List<TimesheetRecord>> getRecords(Integer userId) {
		
		return timesheetRepository.findByUserId(userId);
	}

	public boolean reject(Integer userId, String date, String status) {
		Optional<List<TimesheetRecord>> recordOptional = timesheetRepository.findByUserId(userId);
		if (recordOptional.isPresent())
		{
			List<TimesheetRecord> rl = recordOptional.get().stream()
					.filter(_r -> _r.weekEnding.compareToIgnoreCase(date) == 0)
					.collect(Collectors.toList());
			if (rl.size() == 0) return false;
			TimesheetRecord r = rl.get(0);
			r.setApprovalStatus(status);
			r.setSubmissionStatus("Incomplete");
			timesheetRepository.save(r);
			return true;
		}
		return false;
	}

	public void update(TimesheetRecord ts) {
		timesheetRepository.save(ts);
	}
	
}
