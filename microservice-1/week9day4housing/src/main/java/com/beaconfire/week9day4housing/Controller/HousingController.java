package com.beaconfire.week9day4housing.Controller;

import com.beaconfire.week9day4housing.DAO.UserContactRepository;
import com.beaconfire.week9day4housing.Domain.MangoDBobj.UserContact;
import com.beaconfire.week9day4housing.Domain.responseObjects.ResponseMsg;
import com.beaconfire.week9day4housing.Service.UserContactService;
import com.beaconfire.week9day4housing.Util.AmazonS3Util;

import java.io.IOException;
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
@RequestMapping("usercontact")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class HousingController {
    
    @Autowired
    private UserContactService userContactService;
//    private UserContactRepository userContactRepository;
    
    @Autowired
	private AmazonS3Util amazonS3Util;

    @GetMapping("getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(userContactService.findAll());
    }
    
    @GetMapping("getUserContact")
    public ResponseEntity<Optional<UserContact>> getUserContact(@RequestHeader Map<String, String> headers, Integer userId)
    {
    	System.out.println(">>>>>>>>CALLED USERCONTACT GET USERCONTACT METHOD");
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
    
    @PostMapping("/update")
	public ResponseMsg updateUserContact(@RequestParam("user") UserContact user)
	{
    	userContactService.UpdateUserInfo(user);
    	return new ResponseMsg("success");
	}

}
