package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Jobcategories; 

public interface JobcategoriesRepository extends JpaRepository<Jobcategories , Integer> {
    
	
	Jobcategories findById(int id);

	Jobcategories findByName(String name); 
   
}


 