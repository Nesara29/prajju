package com.project.jobportal.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
 
import  com.project.jobportal.entity.Chatroom; 
 
 
public interface ChatroomRepository extends JpaRepository<Chatroom , Integer> {
    
	 

	Chatroom findByUser(int id);

	List<Chatroom> findByStatusOrderByUpdatedatAsc(int i); 
	
	
	
	

}


 