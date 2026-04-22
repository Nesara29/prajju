package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobportal.LoginTypes;
import com.project.jobportal.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findById(int id); 
	Users findByEmail(String un);
	Users findByMobile(String mobileno);
	List<Users> findByTypeOrderByNameAsc(String type);
	long countByStatus(int i);
	long countByTypeAndStatus(String name, int i); 
}
