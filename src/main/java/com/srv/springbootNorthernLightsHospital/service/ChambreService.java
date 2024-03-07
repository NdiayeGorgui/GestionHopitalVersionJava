package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srv.springbootNorthernLightsHospital.entities.Chambre;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.ChambreRepository;

@Service
public class ChambreService {
	@Autowired
	ChambreRepository chambreRepository;

	public Chambre saveChambre(Chambre c) {

		return chambreRepository.save(c);
	}

	public Optional<Chambre> getChambre(Long id) {

		return chambreRepository.findById(id);
	}

	public List<Chambre> getAllChambres() {

		return chambreRepository.findAll();
	}

	public void deleteChambreById(Long id) {
		chambreRepository.deleteById(id);

	}

	public Chambre updateChambre(Long id, Chambre chambre) throws ResourceNotFoundException {
		Optional<Chambre>t = chambreRepository.findById(id);
		if (t.isPresent()) {
			Chambre currentChambre = t.get();

			
			
			String type = chambre.getDescription();
			if (type != null) {
				currentChambre.setDescription(type);
			}

			this.saveChambre(currentChambre);
			return currentChambre;
		} else {
			throw new ResourceNotFoundException("La Chambre avec l'id " + id + " n'existe pas");
		}

	}
	
	public List<Chambre> getAllChambreByType(String typeChambre) {

		List<Chambre> listTypeChambre= this.getAllChambres().stream()
				.filter(chambre->chambre.getDescription().equalsIgnoreCase(typeChambre))
				.collect(Collectors.toList());
		return listTypeChambre;

		
	}
}
