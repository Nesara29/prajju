package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Savedpost; 

public interface SavedpostRepository extends JpaRepository<Savedpost , Integer> {
    
	
	Savedpost findById(int id);

	List<Savedpost> findByUser(int id); 

	List<Savedpost> findByPost(int id);  

	List<Savedpost> findByUserOrderByIdDesc(int id); 

	Savedpost findByUserAndPost(int id, int id2); 
   
   
}


 