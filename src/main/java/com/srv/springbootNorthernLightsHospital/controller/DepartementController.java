package com.srv.springbootNorthernLightsHospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srv.springbootNorthernLightsHospital.entities.Departement;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.DepartementService;


@RestController
public class DepartementController {

	
	@Autowired
	DepartementService departementService;
	
	@RequestMapping(value = "Departements", method = RequestMethod.POST)
	public Departement createDepartement(@RequestBody Departement departement) {
		return departementService.saveDepartement(departement);
	}
	
	@RequestMapping(value = "Departements/{id}", method = RequestMethod.GET)
	public Departement getDepartement(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Departement> departement =departementService.getDepartement(id);
		if (departement.isPresent()) {
			return departement.get();
		} else {

			throw new ResourceNotFoundException("Le lit avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Departements", method = RequestMethod.GET)
	public List<Departement> getAllDepartements() {
		return departementService.getAllDepartements();
	}
	
	@RequestMapping(value = "Departements/{id}", method = RequestMethod.DELETE)
	public void deleteDepartement(@PathVariable("id") Long id) {
		
		departementService.deleteDepartementById(id);
	}
	
	@RequestMapping(value = "/Departements/{id}", method = RequestMethod.PUT)
	public Departement updateDepartement(@PathVariable("id") final Long id, @RequestBody Departement departement) throws ResourceNotFoundException  {
		return departementService.updateDepartement(id, departement);
	}
}
