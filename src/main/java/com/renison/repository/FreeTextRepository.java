package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.FreeText;

@RepositoryRestResource
public interface FreeTextRepository extends CrudRepository<FreeText, Long> {
}
