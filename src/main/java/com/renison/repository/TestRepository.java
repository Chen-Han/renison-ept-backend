package com.renison.repository;

import org.springframework.data.repository.CrudRepository;

import com.renison.model.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
}
