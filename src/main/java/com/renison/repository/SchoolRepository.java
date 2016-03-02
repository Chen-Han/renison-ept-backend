package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.School;

@RepositoryRestResource
public interface SchoolRepository extends CrudRepository<School, Long> {
}
