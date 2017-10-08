package com.renison.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.exception.BadRequestException;
import com.renison.jackson.View.Admin;
import com.renison.model.Category;
import com.renison.model.Progress;
import com.renison.model.Test;
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

	@JsonView(Admin.class)
	@RequestMapping(value = "/{id}/setProgress", method = RequestMethod.POST)
	@Transactional
	public Map<String, Long> setProgress(@PathVariable("id") Long testSessionId,
			@RequestBody Map<String, Long> progressInfo) {
		TestSession testSession = get(testSessionId);
		Session session = sessionFactory.getCurrentSession();
		Long categoryId = progressInfo.get("categoryId");
		Long timeRemainingInMin = progressInfo.get("timeRemaining");
		if (timeRemainingInMin == 0) {
			throw new BadRequestException(126635887l, "Trying to set time remaining to 0, ignoring request ",
					"timeRemaining = 0");
		}
		Test test = testSession.getTest();
		List<Category> testCategories = test.getCategories();
		Category category = session.get(Category.class, categoryId);
		if (!testCategories.contains(category)) {
			throw new BadRequestException(6663259974l, "category not in current test",
					"test categories do not contain given category");
		}
		Long maxTimeInMin = category.getTimeAllowedInSeconds() / 60;
		if (timeRemainingInMin > maxTimeInMin) {
			throw new BadRequestException(12399484002l, "Time remaining exceeds maximum allowed time in that category",
					"timeRemainingInMin > maxTimeInMin");
		}
		// category progress for this
		// student should now start at `setBack` minutes ago, from now
		Long setBack = maxTimeInMin - timeRemainingInMin;
		Progress alteredProgress = new Progress(testSession, category);
		// set the start of altered progress to 5 min ago, so that student is 5
		// min into the category specified
		alteredProgress.setStartAt(new Date(System.currentTimeMillis() - 1000 * setBack * 60));
		Progress lastProgress = testSession.getLatestProgress();
		if (lastProgress != null) {
			lastProgress.setEndAt(new Date()); // end now
		}
		testSession.addProgress(alteredProgress);
		testSession.markAsUnsubmitted();
		session.save(testSession);
		return progressInfo;
	}

	@Override
	protected Class<TestSession> getResourceType() {
		return TestSession.class;
	}
}