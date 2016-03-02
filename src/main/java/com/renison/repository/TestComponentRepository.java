package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.TestComponent;

@RepositoryRestResource
public interface TestComponentRepository extends CrudRepository<TestComponent, Long> {
}
