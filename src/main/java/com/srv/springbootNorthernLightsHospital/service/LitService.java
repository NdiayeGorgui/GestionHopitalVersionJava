package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srv.springbootNorthernLightsHospital.entities.Departement;
import com.srv.springbootNorthernLightsHospital.entities.Lit;
import com.srv.springbootNorthernLightsHospital.entities.Chambre;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.LitRepository;


@Service
public class LitService {
	@Autowired
	LitRepository litRepository;

	public Lit saveLit(Lit l) {

		return litRepository.save(l);
	}

	public Optional<Lit> getLit(Long id) {

		return litRepository.findById(id);
	}

	public List<Lit> getAllLits() {

		return litRepository.findAll();
	}

	public void deleteLitById(Long id) {
		litRepository.deleteById(id);

	}

	public Lit updateLit(Long id, Lit lit) throws ResourceNotFoundException {
		Optional<Lit> l = litRepository.findById(id);
		if (l.isPresent()) {
			Lit currentLit = l.get();

			boolean occup = lit.isOccupe();
			//if (occup) {
				currentLit.setOccupe(occup);
		//	}
			
			Chambre chambre = lit.getChambre();
			if (chambre != null) {
				currentLit.setChambre(chambre);
			}

			Departement dept = lit.getDepartement();
			if (dept != null) {
				currentLit.setDepartement(dept);
			}

			this.saveLit(currentLit);
			return currentLit;
		} else {
			throw new ResourceNotFoundException("Le Lit avec l'id " + id + " n'existe pas");
		}

	}
	
	/*public List<Lit> getAllLitsByType(String typeLit) {

		List<Lit> listTypeLit= this.getAllLits().stream()
				.filter(lit->lit.getChambre().getDescription().equalsIgnoreCase(typeLit)).collect(Collectors.toList());
		return listTypeLit;

		
	}*/
	
	
	public List<Lit> getAllLitsByType(String typeLit) {

		List<Lit> listTypeLit= litRepository.findByChambreDescription(typeLit);
		return listTypeLit;

		
	}
	
	/*public List<Lit> getAllLitsAvailableByType(String typeLit) {

		List<Lit> listTypeLit= this.getAllLits().stream()
				.filter(lit->lit.isOccupe())
				.filter(lit->lit.getChambre().getDescription().equalsIgnoreCase(typeLit)).collect(Collectors.toList());
				
		return listTypeLit;

		
	}*/
	public List<Lit> getAllLitsAvailableByDept(String dept) {

		List<Lit> listTypeLit= this.getAllLits().stream()
				.filter(lit->lit.isOccupe()==false)
				.filter(lit->lit.getDepartement().getNomDepartement().equalsIgnoreCase(dept))
				.collect(Collectors.toList());
				
		return listTypeLit;	
	}
	
	public List<Lit> getAllLitsAvailableByDeptAndType(String dept,String typeLit) {

		List<Lit> listTypeLit= this.getAllLits().stream()
				.filter(lit->lit.isOccupe()==false)
				.filter(lit->lit.getChambre().getDescription().equalsIgnoreCase(typeLit))
				.filter(lit->lit.getDepartement().getNomDepartement().equalsIgnoreCase(dept))
				.collect(Collectors.toList());
				
		return listTypeLit;	
	}
	
	public List<Lit> getAllAvailableLitsByType(String typeLit) {

		List<Lit> listTypeLit= litRepository.findByChambreDescriptionAndIsOccupe(typeLit, false);
		
			return listTypeLit;

		
	}
	
	
	public List<Lit> getAllLitsNotAvailable() {

		List<Lit> listTypeLit= this.getAllLits().stream()
				.filter(lit->lit.isOccupe()==true)				
				.collect(Collectors.toList());
				
		return listTypeLit;	
	}
	
	public boolean emptyAvailableLitByType(String typeLit) {

		List<Lit> listTypeLit= litRepository.findByChambreDescriptionAndIsOccupe(typeLit, false);
		if(listTypeLit.isEmpty()) {
			return true;
		}
			return false;

		
	}
}
