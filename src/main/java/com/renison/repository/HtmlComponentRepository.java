package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.renison.model.HtmlComponent;

@RepositoryRestResource
public interface HtmlComponentRepository extends CrudRepository<HtmlComponent, Long> {
}
