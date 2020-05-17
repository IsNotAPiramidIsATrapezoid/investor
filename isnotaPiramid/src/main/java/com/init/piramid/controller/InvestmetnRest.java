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

import com.init.piramid.dao.InvestmentDAO;
import com.init.piramid.entity.Investments;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/investments")
public class InvestmetnRest {
	
	@Autowired
	private InvestmentDAO investestmentDAO;
	
	@GetMapping
	public ResponseEntity<java.util.List<Investments>> getInvesment(){
		java.util.List<Investments> investvestments = investestmentDAO.findAll();
		return ResponseEntity.ok(investvestments);
	}
	
	@RequestMapping(value="{investmentId}")
	public ResponseEntity<Investments> getInvestor(@PathVariable("investmentId") Long investmentId){
		Optional<Investments> optionalInvestments = investestmentDAO.findById(investmentId);
		if (optionalInvestments.isPresent()) {
			return ResponseEntity.ok(optionalInvestments.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping //Post Method
	public ResponseEntity<Investments> createInvestment(@RequestBody Investments investment){
		Investments newInvestment = investestmentDAO.save(investment);
		return ResponseEntity.ok(newInvestment);
	}
	
	@DeleteMapping(value="{investorId}") //Delete Method
	public ResponseEntity<Void> deleteInvestment(@PathVariable("investmentId") Long investmentId){
		investestmentDAO.deleteById(investmentId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Investments> updateInvestment(@RequestBody Investments investments){
		Optional<Investments> optionalInvestments = investestmentDAO.findById(investments.getId());
		if (optionalInvestments.isPresent()) {
			Investments updateInvestment = optionalInvestments.get();
			updateInvestment.setAmount(investments.getAmount());
			investestmentDAO.save(updateInvestment);
			return ResponseEntity.ok(updateInvestment);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
