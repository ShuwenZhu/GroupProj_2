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


    @GetMapping("getAllRecord")
    public ResponseEntity getAllUsers(){
//        return ResponseEntity.ok(userService.getAllUsers());
    	ResponseEntity r = ResponseEntity.ok(timesheetService.getAllRecords());
    	System.out.print(r.getHeaders());
    	return r;
//    	return ResponseEntity.ok(timesheetService.update(0, "testURL"));
    }
    
    @PostMapping("/uploadDocument")
	public ResponseMsg uploadAvatarFile(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
		Integer userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		String date = httpServletRequest.getParameter("date");
		System.out.println("uploading document for " + userId);
		String url = "";
	    if (file != null)
	    {
	    	try {
				url = amazonS3Util.uploadMultipartFile(httpServletRequest, file, "file");
//				System.out.println(url);
//				personalInfoService.updateAvatarInfo(email, url);
				timesheetService.update(userId, date, url);
				return new ResponseMsg(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return new ResponseMsg("failed");
	}
    
    @PostMapping("/approveStatSet")
    public ResponseMsg approveTimesheet(HttpServletRequest httpServletRequest)
    {
    	Integer userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
    	String date = httpServletRequest.getParameter("date");
    	String status = httpServletRequest.getParameter("status");
    	if (timesheetService.approve(userId, date, status))
    		return new ResponseMsg("succeed");
    	else
    		return new ResponseMsg("failed");
    }
    
    @GetMapping("/getUserWE")
    public ResponseEntity<Optional<TimesheetRecord>> getUserWERecord(@RequestHeader Map<String, String> headers, @RequestParam Integer userId, @RequestParam String weDate)
    {
    	return ResponseEntity.ok(timesheetService.getRecord(userId, weDate));
    }
    
    @GetMapping("/getUserWEByUserId")
    public ResponseEntity<Optional<List<TimesheetRecord>>> getUserWERecord(@RequestParam Integer userId)
    {
    	return ResponseEntity.ok(timesheetService.getRecords(userId));
    }


}
