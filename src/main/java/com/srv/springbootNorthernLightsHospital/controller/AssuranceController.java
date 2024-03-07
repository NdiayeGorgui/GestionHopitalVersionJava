package com.srv.springbootNorthernLightsHospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.srv.springbootNorthernLightsHospital.entities.Assurance;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.AssuranceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AssuranceController {
	@Autowired
	AssuranceService assuranceService;
	
	@RequestMapping(value = "Assurances", method = RequestMethod.POST)
	public Assurance createAssurance(@RequestBody Assurance assurance) {
		return assuranceService.saveAssurance(assurance);
	}
	
	@RequestMapping(value = "Assurances/{id}", method = RequestMethod.GET)
	public Assurance getAssurance(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Assurance> assurance = assuranceService.getAssurance(id);
		if (assurance.isPresent()) {
			return assurance.get();
		} else {

			throw new ResourceNotFoundException("L'Assurance avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Assurances", method = RequestMethod.GET)
	public List<Assurance> getAllAssurances() {
		return assuranceService.getAllAssurances();
	}
	
	@RequestMapping(value = "Assurances/{id}", method = RequestMethod.DELETE)
	public void deleteAssurance(@PathVariable("id") Long id) {
		
		assuranceService.deleteAssuranceById(id);
	}
	
	@RequestMapping(value = "/Assurances/{id}", method = RequestMethod.PUT)
	public Assurance updateAssurance(@PathVariable("id") final Long id, @RequestBody Assurance assurance) throws ResourceNotFoundException  {
		return assuranceService.updateAssurance(id, assurance);
	}

}
