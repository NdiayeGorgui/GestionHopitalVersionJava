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
import com.srv.springbootNorthernLightsHospital.entities.Admission;
import com.srv.springbootNorthernLightsHospital.exception.ResourceDuplicatedException;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.AdmissionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdmissionController {
@Autowired
AdmissionService admissionService;

@RequestMapping(value = "Admissions", method = RequestMethod.POST)
public Admission createAdmission(@RequestBody Admission admission) throws ResourceDuplicatedException, ResourceNotFoundException {
	return admissionService.saveAdmission(admission);
}

@RequestMapping(value = "Admissions", method = RequestMethod.GET)
public List<Admission> getAllAdmissions() {
	return admissionService.getAllAdmissions();
}

@RequestMapping(value = "Admissions/{id}", method = RequestMethod.DELETE)
public void deleteAdmission(@PathVariable("id") Long id) {
	
	admissionService.deleteAdmissionById(id);
}
@RequestMapping(value = "Admissions/{id}", method = RequestMethod.GET)
public Admission getAdmission(@PathVariable("id") final Long id) throws ResourceNotFoundException {
	Optional<Admission> admission = admissionService.getAdmission(id);
	if (admission.isPresent()) {
		return admission.get();
	} else {

		throw new ResourceNotFoundException("L'admission avec l'id " + id + " n'existe pas");

	}
}
@RequestMapping(value = "/Admissions/conge/{id}", method = RequestMethod.PUT)
public String donnerConge(@PathVariable("id") final Long id, @RequestBody Admission admission) throws ResourceNotFoundException, ResourceDuplicatedException  {
	return admissionService.donnerConge(id, admission);
}
}
