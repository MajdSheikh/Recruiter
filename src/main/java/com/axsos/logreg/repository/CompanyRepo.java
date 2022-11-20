package com.axsos.logreg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.axsos.logreg.models.Company;


@Repository
public interface CompanyRepo extends CrudRepository<Company,Long> {
	

	Optional<Company> findById(Long id);
	
    List<Company> findAll();

}
