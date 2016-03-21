package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.renison.model.QuestionResponse;

@RepositoryRestResource
public interface QuestionResponseRepository extends CrudRepository<QuestionResponse, Long> {
}
