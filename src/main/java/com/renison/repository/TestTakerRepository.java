package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.TestTaker;

@RepositoryRestResource
public interface TestTakerRepository extends CrudRepository<TestTaker, Long> {
}
