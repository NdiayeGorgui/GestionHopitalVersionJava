package com.srv.springbootNorthernLightsHospital.constante;

public class Constante {
	
	public static final double TARIF_PRIVE = 571;
	public static final double TARIF_SEMI_PRIVE = 267;
	public static final double TARIF_STANDARD = 0;
	public static final double TARIF_TELEVISION = 42.5;
	public static final double TARIF_TELEPHONE = 7.5;
	
	public static final String RAMQ = "RAMQ";
	public static final String PRIVE = "PRIVE";
	public static final String SEMI_PRIVE = "SEMI_PRIVE";
	public static final String STANDARD = "STANDARD";
	public static final String PEDIATRIE = "Pediatrie";
	public static final String CHIRURGIE = "Chirurgie";
	public static final String PATIENT_EXISTANT_NON_LIBERE = "Ce patient est déja admis, et non encore libéré  !";
	public static final String LIT_INDISPONIBLE = "Désolé, il n y a plus de lit de ce type disponible !";
	public static final String LIT_Occupe = "Désolé, ce lit est actuellement occupé !";
	public static final String LIT_CHOISI_NEST_PAS_DANS_LE_DEPARTEMENT = "Désolé, le lit choisi n'est pas dans le département, ou est indisponible !";
	public static final String LIT_PATIENT_MEDECIN_REQUIS = "Le Lit, le Patient et le Médecin sont requis pour faire une admission !";
	public static final String LIT_REQUIS = "Le Lit est requis pour faire une admission !";
	public static final String CHOISIR_CHAMBRE_SEMI_PRIVE = "Le Lit est requis pour faire une admission,Choisir une chambre Semi-privée sans frais supplémentaire !";
	public static final String CHOISIR_CHAMBRE_PRIVE = "Le Lit est requis pour faire une admission,Choisir une chambre privée sans frais supplémentaire !";
	public static final String FRAIS_SUPPLEMENTAIRES = "Le patient devra assumer des frais supplémentaires pour avoir choisi ce type de chambre \" +\r\n"
			+ "                        \"alors qu'il reste encore de lits";
}
