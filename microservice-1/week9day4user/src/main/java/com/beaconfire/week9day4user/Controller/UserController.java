package com.beaconfire.week9day4user.Controller;

import com.beaconfire.week9day4user.Domain.User;
import com.beaconfire.week9day4user.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4user.Domain.responseObjects.ResponseMsg;
import com.beaconfire.week9day4user.Filter.JwtFilter;
import com.beaconfire.week9day4user.Service.TimesheetService;
import com.beaconfire.week9day4user.Util.AmazonS3Util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("timesheet")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    @Autowired
    TimesheetService timesheetService;
    
    @Autowired
	private AmazonS3Util amazonS3Util;
    
    @GetMapping("/whoami")
	public User.Web whoami(HttpServletRequest httpServletRequest) {

		return new User.Web(JwtFilter.getUser(httpServletRequest));
	}

    @GetMapping("getAllR4ApproveRecord")
    public ResponseEntity<List<TimesheetRecord>> getAllCompletedRecord(){
//        return ResponseEntity.ok(userService.getAllUsers());
    	ResponseEntity<List<TimesheetRecord>> r = ResponseEntity.ok(timesheetService.findTimesheetRecordBySubmissionStatus("Completed","Approved"));
//    	System.out.print(r.getHeaders());
    	return r;
//    	return ResponseEntity.ok(timesheetService.update(0, "testURL"));
    }

    @GetMapping("getAllRecord")
    public ResponseEntity<List<TimesheetRecord>> getAllUsers(){
//        return ResponseEntity.ok(userService.getAllUsers());
    	ResponseEntity<List<TimesheetRecord>> r = ResponseEntity.ok(timesheetService.getAllRecords());
    	System.out.print(r.getHeaders());
    	return r;
//    	return ResponseEntity.ok(timesheetService.update(0, "testURL"));
    }
    
    @PostMapping("/uploadDocument")
	public ResponseMsg uploadAvatarFile(HttpServletRequest httpServletRequest, 
			@RequestParam("file") MultipartFile file,
			@RequestParam("date") String date,
			@RequestParam("userId") Integer userId) {
		System.out.println("uploading document for " + userId);
		String url = "";
	    if (file != null)
	    {
	    	try {
				url = amazonS3Util.uploadMultipartFile(httpServletRequest, file, "file");
//				System.out.println(url);
//				personalInfoService.updateAvatarInfo(email, url);
				if (timesheetService.update(userId, date, url))
					return new ResponseMsg(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return new ResponseMsg("failed");
	}
    
    @PostMapping("/update")
    public void updateTimesheet(@RequestParam("timesheet") TimesheetRecord ts)
    {
    	timesheetService.update(ts);
    }
    
    @PostMapping("/updateList")
    public void updateTimesheetList(List<TimesheetRecord> ts)
    {
    	System.out.println("received Request for default update");
    	timesheetService.updateList(ts);
    }
    
    @PostMapping("/changeStatSet") //for hr changing user request status
    public ResponseMsg changeTimesheetApprovalStatus(
    		@RequestParam("userId")Integer userId,
    		@RequestParam("date") String date,
    		@RequestParam("status") String status
    		)
    {
    	if (status.compareToIgnoreCase("Approved")==0)
		{
    		System.out.println("Approving userId: " + userId + " " + date);
		  if(timesheetService.approve(userId, date, status))
			  return new ResponseMsg("succeed");
		}
    	else if(status.compareToIgnoreCase("Not Approved")==0)
    	{
    		System.out.println("Rejecting userId: " + userId + " " + date);
    		if(timesheetService.reject(userId,date,status))
    			return new ResponseMsg("succeed");
    	}
    	return new ResponseMsg("failed updating status");
    }
    
    @GetMapping("/getUserWE")
    public ResponseEntity<Optional<TimesheetRecord>> getUserWERecord(@RequestHeader Map<String, String> headers, @RequestParam Integer userId, @RequestParam String weDate)
    {
    	return ResponseEntity.ok(timesheetService.getRecord(userId, weDate));
    }
    
    @GetMapping("/getUserWEByUserId")
    public ResponseEntity<Optional<List<TimesheetRecord>>> getUserWERecord(@RequestHeader Map<String, String> headers, @RequestParam Integer userId)
    {
    	return ResponseEntity.ok(timesheetService.getRecords(userId));
    }
    
    @GetMapping("/updateStats")
    public ResponseEntity<String> updateTimesheet(
    		@RequestParam Integer userId, 
    		@RequestParam String weDate,
    		@RequestParam String sbStats,
    		@RequestParam boolean approvedFile)
    {
    	timesheetService.updateTimesheet(userId,weDate,sbStats,approvedFile);
    	return ResponseEntity.ok("success");
    }
}
