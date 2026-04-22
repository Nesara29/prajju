package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Usercompany; 

public interface UsercompanyRepository extends JpaRepository<Usercompany , Integer> {
    
	
	Usercompany findById(int id);

	Usercompany findByUser(int id);  
   
   
}


 