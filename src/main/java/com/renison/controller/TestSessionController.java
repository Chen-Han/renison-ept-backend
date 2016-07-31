package com.renison.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View.Admin;
import com.renison.model.Category;
import com.renison.model.TestSession;

@RestController
@RequestMapping("testSessions")
public class TestSessionController extends BaseController<TestSession> {

	public TestSessionController() {
	}

	@Autowired
	private ProctorController proctorController;

	@JsonView(Admin.class)
	@RequestMapping(value = "/exam", method = RequestMethod.GET)
	// ideally `id` (test session id) should be in PathVariable but we need it
	// in query
	// string
	// for angular ngResource support :/
	public @ResponseBody Map<String, Object> getExam(@RequestParam("id") Long testSessionId) {
		Session session = sessionFactory.getCurrentSession();
		Map<String, Object> map = new HashMap<>();
		TestSession testSession = get(testSessionId);
		map.put("score", testSession.getScore());
		map.put("name", testSession.getStudent().getFullName());
		map.put("totalScore", testSession.getTest().getTotalScore());
		List<Category> categories = testSession.getTest().getCategories();
		categories.forEach((c) -> proctorController.addStudentResponse(c, testSession, session));
		map.put("categories", categories);
		return map;
	}

	@Override
	protected Class<TestSession> getResourceType() {
		return TestSession.class;
	}
}