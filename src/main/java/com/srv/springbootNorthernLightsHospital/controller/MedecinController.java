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
import com.srv.springbootNorthernLightsHospital.entities.Medecin;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.MedecinService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MedecinController {

	@Autowired
	MedecinService medecinService;
	
	@RequestMapping(value = "Medecins", method = RequestMethod.POST)
	public Medecin createMedecin(@RequestBody Medecin medecin) {
		return medecinService.saveMedecin(medecin);
	}
	
	@RequestMapping(value = "Medecins/{id}", method = RequestMethod.GET)
	public Medecin getMedecin(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Medecin> medecin = medecinService.getMedecin(id);
		if (medecin.isPresent()) {
			return medecin.get();
		} else {

			throw new ResourceNotFoundException("Le Medecin avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Medecins", method = RequestMethod.GET)
	public List<Medecin> getAllMedecins() {
		return medecinService.getAllMedecins();
	}
	
	@RequestMapping(value = "Medecins/{idMedecinToAdd}/{idMedecinToRemove}", method = RequestMethod.DELETE)
	public void repllaceMedecin(@PathVariable("idMedecinToAdd") final Long idMedecinToAdd,@PathVariable("idMedecinToRemove") final Long idMedecinToRemove) {
		 medecinService.replaceMedecin(idMedecinToAdd, idMedecinToRemove);
	}
	
	@RequestMapping(value = "Medecins/{id}", method = RequestMethod.DELETE)
	public void deleteMedecin(@PathVariable("id") Long id) {
		
		medecinService.deleteMedecinById(id);
	}
	
	@RequestMapping(value = "/Medecins/{id}", method = RequestMethod.PUT)
	public Medecin updateMedecin(@PathVariable("id") final Long id, @RequestBody Medecin medecin) throws ResourceNotFoundException  {
		return medecinService.updateMedecin(id, medecin);
	}
}
