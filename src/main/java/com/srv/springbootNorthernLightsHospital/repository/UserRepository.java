package com.srv.springbootNorthernLightsHospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srv.springbootNorthernLightsHospital.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);
	User findByUserId(Long userId);
	
	//@Query("select r from Role, User u where u.userId like %:numUser")
	List<User> findByRolesId(Long id);

}
