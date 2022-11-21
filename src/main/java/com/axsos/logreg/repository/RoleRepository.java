package com.axsos.logreg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.logreg.models.Role;

//imports removed for brevity
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}

