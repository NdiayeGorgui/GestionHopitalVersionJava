package com.srv.springbootNorthernLightsHospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
