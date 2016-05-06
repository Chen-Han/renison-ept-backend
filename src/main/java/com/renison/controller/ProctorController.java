package com.renison.controller;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.auth.JwtUtil;
import com.renison.auth.StudentTokenPayload;
import com.renison.exception.ProctorException;
import com.renison.jackson.View;
import com.renison.model.Category;
import com.renison.model.Progress;
import com.renison.model.Test;
import com.renison.model.TestSession;

@RestController
@RequestMapping(value = "/proctor")
public class ProctorController {
	private static JwtUtil jwtUtil = new JwtUtil();

	@Autowired
	private TestSessionController testSessionController;

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/currentCategory", method = RequestMethod.GET)
	@JsonView(View.Student.class)
	public Category currentCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
		// TODO refactor
		StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class);
		TestSession testSession = testSessionController.get(payload.getTestSessionId());

		Progress progress = testSession.getLatestProgress();
		return withAllFields(progress.getCategory());
	}

	@RequestMapping(value = "/nextCategory", method = RequestMethod.POST)
	@JsonView(View.Student.class)
	@Transactional
	public Category nextCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
		// TODO refactor to auth middleware
		StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class);
		Session session = sessionFactory.getCurrentSession();
		TestSession testSession = session.get(TestSession.class, payload.getTestSessionId());
		Progress progress = toNextProgress(testSession);
		session.flush();
		if (progress == null) { // no next progress anymore, test reaches end
			return null;
		} else {
			return withAllFields(progress.getCategory());
		}
	}

	@Transactional
	private Progress toNextProgress(TestSession testSession) {
		// if test ended, throw exception
		checkTestSubmitted(testSession);
		// if no progress, start a new one
		Progress progress = testSession.getLatestProgress();
		if (progress == null) {
			Category category = testSession.getTest().getFirstCategory();
			if (category == null) {
				throw new ProctorException(56056772123l, "Test is empty", "test is empty");
			}
			progress = new Progress(testSession, category);
			testSession.addProgress(progress);
			sessionFactory.getCurrentSession().saveOrUpdate(testSession);
			return progress;
		}
		endCurrentProgress(progress);
		Test test = testSession.getTest();
		Category nextCategory = test.nextCategoryTo(progress.getCategory());
		if (nextCategory == null) {
			return null;// it was already the last category, cannot go to the
						// next one
		}
		Progress nextProgress = new Progress(testSession, nextCategory);
		testSession.addProgress(nextProgress);
		// sessionFactory.getCurrentSession().save(testSession);
		return nextProgress;
	}

	@Transactional
	private void endCurrentProgress(Progress progress) {
		long startAt = progress.getStartAt().getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long timePassed = (now - startAt);
		long timeAllowed = (long) progress.getCategory().getTimeAllowedInSeconds();
		if (timePassed >= timeAllowed) {
			Date endAt = new Date((startAt + timeAllowed) * 1000);
			progress.setEndAt(endAt);
		} else {
			progress.setEndAt(new Date());// end now
		}
		// sessionFactory.getCurrentSession().saveOrUpdate(progress);
	}

	public void checkTestSubmitted(TestSession testSession) {
		if (testSession.isTestSubmitted()) {
			throw new ProctorException(56983266541l, "test ended, cannot go to next category", "");
		}
	}

	private Category withAllFields(Category category) {
		// get around lazily initialized fields
		category.setTestComponents(category.getTestComponents());
		return category;
	}
}
