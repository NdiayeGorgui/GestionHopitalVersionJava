package com.srv.springbootNorthernLightsHospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long>{
	Medecin  findByEmail(String email);

}
