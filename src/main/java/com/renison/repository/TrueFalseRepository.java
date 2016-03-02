package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.TrueFalse;

@RepositoryRestResource
public interface TrueFalseRepository extends CrudRepository<TrueFalse, Long> {
}
