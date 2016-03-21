package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.renison.model.Question;

@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
