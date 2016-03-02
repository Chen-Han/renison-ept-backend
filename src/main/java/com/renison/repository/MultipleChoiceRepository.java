package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.MultipleChoice;

@RepositoryRestResource
public interface MultipleChoiceRepository extends CrudRepository<MultipleChoice, Long> {
}
