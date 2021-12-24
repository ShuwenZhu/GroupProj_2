package com.beaconfire.week9day4user.Controller;

import com.beaconfire.week9day4user.DAO.TimesheetRepository;
import com.beaconfire.week9day4user.Domain.responseObjects.ResponseMsg;
import com.beaconfire.week9day4user.Util.AmazonS3Util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("timesheet")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class UserController {

    @Autowired
    TimesheetRepository timesheetRepository;
    
    @Autowired
	private AmazonS3Util amazonS3Util;


    @GetMapping("getAllRecord")
    public ResponseEntity getAllUsers(){
//        return ResponseEntity.ok(userService.getAllUsers());
//    	timesheetRepository.save(TimesheetRecord.builder().userId(3).build());
    	return ResponseEntity.ok(timesheetRepository.findAll());
    }
    
    @PostMapping("/uploadDocument")
	public ResponseMsg uploadAvatarFile(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
		String userId = httpServletRequest.getParameter("userId");
		System.out.println("uploading document for " + userId);
		String url = "";
	    if (file != null)
	    {
	    	try {
				url = amazonS3Util.uploadMultipartFile(httpServletRequest, file, "file");
//				System.out.println(url);
//				personalInfoService.updateAvatarInfo(email, url);
				return new ResponseMsg(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return new ResponseMsg("failed");
	}


}
