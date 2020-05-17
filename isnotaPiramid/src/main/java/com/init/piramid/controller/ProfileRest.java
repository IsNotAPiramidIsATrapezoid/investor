package com.init.piramid.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.piramid.dao.ProfileDAO;
import com.init.piramid.entity.Profile;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/profile")
public class ProfileRest {
	@Autowired
	private ProfileDAO profileDAO;
	
	@GetMapping
	public ResponseEntity<java.util.List<Profile>> getPResponseEntity(){
		java.util.List<Profile> profile = profileDAO.findAll();
		return ResponseEntity.ok(profile);
	}
	
	@RequestMapping(value="{profileId}")
	public ResponseEntity<Profile> getInvestor(@PathVariable("profileId") Long profile){
		Optional<Profile> optionalProfile = profileDAO.findById(profile);
		if (optionalProfile.isPresent()) {
			return ResponseEntity.ok(optionalProfile.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping //Post Method
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){
		Profile newProfile = profileDAO.save(profile);
		return ResponseEntity.ok(newProfile);
	}
	
	@DeleteMapping(value="{profileId}") //Delete Method
	public ResponseEntity<Void> deleteProfile(@PathVariable("profileId") Long profileId){
		profileDAO.deleteById(profileId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Profile> updateInvestment(@RequestBody Profile profile){
		Optional<Profile> optionalProfile = profileDAO.findById(profile.getId());
		if (optionalProfile.isPresent()) {
			Profile updateProfile = optionalProfile.get();
			updateProfile.setName(profile.getName());
			updateProfile.setSector(profile.getSector());
			profileDAO.save(updateProfile);
			return ResponseEntity.ok(updateProfile);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
