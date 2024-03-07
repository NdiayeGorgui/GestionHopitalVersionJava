package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srv.springbootNorthernLightsHospital.entities.Assurance;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.AssuranceRepository;


@Service
public class AssuranceService {
	
	@Autowired
	AssuranceRepository assuranceRepository;

	public Assurance saveAssurance(Assurance a) {

		return assuranceRepository.save(a);
	}

	public Optional<Assurance> getAssurance(Long id) {

		return assuranceRepository.findById(id);
	}

	public List<Assurance> getAllAssurances() {

		return assuranceRepository.findAll();
	}

	public void deleteAssuranceById(Long id) {
		assuranceRepository.deleteById(id);

	}

	public Assurance updateAssurance(Long id, Assurance assurance) throws ResourceNotFoundException {
		Optional<Assurance>a = assuranceRepository.findById(id);
		if (a.isPresent()) {
			Assurance currentAssurance =a.get();

			
			
			String nomass = assurance.getNomAssurance();
			if (nomass != null) {
				currentAssurance.setNomAssurance(nomass);
			}
			
			String type = assurance.getTypeAssurance();
			if (type != null) {
				currentAssurance.setTypeAssurance(type);
			}

			this.saveAssurance(currentAssurance);
			return currentAssurance;
		} else {
			throw new ResourceNotFoundException("L'Assurance avec l'id " + id + " n'existe pas");
		}

	}

}
