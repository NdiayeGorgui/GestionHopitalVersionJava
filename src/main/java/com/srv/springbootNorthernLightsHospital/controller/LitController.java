package com.srv.springbootNorthernLightsHospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srv.springbootNorthernLightsHospital.entities.Chambre;
import com.srv.springbootNorthernLightsHospital.entities.Lit;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.LitService;


@RestController
public class LitController {
	@Autowired
	LitService litService;
	
	@RequestMapping(value = "Lits", method = RequestMethod.POST)
	public Lit createLit(@RequestBody Lit lit) {
		return litService.saveLit(lit);
	}
	
	@RequestMapping(value = "Lits/{id}", method = RequestMethod.GET)
	public Lit getLit(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Lit> lit =litService.getLit(id);
		if (lit.isPresent()) {
			return lit.get();
		} else {

			throw new ResourceNotFoundException("Le lit avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Lits", method = RequestMethod.GET)
	public List<Lit> getAllLits() {
		return litService.getAllLits();
	}
	
	@RequestMapping(value = "Lits/type/{typeLit}", method = RequestMethod.GET)
	public List<Lit> getAllLitsByType(@PathVariable("typeLit") final String typeLit) {
		return litService.getAllLitsByType(typeLit);
	}
	
	@RequestMapping(value = "Lits/type/disponible/{typeLit}", method = RequestMethod.GET)
	public List<Lit> getAllAvailableLitsByType(@PathVariable("typeLit") final String typeLit) {
		return litService.getAllAvailableLitsByType(typeLit);
	}
	@RequestMapping(value = "Lits/type/disponible/ok/{dept}", method = RequestMethod.GET)
	public List<Lit> getAllAvailableLitsBydept(@PathVariable("dept") final String dept) {
		return litService.getAllLitsAvailableByDept(dept);
	}
	
	@RequestMapping(value = "Lits/{id}", method = RequestMethod.DELETE)
	public void deleteLit(@PathVariable("id") Long id) {
		
		litService.deleteLitById(id);
	}
	
	@RequestMapping(value = "/Lits/{id}", method = RequestMethod.PUT)
	public Lit updateLit(@PathVariable("id") final Long id, @RequestBody Lit lit) throws ResourceNotFoundException  {
		return litService.updateLit(id, lit);
	}
}
