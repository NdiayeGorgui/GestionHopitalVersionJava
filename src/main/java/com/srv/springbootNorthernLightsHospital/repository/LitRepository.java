package com.srv.springbootNorthernLightsHospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.Lit;

public interface LitRepository extends JpaRepository<Lit, Long>{
	
	List<Lit> findByChambreDescription(String desc);
	List<Lit> findByChambreDescriptionAndIsOccupe(String desc,boolean oc);

}
