package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srv.springbootNorthernLightsHospital.entities.Departement;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.DepartementRepository;


@Service
public class DepartementService {
	@Autowired
	DepartementRepository departementRepository;

	public Departement saveDepartement(Departement d) {

		return departementRepository.save(d);
	}

	public Optional<Departement> getDepartement(Long id) {

		return departementRepository.findById(id);
	}

	public List<Departement> getAllDepartements() {

		return departementRepository.findAll();
	}

	public void deleteDepartementById(Long id) {
		departementRepository.deleteById(id);

	}

	public Departement updateDepartement(Long id, Departement departement) throws ResourceNotFoundException {
		Optional<Departement> d = departementRepository.findById(id);
		if (d.isPresent()) {
			Departement currentDepartement = d.get();

			String nomDep = departement.getNomDepartement();
			if (nomDep != null) {
				currentDepartement.setNomDepartement(nomDep);
			}
			
			this.saveDepartement(currentDepartement);
			return currentDepartement;
		} else {
			throw new ResourceNotFoundException("Le Departement avec l'id " + id + " n'existe pas");
		}

	}
}
