package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import  com.project.jobportal.entity.Userexperience;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface UserexperienceRepository extends JpaRepository<Userexperience , Integer> {
    
	
	Userexperience findById(int id);

	List<Userexperience> findByUser(int id);
	
	@Transactional
	default void deleteByUser(EntityManager em,int user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

	    // create delete
	    CriteriaDelete<Userexperience> delete = cb.
	     createCriteriaDelete(Userexperience.class);

	    // set the root class
	    Root e = delete.from(Userexperience.class);

	    // set where clause
	    delete.where(cb.equal(e.get("user"), user));

	    // perform update
	     em.createQuery(delete).executeUpdate();
	}
   
   
}


 