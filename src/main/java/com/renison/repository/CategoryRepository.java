package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.Category;

@RepositoryRestResource(exported = true, collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
