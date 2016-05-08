package com.renison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.JOSEException;
import com.renison.auth.JwtUtil;
import com.renison.model.Student;
import com.renison.model.Test;
import com.renison.model.TestSession;
import com.renison.util.JsonUtil;

@RestController
@RequestMapping("/login")
@Transactional
public class LoginController {

	private TestController testController;
	private TestSessionController testSessionController;
	private JwtUtil jwtUtil = new JwtUtil();

	@Autowired
	public LoginController(TestController testController, TestSessionController testSessionController) {
		this.testController = testController;
		this.testSessionController = testSessionController;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ObjectNode login(@RequestBody Student student) throws JsonProcessingException, JOSEException {
		Test test = testController.getActive();
		TestSession testSession = new TestSession();
		testSession.setStudent(student);
		testSession.setTest(test);
		test.getTestSessions().add(testSession);
		testSessionController.create(testSession);
		String jwt = jwtUtil.getTestSessionJwt(testSession.getId()); // TODO
																		// catch
																		// api
																		// exception
																		// and
																		// handle
																		// somewhere
																		// else
		ObjectNode node = JsonUtil.newJsonObject();
		node.put("jwt", jwt);
		return node;
	}
}