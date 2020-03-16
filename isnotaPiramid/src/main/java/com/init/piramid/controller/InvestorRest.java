package com.init.piramid.controller;

import java.awt.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.piramid.dao.InvestorDAO;
import com.init.piramid.entity.Investor;

@RestController
@RequestMapping("/investors")
public class InvestorRest {
	
	@Autowired
	private InvestorDAO investorDAO;
	
	@GetMapping
	public ResponseEntity<java.util.List<Investor>> getInvestor(){
		java.util.List<Investor> investors = investorDAO.findAll();
		return ResponseEntity.ok(investors);
	}
	
	@RequestMapping(value="{investorId}")
	public ResponseEntity<Investor> getInvestor(@PathVariable("investorId") Long investorId){
		Optional<Investor> optionalInvestor = investorDAO.findById(investorId);
		if (optionalInvestor.isPresent()) {
			return ResponseEntity.ok(optionalInvestor.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping //Post Method
	public ResponseEntity<Investor> createInvestor(@RequestBody Investor investor){
		Investor newInvestor = investorDAO.save(investor);
		return ResponseEntity.ok(newInvestor);
	}
	
	@DeleteMapping(value="{investorId}") //Delete Method
	public ResponseEntity<Void> deleteInvestor(@PathVariable("investorId") Long investorId){
		investorDAO.deleteById(investorId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Investor> updateInvestor(@RequestBody Investor investor){
		Optional<Investor> optionalInvestor = investorDAO.findById(investor.getId());
		if (optionalInvestor.isPresent()) {
			Investor updateInvestor = optionalInvestor.get();
			updateInvestor.setDocument(investor.getDocument());
			updateInvestor.setName(investor.getName());
			updateInvestor.setMail(investor.getMail());
			updateInvestor.setInvestments(investor.getInvestments());
			updateInvestor.setProfile(investor.getProfile());
			investorDAO.save(updateInvestor);
			return ResponseEntity.ok(updateInvestor);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	//@GetMapping	
	//public ResponseEntity<Investor> getInvestor(){
	//		Investor investor = new Investor();
	//		investor.setId(123456);
	//		investor.setName("Investor Name");
	//		return ResponseEntity.ok(investor);
	//	}

	//@GetMapping
	//@RequestMapping(value="hello2", method = RequestMethod.GET)
	public String hello() {
		// TODO Auto-generated method stub
		return "Hello world";
	}

}
