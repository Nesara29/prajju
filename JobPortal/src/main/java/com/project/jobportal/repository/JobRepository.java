package com.project.jobportal.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.jobportal.CountByUser;
import  com.project.jobportal.entity.Job; 

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root; 

public interface JobRepository extends JpaRepository<Job , Integer> {
    
	
	Job findById(int id);

	List<Job> findByUser(int id); 

	List<Job> findByStatus(int id);

	long countByStatus(int i);

	List<Job> findByUserOrderByIdDesc(int id);  
	List<Job> findByStatusAndCategory(int i, int category, Pageable pageable); 
	List<Job> findByStatusOrderByIdDesc(int i);
	

	@Query("SELECT  user AS user, count(id) AS count "
			  + "FROM posts GROUP BY user ORDER BY count DESC,id asc")
	  List<CountByUser> CountByUserInterface();	
	
	
	public default List<Job> filterrecords(EntityManager em,int status,String title,int catg,String employmenttype,String experience,int company)
	{
		
		List<Job> objs=new ArrayList<>();
		try {  
		CriteriaBuilder cb =em.getCriteriaBuilder();
	    CriteriaQuery<Job> q = cb.createQuery(Job.class);
	    Root<Job> ua = q.from(Job.class);
	    List<Predicate> predicates = new ArrayList<>();
	    if(status!=0)
	    	predicates.add(cb.equal(ua.get("status"), status));
	    
	    predicates.add(cb.like(ua.get("jobtitle"), "%"+title+"%"));
	    
	    if(employmenttype.compareTo("0")!=0)
	    	predicates.add(cb.equal(ua.get("employmenttype"), employmenttype));
	    
	    if(catg!=0)
	    	predicates.add(cb.equal(ua.get("category"), catg));
	    
	    if(experience.compareTo("0")!=0)
	    	predicates.add(cb.equal(ua.get("experience"), experience));

	    if(company!=0)
	    	predicates.add(cb.equal(ua.get("user"), company));
	    
	    
	    q.select(ua).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
	    q.orderBy(cb.desc(ua.get("id")));
	    
	    
	    objs=em.createQuery(q).getResultList();
	    
		}
		catch(Exception e)
		{
			
		}
		return objs;
	}


}


 