package com.srv.springbootNorthernLightsHospital.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.srv.springbootNorthernLightsHospital.repository.UserRepository;
import com.srv.springbootNorthernLightsHospital.entities.Role;
import com.srv.springbootNorthernLightsHospital.entities.User;
import com.srv.springbootNorthernLightsHospital.repository.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor //pour faire l'injection des dépendences
public class UserService {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	//@Autowired
	//BCryptPasswordEncoder passwordEncoder;

	/*
	 * @Override public User addNewUser(User user) { //todo hacher le pwd
	 * //user.setUserId(UUID.randomUUID().toString());
	 * user.setPassword(passwordEncoder.encode(user.getPassword())); return
	 * userRepository.save(user); }
	 */


	public Role addNewRole(Role role) {
		return roleRepository.save(role);
	}


	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	
	public Role findRoleByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	
	public void addRoleToUser(String userName, String roleName) {
		User user=findUserByUserName(userName);
		Role role=findRoleByRoleName(roleName);
		if(user.getRoles()!=null) {
			user.getRoles().add(role); //on va aller vers la collection des roles de l'utilisateur et on ajoute le role
			//userRepository.save(user); // c'est pas nécessaire car on a utiliser @Transactional, ya commit a la fin de la transaction
		}
		if(role.getUsers()!=null) {
			role.getUsers().add(user);  //vice versa
		}
		
	}


	public User userAuthenticate(String userName, String password) {
		User user=userRepository.findByUserName(userName);
		if(user!=null  && user.getPassword().equals(password)) {
			return user;
		}
		throw new RuntimeException("Bad credential");
	}


	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	
	public List<Role> getAllRoles() {
		
		return roleRepository.findAll();
	}

	

	
	public void deleteRoleToUser(String userName, String roleName) {
		User user=findUserByUserName(userName);
		Role role=findRoleByRoleName(roleName);
		if(user.getRoles()!=null) {
			user.getRoles().remove(role); 
			
		}
		if(role.getUsers()!=null) {
			role.getUsers().remove(user);  
		}
	}

	
	/*public void  saveUserWihtRole(User user, String roleName) {
		User usr=addNewUser(user);
		Role role=findRoleByRoleName(roleName);
		if(usr.getRoles()!=null) {
			usr.getRoles().add(role); 
		}
		if(role.getUsers()!=null) {
			role.getUsers().add(usr);  
		}
					
	}*/

	


	public void deleteRoleById(Long id) {
		roleRepository.deleteById(id);
		
	}

	
	public Optional<Role> getRole(Long id) {
		return roleRepository.findById(id);
	}

	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	
	public Optional<User> getUserByUserName(String userName) {
		
		return Optional.ofNullable(userRepository.findByUserName(userName));
	}

	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
		
	}

	
	public Optional<User> getUser(Long id) {
		
		return userRepository.findById(id);
	}

	
	public List<User> findByRolesId(Long id) {
		
		return userRepository.findByRolesId(id);
	}

	
	public List<Role> findByUsersUserId(Long userId) {
	
		return roleRepository.findByUsersUserId(userId);
	}

	
	public List<Role> findByUsersUserName(String userName) {
		
		return roleRepository.findByUsersUserName(userName);
	}
}
