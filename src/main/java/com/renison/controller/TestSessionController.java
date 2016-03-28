package com.renison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renison.model.TestSession;
import com.renison.repository.TestSessionRepository;

@RestController
@RequestMapping("tests/{testId}/testSessions")
public class TestSessionController extends BaseController<TestSession> {

    private TestSessionRepository repo;
    @Autowired
    private TestController testController;

    @Autowired
    public TestSessionController(TestSessionRepository repo) {
        super(repo);
        this.repo = repo;
    }

    public TestController getTestController() {
        return testController;
    }

    public void setTestController(TestController testController) {
        this.testController = testController;
    }

}