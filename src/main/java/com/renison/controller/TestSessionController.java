package com.renison.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renison.model.TestSession;

@RestController
@RequestMapping("tests/{testId}/testSessions")
public class TestSessionController extends BaseController<TestSession> {

	public TestSessionController() {
	}

	@Override
	protected Class<TestSession> getResourceType() {
		return TestSession.class;
	}
}