package com.renison.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.renison.model.Student;
import com.renison.model.Test;
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

	@RequestMapping(value = { "/start" }, method = RequestMethod.POST)
	public @ResponseBody TestSession start(@RequestBody Student student, @PathVariable Long testId) {
		Test test = testController.get(testId);
		TestSession testSession = new TestSession();
		testSession.setStudent(student);
		testSession.setTest(test);
		test.getTestSessions().add(testSession);
		repo.save(testSession);
		return testSession;
	}

	public TestController getTestController() {
		return testController;
	}

	public void setTestController(TestController testController) {
		this.testController = testController;
	}
}