package com.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import  com.project.jobportal.entity.Usereducation;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface UsereducationRepository extends JpaRepository<Usereducation , Integer> {
    
	
	Usereducation findById(int id);

	List<Usereducation> findByUser(int id);
	
	@Transactional
	default void deleteByUser(EntityManager em,int user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

	    // create delete
	    CriteriaDelete<Usereducation> delete = cb.
	     createCriteriaDelete(Usereducation.class);

	    // set the root class
	    Root e = delete.from(Usereducation.class);

	    // set where clause
	    delete.where(cb.equal(e.get("user"), user));

	    // perform update
	     em.createQuery(delete).executeUpdate();
	}
   
   
}


 