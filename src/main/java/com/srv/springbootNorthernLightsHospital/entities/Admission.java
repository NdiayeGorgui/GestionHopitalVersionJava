package com.srv.springbootNorthernLightsHospital.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "admissions")
public class Admission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateAdmission;
	private LocalDateTime dateConge;
	private boolean chirurgieProgramme;
	private boolean televiseurDisponible;
	private boolean telephoneDisponible;
	private String nomParent;
	private String telephoneParent;
	
	@ManyToOne
	private Patient patient;	
	@ManyToOne
	private Medecin medecin;
	@ManyToOne
	private Lit lit;
	

}
