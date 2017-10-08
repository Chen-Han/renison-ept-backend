package com.renison.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.auth.JwtUtil;
import com.renison.auth.StudentTokenPayload;
import com.renison.exception.ErrorNumber;
import com.renison.exception.ProctorException;
import com.renison.jackson.View;
import com.renison.model.Category;
import com.renison.model.Progress;
import com.renison.model.Question;
import com.renison.model.QuestionResponse;
import com.renison.model.ResponseContent;
import com.renison.model.Test;
import com.renison.model.TestComponent;
import com.renison.model.TestSession;

@RestController
@RequestMapping(value = "/proctor")
@Transactional
public class ProctorController {
	private static JwtUtil jwtUtil = new JwtUtil();

	@Autowired
	private TestSessionController testSessionController;

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping(value = "/currentCategory", method = RequestMethod.GET)
	@JsonView(View.Student.class)
	@Transactional
	public Category currentCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
		TestSession testSession = verifyLoginSession(eptLoginToken);
		checkTestSubmitted(testSession);
		Progress progress = testSession.getLatestProgress();
		return withMetaInfo(progress.getCategory(), testSession);
	}

	@RequestMapping(value = "/nextCategory", method = RequestMethod.POST)
	@JsonView(View.Student.class)
	public Category nextCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
		TestSession testSession = verifyLoginSession(eptLoginToken);
		Progress progress = toNextProgress(testSession);
		if (progress == null) { // no next progress anymore, test reaches end
			return null;
		} else {
			return withMetaInfo(progress.getCategory(), testSession);
		}
	}

	@RequestMapping(value = "/timer", method = RequestMethod.GET)
	@Transactional
	public Long getTimeLeft(@RequestHeader("ept-login-token") String eptLoginToken) {
		TestSession testSession = verifyLoginSession(eptLoginToken);
		checkTestSubmitted(testSession);
		if (!testSession.isTestStarted()) {
			throw new ProctorException(6399985223l, "Test has not started", "");
		}
		return testSession.getTimeLeftForCurrentProgress();
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Set<ResponseContent> submitAnswer(@RequestBody Set<ResponseContent> responseContents,
			@PathVariable("questionId") Long questionId, @RequestHeader("ept-login-token") String eptLoginToken) {
		TestSession testSession = verifyLoginSession(eptLoginToken);
		checkTestSubmitted(testSession);
		long timeLeft = testSession.getTimeLeftForCurrentProgress();
		if (timeLeft <= 0) {
			throw new ProctorException(9638882399l, "Category is past due, cannot submit", "");
		}
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		if (!question.getCategory().getId().equals(testSession.getLatestCategory().getId())) {
			throw new ProctorException(8095612302l, "Question is not in current category", "");
		}
		QuestionResponse questionResponse = (QuestionResponse) session.createCriteria(QuestionResponse.class)
				.add(Restrictions.eq("question", question)).add(Restrictions.eq("testSession", testSession))
				.uniqueResult();
		if (questionResponse == null) {
			questionResponse = new QuestionResponse(testSession, question);
		}
		if (responseContents.size() > 1) {
			throw new ProctorException(5236622212l, "Does not support more than one answer",
					"Only one answer is allowed");
		}
		for (ResponseContent rc : responseContents) {
			rc.setQuestionResponse(questionResponse);
		}
		for (ResponseContent oldRc : questionResponse.getResponseContents()) {
			session.delete(oldRc);// must do this to delete a set of objects
									// from hibernate
		}
		questionResponse.getResponseContents().clear();
		questionResponse.getResponseContents().addAll(responseContents);
		session.saveOrUpdate(questionResponse);
		return responseContents;
	}

	@RequestMapping(value = "/statusReporter", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody Map<String, Object> handleStatusReport(@RequestBody Map<String, Long> studentStatusReport,
			@RequestHeader("ept-login-token") String eptLoginToken) {
		TestSession testSession = verifyLoginSession(eptLoginToken);
		Long categoryId = studentStatusReport.get("categoryId");
		Long timeRemaining = studentStatusReport.get("timeRemaining");
		Category ongoingCategory = testSession.getLatestCategory();
		Long timeRemainingServer = testSession.getTimeLeftForCurrentProgress();
		boolean categoryUpdated = !ongoingCategory.getId().equals(categoryId);

		boolean timeUpdated = Math.abs(timeRemaining - timeRemainingServer) > 30;
		Map<String, Object> reportResponse = new HashMap<>();
		reportResponse.put("refreshNeeded", timeUpdated || categoryUpdated);
		return reportResponse;
	}

	@Transactional
	private Progress toNextProgress(TestSession testSession) {
		// if test ended, throw exception
		checkTestSubmitted(testSession);
		// if no progress, start a new one
		if (!testSession.isTestStarted()) {
			Category category = testSession.getTest().getFirstCategory();
			if (category == null) {
				throw new ProctorException(56056772123l, "Test is empty", "test is empty");
			}
			Progress progress = new Progress(testSession, category);
			testSession.addProgress(progress);
			sessionFactory.getCurrentSession().saveOrUpdate(testSession);
			return progress;
		}
		Progress progress = testSession.getLatestProgress();
		endCurrentProgress(progress);
		Test test = testSession.getTest();
		Category nextCategory = test.nextCategoryTo(progress.getCategory());
		if (nextCategory == null) {
			testSession.markAsSubmitted();
			sessionFactory.getCurrentSession().saveOrUpdate(testSession);
			return null;// it was already the last category, cannot go to the
						// next one
		}
		Progress nextProgress = new Progress(testSession, nextCategory);
		testSession.addProgress(nextProgress);
		sessionFactory.getCurrentSession().save(testSession);
		return nextProgress;
	}

	@Transactional
	private void endCurrentProgress(Progress progress) {
		long timeLeft = progress.getTimeLeftInSeconds();
		if (timeLeft <= 0) {
			progress.setEndAt(progress.getExpectedEndAt());
		} else {
			progress.setEndAt(new Date());// end now
		}
		sessionFactory.getCurrentSession().saveOrUpdate(progress);
	}

	public void checkTestSubmitted(TestSession testSession) {
		if (testSession.isTestSubmitted()) {
			throw new ProctorException(56983266541l, "test ended, invalid operation", "", ErrorNumber.TEST_SUBMITTED);
		}
	}

	// adds student's response to each question
	public void addStudentResponse(Category category, TestSession testSession, Session session) {
		List<QuestionResponse> questionResponses = session.createCriteria(QuestionResponse.class)
				.add(Restrictions.eq("testSession", testSession)).list();
		Set<Question> questions = questionResponses.stream().map((qs) -> {
			Question q = qs.getQuestion();
			q.addResponse(qs.getResponseContents().iterator().next().getText());
			return q;
		}).collect(Collectors.toSet());
		List<TestComponent> testComponents = category.getTestComponents();
		testComponents.stream().forEach((t) -> {
			if (t instanceof Question) {
				Question question = (Question) t;
				if (questions.contains(question)) {
					question.setSaved(true);
				}
			}
		});
	}

	// adds additional info such as student's response for each question
	// and a list of categories in the test
	public Category withMetaInfo(Category category, TestSession testSession) {
		Session session = sessionFactory.getCurrentSession();
		addStudentResponse(category, testSession, session);
		List<String> categoryNames = category.getTest().getCategories().stream().map(Category::getName)
				.collect(Collectors.toList());
		category.setAllCategories(categoryNames);
		return category;
	}

	private TestSession verifyLoginSession(String eptLoginToken) {
		StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class);
		Session session = sessionFactory.getCurrentSession();
		TestSession testSession = session.get(TestSession.class, payload.getTestSessionId());
		return testSession;
	}
}
