package com.srv.springbootNorthernLightsHospital.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.srv.springbootNorthernLightsHospital.constante.Constante;
import com.srv.springbootNorthernLightsHospital.entities.Admission;
import com.srv.springbootNorthernLightsHospital.entities.Lit;
import com.srv.springbootNorthernLightsHospital.entities.Patient;
import com.srv.springbootNorthernLightsHospital.exception.ResourceDuplicatedException;
import com.srv.springbootNorthernLightsHospital.exception.ResourceNotFoundException;
import com.srv.springbootNorthernLightsHospital.repository.AdmissionRepository;

@Service
public class AdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;
	@Autowired
	LitService litService;

	@SuppressWarnings("unlikely-arg-type")
	public Admission saveAdmission(Admission a) throws ResourceDuplicatedException, ResourceNotFoundException {
		LocalDateTime today = LocalDateTime.now();

		a.setDateAdmission(today);
		String typeAssurance=null;
		String typeLit =null;
		String departement =null;
		
		if (a.getPatient() != null) {
			 typeAssurance = a.getPatient().getAssurance().getNomAssurance();
		}
		
		
		if (a.getLit() != null) {
			 typeLit = a.getLit().getChambre().getDescription();
			 departement = a.getLit().getDepartement().getNomDepartement();
		}
		
		
		if (a.getLit() == null || a.getMedecin()==null  || a.getPatient()==null) {
			throw new ResourceNotFoundException(Constante.LIT_PATIENT_MEDECIN_REQUIS);
		}
		if (patientDejaAdmis(a.getPatient())) {

			throw new ResourceDuplicatedException(Constante.PATIENT_EXISTANT_NON_LIBERE);
		} else {
			
			
			if(this.getLitStatusNotAvailable(a)==true) {
				throw new ResourceNotFoundException(Constante.LIT_Occupe);
			}
			
			// si le patient n'est pas couvert par une assurance privé
			if (typeAssurance.equalsIgnoreCase(Constante.RAMQ)) {
				// si ya plus de lit disponible dans les chambres standards
				if (typeLit.equalsIgnoreCase(Constante.STANDARD)) {
					if ((this.litService.emptyAvailableLitByType(typeLit))) {
						throw new ResourceNotFoundException(Constante.CHOISIR_CHAMBRE_SEMI_PRIVE);
					}
				}
				// si ya plus de lit disponible dans les chambres semi-privé
				else if (typeLit.equalsIgnoreCase(Constante.SEMI_PRIVE)) {
					if ((this.litService.emptyAvailableLitByType(typeLit))) {
						throw new ResourceNotFoundException(Constante.CHOISIR_CHAMBRE_PRIVE);
					}
				}
				// si ya plus de lit disponible dans les chambres privé
				else if (typeLit.equalsIgnoreCase(Constante.PRIVE)) {
					if ((this.litService.emptyAvailableLitByType(typeLit))) {
						throw new ResourceNotFoundException(Constante.LIT_INDISPONIBLE);
					}
				}
				
			}

			if (patientAMoinsDeSeizeAns(a) == true && a.isChirurgieProgramme() == false) {
				affectationLit(a, Constante.PEDIATRIE, 1L, typeLit);
			}

			if (a.isChirurgieProgramme() == true) {
				affectationLit(a, Constante.CHIRURGIE, 5L, typeLit);
			} else {
				affectationLitAutreDepartement(a, departement, typeLit);
			}	

			/*if (a.getLit() != null) {
				a.getLit().setOccupe(true);
				a.getLit().setChambre(a.getLit().getChambre());
				a.getLit().setDepartement(a.getLit().getDepartement());

				litService.saveLit(a.getLit());
			} else {
				throw new ResourceNotFoundException(Constante.LIT_REQUIS);
			}*/
			return admissionRepository.save(a);
		}

	}
	
	public void affectationLit(Admission a, String dept, Long id,String typeLit) throws ResourceNotFoundException {
		if (!litService.getAllLitsAvailableByDeptAndType(dept, typeLit).isEmpty()) {
			if (litExiteDansLeDepartement(a.getLit(), dept, typeLit)) {
				if (a.getLit() != null) {
					a.getLit().setOccupe(true);
					a.getLit().getDepartement().setId(id);
					a.getLit().getDepartement().setNomDepartement(dept);
					litService.saveLit(a.getLit());
				}else {
					throw new ResourceNotFoundException(Constante.LIT_REQUIS);
				}
				
			} else {
				throw new ResourceNotFoundException(Constante.LIT_CHOISI_NEST_PAS_DANS_LE_DEPARTEMENT);
			}
		}
	}
	
	public void affectationLitAutreDepartement(Admission a, String dept,String typeLit) throws ResourceNotFoundException {
		if (!litService.getAllLitsAvailableByDeptAndType(dept, typeLit).isEmpty() && patientAMoinsDeSeizeAns(a) == false) {
			if (litExiteDansLeDepartement(a.getLit(), dept, typeLit)) {
				if (a.getLit() != null) {
					a.getLit().setOccupe(true);
					a.getLit().setChambre(a.getLit().getChambre());
					a.getLit().setDepartement(a.getLit().getDepartement());
					litService.saveLit(a.getLit());
				} else {
					throw new ResourceNotFoundException(Constante.LIT_REQUIS);
				}
			} else {
				throw new ResourceNotFoundException(Constante.LIT_CHOISI_NEST_PAS_DANS_LE_DEPARTEMENT);
			}
		}
	}

	public boolean litExiteDansLeDepartement(Lit monLit, String departement, String typeLit) {

		List<Lit> maList = litService.getAllLitsAvailableByDeptAndType(departement, typeLit);

		List<Long> myIds = maList.stream().map(x -> x.getId()).collect(Collectors.toList());

		if (myIds.contains(monLit.getId())) {
			return true;
		}

		return false;
	}

	public List<Admission> getAllAdmissions() {

		return admissionRepository.findAll();
	}

	private boolean patientDejaAdmis(Patient p) {

		List<Admission> admiss = this.getAllAdmissions();
		for (Admission ad : admiss) {
			if (ad.getDateConge() == null && ad.getPatient().getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	public void deleteAdmissionById(Long id) {
		admissionRepository.deleteById(id);

	}

	public boolean rechercheTypeChambre(String typeLit) {

		for (Lit l : litService.getAllLits()) {
			if (l.getChambre().getDescription().equalsIgnoreCase(typeLit) && l.isOccupe() == false) {
				return true;
			}
		}

		return false;
	}

	public boolean patientAMoinsDeSeizeAns(Admission a) {
    long differenceInDays=ChronoUnit.YEARS.between(a.getPatient().getDateNaissance(), a.getDateAdmission());
    long seizeAns =  16;
		if (differenceInDays <= seizeAns) {
			return true;
		}
		return false;
	}
	
	public boolean getLitStatusNotAvailable(Admission a) {
		
		List<Lit> litNotAvalables=litService.getAllLitsNotAvailable();
		
		for(Lit lit:litNotAvalables) {
			if(a.getLit().getId()==lit.getId()) {
				if(lit.isOccupe()==true)
				return true;
			}	
		}
		return false;
	}

	public long nombreDeJourAdmis(Admission a) {
		LocalDateTime jourDebut = a.getDateAdmission();
		LocalDateTime jourFin = LocalDateTime.now();

		long differenceInDays = ChronoUnit.DAYS.between(jourDebut, jourFin);
		return differenceInDays;
	}

	public double tarification(Admission a) {

		double valeur = 0.0;

		// si le patient a une assurance privée
		if (!a.getPatient().getAssurance().getNomAssurance().equalsIgnoreCase(Constante.RAMQ)) {
			// si le patient choisit une chambre (lit) privée
			if (a.getLit().getChambre().getDescription().equalsIgnoreCase(Constante.PRIVE)) {
				valeur =calculFacturation(a, Constante.TARIF_PRIVE, valeur);

				// si le patient choisit une chambre (lit) Semi-privée
			} else if (a.getLit().getChambre().getDescription().equalsIgnoreCase(Constante.SEMI_PRIVE)) {
				valeur =calculFacturation(a, Constante.TARIF_SEMI_PRIVE, valeur);
			}
			// si le patient choisit une chambre (lit) Standard
			else {
				valeur =calculFacturation(a, Constante.TARIF_STANDARD, valeur);
			}
		}
		// si le patient n'a pas une assurance privée
		else {
			// si un type de chambre standard était disponible au moment du choix de chambre
			// privée ou semi-prvée
			// il paye des frais supplémentaires.
			if (rechercheTypeChambre(a.getLit().getChambre().getDescription()) == true) {
				// si le patient choisit un chambre (lit) Privée

				if (a.getLit().getChambre().getDescription().equalsIgnoreCase(Constante.PRIVE)) {

					valeur =calculFacturation(a, Constante.TARIF_PRIVE, valeur);
				}
				// si le patient choisit une chambre (lit) Semi-privée
				if (a.getLit().getChambre().getDescription().equalsIgnoreCase(Constante.SEMI_PRIVE)) {
					valeur =calculFacturation(a, Constante.TARIF_SEMI_PRIVE, valeur);
				} else {
					// sinon si le patient choisit une chambre (lit) Standard
					valeur =calculFacturation(a, Constante.TARIF_STANDARD, valeur);
				}
			}
		}
		return valeur * nombreDeJourAdmis(a);
	}

	
	public double calculFacturation(Admission a, double tarifChambre, double valeur) {
		if (a.isTelephoneDisponible() == true && a.isTeleviseurDisponible() == true) {
			valeur = tarifChambre + Constante.TARIF_TELEPHONE + Constante.TARIF_TELEVISION;
		} else if (a.isTelephoneDisponible() == true && a.isTeleviseurDisponible() == false) {
			valeur = tarifChambre + Constante.TARIF_TELEPHONE;
		} else if (a.isTelephoneDisponible() == false && a.isTeleviseurDisponible() == true) {
			valeur = tarifChambre + Constante.TARIF_TELEVISION;
		} else {
			valeur = tarifChambre;
		}
		return valeur;
	}
	 

	public String donnerConge(Long id, Admission a) throws ResourceNotFoundException, ResourceDuplicatedException {
		Optional<Admission> ad = admissionRepository.findById(id);
		if (ad.isPresent()) {
			Admission currentAdmission = ad.get();

			LocalDateTime today = LocalDateTime.now();
			Lit lit = a.getLit();
			currentAdmission.setDateConge(today);
			if (lit != null) {
				currentAdmission.setDateConge(today);
				currentAdmission.getLit().setOccupe(false);
				litService.saveLit(currentAdmission.getLit());
				admissionRepository.save(currentAdmission);
			}
			return "Congé donné avec succés **** Tarification : " + tarification(currentAdmission) + " $";
		} else {
			throw new ResourceNotFoundException("L'Admission avec l'id " + id + " n'existe pas");
		}
	}

	public Optional<Admission> getAdmission(Long id) {
		return admissionRepository.findById(id);
	}
}
