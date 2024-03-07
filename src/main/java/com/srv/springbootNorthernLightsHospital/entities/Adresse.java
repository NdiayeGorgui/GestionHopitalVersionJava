package com.srv.springbootNorthernLightsHospital.entities;



import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@MappedSuperclass
public class Adresse {
	
	private String rue;
	private String numeroApp;
	private String ville;
	private String province;
	private String codePostal;
	
}
