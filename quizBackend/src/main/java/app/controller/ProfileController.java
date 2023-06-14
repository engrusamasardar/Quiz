package app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Profile;
import app.model.UserSession;
import app.repository.ProfileRepository;

/**
 * @implNote Controller Class to manage Profile http operations
 * @author usardar
 *
 */
@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

	private JSONObject json = null;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private Profile profile;
	
	private UserSession userSession = null;
	
	
	/*
	 * Usage: 
	 * Example: http://localhost:8080/profile/retrieveprofiles
	 * Output: [ {"id":1 ,"user":"ahassan", "password":"112233"} ]
	 */
	@GetMapping("/retrieveprofiles")
	public List<Profile> getQuestionsList() {
		List<Profile> usersList = profileRepository.findAll();
		if(usersList != null) {
			return usersList;
		}
		return usersList;
	}

	/*
	 * Usage: 
	 * Example: http://localhost:8080/profile/addprofile
	 * Post Request : [ {"user":"usama", "password":"112233"} ]
	 * Output: { Profile Inserted Successfuly }
	 */
	@PostMapping("/addprofile")
	public String addUser(@RequestBody String newProfile) {
		json = new JSONObject(newProfile);
		profile.setuName(json.getString("uName"));
		profile.setuPassword(json.getString("uPassword"));
		profileRepository.save(profile);
		json.clear();
		json.put("success", "Profile Inserted Successfuly");
		return json.toString();
	}
	
	/*
	 * Usage: 
	 * Example: http://localhost:8080/profile/authenticate
	 * Post Request : [ {"user":"ahassan", "password":"112233"} ]
	 * Output: { true }
	 */
	@PostMapping("/authenticate")
	public boolean authticateProfile(@RequestBody String authProfile, HttpServletResponse response) throws IOException {
		userSession = UserSession.getInstance();
		json = new JSONObject(authProfile);
		Integer tempId = profileRepository.authByUserName(json.getString("userName"));
		if (tempId!=null) {
			profile = profileRepository.authByPassword(tempId, json.getString("userPassword"));
			if (profile!=null) {
				userSession.id = profile.getuId();
				return true;
//				response.sendRedirect("/quiz/getquestion");
			}else {
				json.clear();
				return false;
//				json.put("error", "Invalid Password");
//				return json.toString();
			}
		}else {
			json.clear();
			return false;
//			json.put("error", "Invalid User Name");
//			return json.toString();
		}
	}
}