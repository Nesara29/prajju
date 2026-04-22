package com.project.jobportal.service;
 
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.project.jobportal.entity.Users;

public interface LoginsService {
    void saveUser(Users user);

    Users findByEmail(String un);

    List<Users> findAllUsers();

	boolean checkIfValidOldPassword(Users user, String password);

	void changeUserPassword(Users user, String newpassword);

	boolean validation_Password(String password);


	Collection<? extends GrantedAuthority> getAuthorities(String role);
}
