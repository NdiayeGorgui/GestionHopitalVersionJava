package com.srv.springbootNorthernLightsHospital.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true, length = 20) // username est unique
	private String userName;
	// @JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER) // eager veut dire a chaque fois qu'il charge un user il
																// charge automatiqment les users de ce role
	private List<Role> roles = new ArrayList<>(); // on initialise avec arraylist pour eviter un nullpointer au cas ou
													// on associe un role a un user qui n'a pas de role
}