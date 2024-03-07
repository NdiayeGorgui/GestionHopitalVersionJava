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
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.ChambreService;

@RestController
public class ChambreController {
	
	@Autowired
	ChambreService chambreService;
	
	@RequestMapping(value = "Chambres", method = RequestMethod.POST)
	public Chambre createChambre(@RequestBody Chambre chambre) {
		return chambreService.saveChambre(chambre);
	}
	
	@RequestMapping(value = "Chambres/{id}", method = RequestMethod.GET)
	public Chambre getChambre(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Chambre> chambre =chambreService.getChambre(id);
		if (chambre.isPresent()) {
			return chambre.get();
		} else {

			throw new ResourceNotFoundException("La Chambre avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Chambres", method = RequestMethod.GET)
	public List<Chambre> getAllChambres() {
		return chambreService.getAllChambres();
	}
	
	@RequestMapping(value = "Chambres/type/{typeChambre}", method = RequestMethod.GET)
	public List<Chambre> getAllChambresByType(@PathVariable("typeChambre") final String typeChambre) {
		return chambreService.getAllChambreByType(typeChambre);
	}
	
	@RequestMapping(value = "Chambres/{id}", method = RequestMethod.DELETE)
	public void deleteChambre(@PathVariable("id") Long id) {
		
		chambreService.deleteChambreById(id);
	}
	
	@RequestMapping(value = "/Chambres/{id}", method = RequestMethod.PUT)
	public Chambre updateChambre(@PathVariable("id") final Long id, @RequestBody Chambre chambre) throws ResourceNotFoundException  {
		return chambreService.updateChambre(id, chambre);
	}

}
