package com.srv.springbootNorthernLightsHospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByRoleName(String roleName);
	 List<Role>findByUsersUserId(Long userId);
	 List<Role>findByUsersUserName(String userName);

}
