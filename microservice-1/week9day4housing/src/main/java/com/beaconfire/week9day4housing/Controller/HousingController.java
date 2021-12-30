package com.beaconfire.week9day4housing.Controller;

import com.beaconfire.week9day4housing.DAO.UserContactRepository;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.UserContact;
import com.beaconfire.week9day4housing.Domain.responseObjects.ResponseMsg;
import com.beaconfire.week9day4housing.Service.UserContactService;
import com.beaconfire.week9day4housing.Util.AmazonS3Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("usercontact")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class HousingController {
    
    @Autowired
    private UserContactService userContactService;
//    private UserContactRepository userContactRepository;
    
    @Autowired
	private AmazonS3Util amazonS3Util;

    @GetMapping("getAll")
    public ResponseEntity<List<UserContact>> getAll(){
        return ResponseEntity.ok(userContactService.findAll());
    }
    
    @GetMapping("getUserContact")
    public ResponseEntity<Optional<UserContact>> getUserContact(@RequestHeader Map<String, String> headers, Integer userId)
    {
    	return ResponseEntity.ok(userContactService.findByUserId(userId));
    }
    
    @PostMapping("/uploadavatar")
	public ResponseMsg uploadAvatarFile(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
		String userId = httpServletRequest.getParameter("userId");
		Optional<UserContact> u = userContactService.findByUserId(Integer.parseInt(userId));
		if (u.isPresent())
		{
			System.out.println("updating avatar for " + userId);
			String url = "";
		    if (file != null)
		    {
		    	try {
					url = amazonS3Util.uploadMultipartFile(httpServletRequest, file, "file");
	//				System.out.println(url);
					u.get().setAvatar(url);
					userContactService.UpdateUserInfo(u.get());
					return new ResponseMsg(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	    return new ResponseMsg("failed");
	}
    
    @GetMapping(value = "/update")
	public ResponseEntity<String> updateUserContact(@RequestBody UserContact user)
	{
//    	response.setHeader("Access-Control-Allow-Credentials", "true");
    	System.out.println(">>>>>>>>CALLED USERCONTACT POST USERCONTACT METHOD");
    	System.out.println(user);
//    	try {
//			UserContact user = new ObjectMapper().readValue(data, UserContact.class);
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//    	userContactService.UpdateUserInfo(user);
    	return ResponseEntity.ok("success");
	}
    
    @PostMapping(value = "/updateDefault")
	public ResponseEntity<String> updateUserContactDefault(
			@RequestParam Integer userId,
			@RequestParam String s1,
			@RequestParam String s2,
			@RequestParam String s3,
			@RequestParam String s4,
			@RequestParam String s5,
			@RequestParam String e1,
			@RequestParam String e2,
			@RequestParam String e3,
			@RequestParam String e4,
			@RequestParam String e5
			)
	{
    	userContactService.updateDefault(userId, s1, s2, s3, s4, s5, e1, e2, e3, e4, e5);
		return ResponseEntity.ok("success");	
	}
    
    @GetMapping("/updatePersonal")
	public ResponseMsg updateUserContactGet(
			@RequestParam Integer userId,
			@RequestParam String phoneNumber,
			@RequestParam String email,
			@RequestParam String addr,
			@RequestParam String em1f,
			@RequestParam String em1l,
			@RequestParam String em1p,
			@RequestParam String em2f,
			@RequestParam String em2l,
			@RequestParam String em2p
			)
	{
    	userContactService.UpdateUserPersonal(userId, phoneNumber, email,
    			addr, em1f, em1l, em1p, em2f, em2l, em2p);
    	return new ResponseMsg("success");
	}
    
    @GetMapping("/updateDayUsage")
	public ResponseMsg updateDayUsage(
			@RequestParam Integer userId,
			@RequestParam Integer floatday,
			@RequestParam Integer vacationday){
    	userContactService.updateUserDayUsage(userId,floatday,vacationday);
    	return new ResponseMsg("success");
    }
}
