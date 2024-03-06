package com.srv.springbootNorthernLightsHospital.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srv.springbootNorthernLightsHospital.entities.Assurance;

import com.srv.springbootNorthernLightsHospital.entities.Patient;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.PatientRepository;
@Service
public class PatientService {
	@Autowired
	PatientRepository patientRepository;

	public Patient savePatient(Patient p) {
		
		//String str = "2010-04-08 12:30";
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		//p.setDateNaissance(dateTime);

		return patientRepository.save(p);
	}

	public Optional<Patient> getPatient(Long id) {

		return patientRepository.findById(id);
	}

	public List<Patient> getAllPatients() {

		return patientRepository.findAll();
	}

	public void deletePatientById(Long id) {
		patientRepository.deleteById(id);

	}

	public Patient updatePatient(Long id, Patient patient) throws ResourceNotFoundException {
		Optional<Patient> p = patientRepository.findById(id);
		if (p.isPresent()) {
			Patient currentPatient = p.get();
			
			String nss = patient.getNss();
			if (nss != null) {
				currentPatient.setNss(nss);
			}

			String nom = patient.getNom();
			if (nom != null) {
				currentPatient.setNom(nom);
			}
			String prenom = patient.getPrenom();
			if (prenom != null) {
				currentPatient.setPrenom(prenom);
			}
			LocalDateTime dateNaiss = patient.getDateNaissance();
			if (dateNaiss != null) {
				currentPatient.setDateNaissance(dateNaiss);
			}
			
			String telephone = patient.getTelephone();
			if (telephone != null) {
				currentPatient.setTelephone(telephone);
			}

			String email = patient.getEmail();
			if (email != null) {
				currentPatient.setEmail(email);
			}

			String rue = patient.getRue();
			if (rue != null) {
				currentPatient.setRue(rue);
			}
			String numApp = patient.getNumeroApp();
			if (numApp != null) {
				currentPatient.setNumeroApp(numApp);
			}
			String ville = patient.getVille();
			if (ville != null) {
				currentPatient.setVille(ville);
			}
			
			String prov = patient.getProvince();
			if (prov != null) {
				currentPatient.setProvince(prov);
			}
			String codePost = patient.getCodePostal();
			if (codePost != null) {
				currentPatient.setCodePostal(codePost);
			}

			Assurance ass = patient.getAssurance();
			if (ass != null) {
				currentPatient.setAssurance(ass);
			}

			this.savePatient(currentPatient);
			return currentPatient;
		} else {
			throw new ResourceNotFoundException("Le Patient avec l'id " + id + " n'existe pas");
		}

	}
	
 public boolean patientExiste(String nss){
		 return false;
	 }
}
