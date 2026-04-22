package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Companycategories; 

public interface CompanycategoriesRepository extends JpaRepository<Companycategories , Integer> {
    
	
	Companycategories findById(int id);

	Companycategories findByName(String name); 
   
}


 