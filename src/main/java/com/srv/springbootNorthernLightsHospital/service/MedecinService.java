package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srv.springbootNorthernLightsHospital.entities.Admission;
import com.srv.springbootNorthernLightsHospital.entities.Medecin;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.AdmissionRepository;
import com.srv.springbootNorthernLightsHospital.repository.MedecinRepository;

@Service
public class MedecinService {
	@Autowired
	MedecinRepository medecinRepository;
	@Autowired
	AdmissionService admissionService;
	@Autowired
	AdmissionRepository admissionRepository;
	

	public Medecin saveMedecin(Medecin m) {

		return medecinRepository.save(m);
	}

	public Optional<Medecin> getMedecin(Long id) {

		return medecinRepository.findById(id);
	}

	public List<Medecin> getAllMedecins() {

		return medecinRepository.findAll();
	}

	public void deleteMedecinById(Long id) {
		
		List<Admission> admissions=admissionRepository.findAdmissionsByMedecinId(id);
		
		if(!admissions.isEmpty() || admissions!=null) {
			admissionRepository.deleteAll(admissions);
		}
		
		medecinRepository.deleteById(id);

	}

	public Medecin updateMedecin(Long id, Medecin medecin) throws ResourceNotFoundException {
		Optional<Medecin> m = medecinRepository.findById(id);
		if (m.isPresent()) {
			Medecin currentMedecin = m.get();

			String nom = medecin.getNom();
			if (nom != null) {
				currentMedecin.setNom(nom);
			}
			String prenom = medecin.getPrenom();
			if (prenom != null) {
				currentMedecin.setPrenom(prenom);
			}

			String specialite = medecin.getSpecialite();
			if (specialite != null) {
				currentMedecin.setSpecialite(specialite);
			}

			String telephone = medecin.getTelephone();
			if (telephone != null) {
				currentMedecin.setTelephone(telephone);
			}

			String email = medecin.getEmail();
			if (email != null) {
				currentMedecin.setEmail(email);
			}

			String rue = medecin.getRue();
			if (rue != null) {
				currentMedecin.setRue(rue);
			}
			String numApp = medecin.getNumeroApp();
			if (numApp != null) {
				currentMedecin.setNumeroApp(numApp);
			}
			String ville = medecin.getVille();
			if (ville != null) {
				currentMedecin.setVille(ville);
			}
			
			String prov = medecin.getProvince();
			if (prov != null) {
				currentMedecin.setProvince(prov);
			}
			String codePost = medecin.getCodePostal();
			if (codePost != null) {
				currentMedecin.setCodePostal(codePost);
			}

			this.saveMedecin(currentMedecin);
			return currentMedecin;
		} else {
			throw new ResourceNotFoundException("Le Medecin avec l'id " + id + " n'existe pas");
		}

	}
	
	public void replaceMedecin(Long idMedecinToAdd, Long idMedecinToRemove) {
		
		Medecin medecinToAdd=medecinRepository.findById(idMedecinToAdd).get();

		List<Admission> admissions=admissionRepository.findAdmissionsByMedecinId(idMedecinToRemove);
		
		if(!admissions.isEmpty() || admissions!=null) {
			for(Admission admission:admissions) {
				admission.setMedecin(medecinToAdd);
			}
		}
		
		admissionRepository.saveAll(admissions);
		medecinRepository.deleteById(idMedecinToRemove);
	}
}
