package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Userjobseeker; 

public interface UserjobseekerRepository extends JpaRepository<Userjobseeker , Integer> {
    
	
	Userjobseeker findById(int id);

	 Userjobseeker  findByUser(int id);  
   
   
}


 