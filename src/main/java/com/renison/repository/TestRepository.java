package com.renison.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.renison.model.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
    //    @RestResource(path = "names")
    //    List<Test> findByName(String name);
    @Query("SELECT t FROM Test t WHERE t.active = TRUE")
    List<Test> getActiveTests();
}
