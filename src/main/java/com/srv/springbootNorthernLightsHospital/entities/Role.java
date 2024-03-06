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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 20)
	@NotNull
	private String roleName;
	@ManyToMany(fetch = FetchType.EAGER)
	// @JoinTable(name="users_roles")
	@ToString.Exclude // on veut pas afficher la liste des users quand on utilise la methode
						// toString()pour afficher les roles
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<User> users = new ArrayList<>();
}
