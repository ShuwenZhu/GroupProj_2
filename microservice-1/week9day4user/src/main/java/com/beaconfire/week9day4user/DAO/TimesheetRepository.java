package com.beaconfire.week9day4user.DAO;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.beaconfire.week9day4user.Domain.MangoDBobj.TimesheetRecord;

public interface TimesheetRepository extends MongoRepository<TimesheetRecord, String> {
	public Optional<List<TimesheetRecord>> findByUserId(Integer userId);
	public List<TimesheetRecord> findAll();
	@Query("{'submissionStatus' : ?0, 'approvalStatus': {$ne : ?1}}")
	public List<TimesheetRecord> findTimesheetRecordBySubmissionStatus(String sbStatus, String aStatus);
}
