package com.renison.repository;

import org.springframework.data.repository.CrudRepository;

import com.renison.model.TestSession;

public interface TestSessionRepository extends CrudRepository<TestSession, Long> {
}
