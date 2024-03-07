package com.srv.springbootNorthernLightsHospital.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
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
@Table(name = "medecins")
//@DiscriminatorValue("2")
public class Medecin extends Adresse{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String specialite;
	@Email(message = "L'adresse Email saisie est invalide")
	@Column(unique = true)
	private String email;
	@Length(min = 10, max = 25, message = "Nombre de caracteres entre 10 et 25")
	private String telephone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medecin")
	private List<Admission> admissions;

}
