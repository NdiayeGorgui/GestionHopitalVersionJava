package com.srv.springbootNorthernLightsHospital.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Table(name = "adresses")
@MappedSuperclass
public class Adresse {
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;
	private String rue;
	private String numeroApp;
	private String ville;
	private String province;
	private String codePostal;
	

}
