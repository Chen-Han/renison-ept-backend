package com.renison.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import com.renison.model.TestSession;

public interface TestSessionRepository extends CrudRepository<TestSession, Long> {
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#save(S)
     * only admin allowed to save
     */
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    TestSession save(TestSession testSession);
}
