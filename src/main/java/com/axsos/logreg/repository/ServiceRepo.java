package com.axsos.logreg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.logreg.models.Service;



@Repository
public interface ServiceRepo extends CrudRepository<Service,Long> {
	
	
	Optional<Service> findById(Long id);
	
	List<Service> findAll();


}
