package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.project.jobportal.entity.Jobapplication; 

public interface JobapplicationRepository extends JpaRepository<Jobapplication , Integer> {
    
	
	Jobapplication findById(int id);

	List<Jobapplication> findByUser(int id); 

	List<Jobapplication> findByPost(int id); 

	List<Jobapplication> findByStatus(int id);

	long countByStatus(int i);

	List<Jobapplication> findByUserOrderByIdDesc(int id);

	long countByPost(int id);

	Jobapplication findByUserAndPost(int id, int id2);

	List<Jobapplication> findByPostAndStatus(int id, int i); 
   
   
}


 