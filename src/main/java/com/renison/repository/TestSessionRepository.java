package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.renison.model.TestSession;

@RepositoryRestResource
public interface TestSessionRepository extends CrudRepository<TestSession, Long> {
}
