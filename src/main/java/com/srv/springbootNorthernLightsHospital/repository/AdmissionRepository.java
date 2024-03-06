package com.srv.springbootNorthernLightsHospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
	
	List<Admission> findAdmissionsByMedecinId(Long id);

}
