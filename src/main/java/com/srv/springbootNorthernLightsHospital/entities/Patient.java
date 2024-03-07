package com.srv.springbootNorthernLightsHospital.entities;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "patients")
//@DiscriminatorValue("1")
public class Patient extends Adresse{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nss;
	private String nom;
	private String prenom;
	private LocalDateTime dateNaissance;
	private String telephone;
	@Column(unique = true)
	private String email;
	@ManyToOne
	private Assurance assurance;
	@JsonIgnore
	@OneToMany(mappedBy = "patient")
	private List<Admission> admissions;

}
