package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.Pencil;

@RepositoryRestResource
public interface PencilRepository extends CrudRepository<Pencil, Long> {
}
