package com.srv.springbootNorthernLightsHospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srv.springbootNorthernLightsHospital.entities.Patient;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@RequestMapping(value = "Patients", method = RequestMethod.POST)
	public Patient createPatient(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}
	
	@RequestMapping(value = "Patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Patient> patient = patientService.getPatient(id);
		if (patient.isPresent()) {
			return patient.get();
		} else {

			throw new ResourceNotFoundException("Le Patient avec l'id " + id + " n'existe pas");

		}
	}
	
	@RequestMapping(value = "Patients", method = RequestMethod.GET)
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}
	
	@RequestMapping(value = "Patients/{id}", method = RequestMethod.DELETE)
	public void deletePatient(@PathVariable("id") Long id) {
		
		patientService.deletePatientById(id);
	}
	
	@RequestMapping(value = "/Patients/{id}", method = RequestMethod.PUT)
	public Patient updatePatient(@PathVariable("id") final Long id, @RequestBody Patient patient) throws ResourceNotFoundException  {
		return patientService.updatePatient(id, patient);
	}

}
