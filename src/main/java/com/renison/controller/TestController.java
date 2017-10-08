package com.renison.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.renison.exception.BadRequestException;
import com.renison.exception.InternalErrorException;
import com.renison.exception.NotFoundException;
import com.renison.jackson.View.Admin;
import com.renison.jackson.View.AdminSummary;
import com.renison.model.Category;
import com.renison.model.CategoryScore;
import com.renison.model.Test;
import com.renison.model.TestSession;
import com.renison.util.JsonUtil;

@RestController
@RequestMapping("/tests")
@CrossOrigin("*")
public class TestController extends BaseController<Test> {
	private static Logger logger = Logger.getLogger(TestController.class);

	public TestController() {
		super();
	}

	public Test getActive() {
		Test test = (Test) this.sessionFactory.getCurrentSession().createCriteria(getResourceType())
				.add(Restrictions.eq("active", true)).uniqueResult();
		if (test == null) {
			throw new NotFoundException(33002698748l, "No active test", "");
		}
		return test;
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{testId}/copy", method = RequestMethod.POST)
	public @ResponseBody Test copyTest(@PathVariable Long testId) {
		Session session = sessionFactory.getCurrentSession();
		Test test = get(testId);
		test.setName(test.getName() + " copy");
		test.detach();
		session.clear();
		session.persist(test);
		return test;
	}

	@JsonView(AdminSummary.class)
	@RequestMapping(value = "/{testId}/categories", method = RequestMethod.GET)
	public List<Category> getCategories(@PathVariable Long testId) {
		return this.get(testId).getCategories();
	}

	@JsonView(AdminSummary.class)
	@RequestMapping(value = "/{testId}/categories", method = RequestMethod.POST)
	public @ResponseBody Category createCategory(@PathVariable Long testId, @RequestBody Category category) {
		Test test = this.get(testId);
		test.addCategory(category);
		sessionFactory.getCurrentSession().save(test);
		return category;
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{testId}/status", method = RequestMethod.POST)
	public @ResponseBody Test toggleTestStatus(@PathVariable Long testId, @RequestParam Boolean isStart) {
		if (isStart == null) {
			throw new BadRequestException(963558814l, "need isStart param", "");
		}
		Test test = this.get(testId);
		Session session = sessionFactory.getCurrentSession();
		if (isStart) {// deactivate all other test when starting a specific one
			session.createSQLQuery("UPDATE test 	set active = false;").executeUpdate();
		} else {
			// when stopping a test, make sure all active test sessions are
			// terminated
			session.createSQLQuery("UPDATE progress p SET p.end_at = ? WHERE p.end_at is NULL AND p.category_id IN "
					+ "(SELECT id FROM category c WHERE c.test_id = ?);").setDate(0, new Date())
					.setLong(1, test.getId()).executeUpdate();
			session.createSQLQuery("UPDATE test_session SET test_submitted = TRUE WHERE test_id = ?")
					.setLong(0, test.getId()).executeUpdate();
		}
		test.setActive(isStart);
		session.save(test);
		return test;
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{testId}/score", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public void scoreTest(@PathVariable Long testId) {
		Test test = get(testId);
		Session session = sessionFactory.getCurrentSession();
		Set<TestSession> testSessions = test.getTestSessions().stream().filter((t) -> t.isTestSubmitted())
				.collect(Collectors.toSet());
		for (TestSession testSession : testSessions) { // for each testsession
			testSession.scoreTest(session);
			session.save(testSession);
		}
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{testId}/report", method = RequestMethod.GET)
	public @ResponseBody ArrayNode generateReport(@PathVariable Long testId) {
		Test test = get(testId);
		Session session = sessionFactory.getCurrentSession();
		Set<TestSession> testSessions = test.getTestSessions();
		ArrayNode result = JsonUtil.newArrayNode();
		for (TestSession t : testSessions) {
			ObjectNode row = JsonUtil.newJsonObject();
			row = JsonUtil.merge(row, t.getStudent().toReportFormat());
			// get something like
			// {"listening":50,"reading":30} keys are category name, values are
			// scores
			ObjectNode categoryScores = JsonUtil.newJsonObject();
			for (Category category : test.getCategories()) {
				Optional<CategoryScore> categoryScore = t.getCategoryScores().stream()
						.filter((cs) -> cs.getCategory().equals(category)).findFirst();
				String categoryName = category.getName() + " (total: " + category.getTotalScore() + ")";
				categoryScores.put(categoryName,
						categoryScore.isPresent() ? categoryScore.get().getScore().doubleValue() : 0.0);
			}
			row = JsonUtil.merge(row, categoryScores);
			result.add(row);
		}
		return result;
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	// this is a hack to resolve a weird frontend issue
	// angular does not like passing testId in the url
	public @ResponseBody ArrayNode generateReport2(@RequestParam("id") Long id) {
		return generateReport(id);
	}

	@JsonView(Admin.class)
	// Sync the order of categories from client to server
	// accept an array like this:
	// [{
	// categoryId: 1236,
	// ordering: 9
	// }, ...]
	@CrossOrigin(origins = "*", methods = RequestMethod.PUT)
	@RequestMapping(value = "/categoryOrder", method = RequestMethod.PUT)
	public @ResponseBody List<Map<String, Long>> syncComponentOrder(@RequestBody List<Map<String, Long>> orders) {
		for (Map<String, Long> order : orders) {
			Long categoryId = order.get("categoryId");
			Long ordering = order.get("ordering");
			if (categoryId == null || ordering == null) {
				throw new BadRequestException(82623552l, "ordering or testComponentId invalid", "");
			}
			Session session = sessionFactory.getCurrentSession();
			int isSuccess = session.createQuery("UPDATE Category SET ordering = ? WHERE id = ?").setLong(0, ordering)
					.setLong(1, categoryId).executeUpdate();
			if (isSuccess == 0) {
				throw new InternalErrorException(123985743l, "Query failed", "");
			}
		}
		return orders;
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{id}/scoredSessions", method = RequestMethod.GET)
	// TODO this is not a good way to resolve a weird frontend issue
	// angular does not like passing testId in the url
	// Han Oct 5, 2016: is this solved??
	public @ResponseBody Set<Map<String, Object>> getScoredSessions(@PathVariable("id") Long id) {
		Test test = get(id);
		int totalScore = test.getTotalScore();
		return test.getTestSessions().stream().filter((t) -> !t.getCategoryScores().isEmpty()).map((t) -> {
			Map<String, Object> map = new HashMap<>();
			com.renison.model.Student student = t.getStudent();
			map.put("name", student.getFirstName() + " " + student.getLastName());
			map.put("id", t.getId());
			map.put("score", t.getScore());
			map.put("totalScore", totalScore);
			return map;
		}).collect(Collectors.toSet());
	}

	@JsonView(Admin.class)
	@RequestMapping(value = "/{id}/studentStatus", method = RequestMethod.GET)
	public @ResponseBody Set<Map<String, Object>> getStudentStatus(@PathVariable("id") Long id) {
		Test test = get(id);
		// TODO make sure we pass categoryId, timeRemaining, testSessionId name,
		// studentId, testSubmitted flag, and also categoryIds and timeAllowed
		// to frontend
		Set<TestSession> activeSessions = test.getTestSessions().stream().filter((t) -> t.getCategoryScores().isEmpty())
				.collect(Collectors.toSet());
		Set<Map<String, Object>> studentProgressInfo = activeSessions.stream().map((s) -> {
			Map<String, Object> activeSessionInfo = new HashMap<>();
			if (s.getLatestProgress() == null) {
				return null;
			}
			activeSessionInfo.put("testSessionId", s.getId());
			activeSessionInfo.put("categoryId", s.getLatestCategory().getId());
			// note that timeRemaining is in seconds, but
			activeSessionInfo.put("timeRemaining", s.getTimeLeftForCurrentProgress());
			Long elapsedTimeAfterEnd = null;
			if (s.isTestSubmitted()) {
				if (s.getLatestProgress() == null) {
					logger.warn(String.format(
							"Cannot find latest progress for testSession %s, even though testSubmitted is true",
							s.getId()));
					elapsedTimeAfterEnd = 0l;
				} else if (s.getLatestProgress().getEndAt() == null) {
					logger.warn(String.format(
							"The latest progress has no endAt, for testSession %s, even though testSubmitted is true",
							s.getId()));
					elapsedTimeAfterEnd = 0l;
				} else {
					Date testEndedAt = s.getLatestProgress().getEndAt();
					elapsedTimeAfterEnd = (System.currentTimeMillis() - testEndedAt.getTime()) / 1000;
				}
			}
			activeSessionInfo.put("testSubmitted", s.isTestSubmitted());
			// the number of seconds after test ended, if
			// test still active, it is null
			activeSessionInfo.put("elapsedTimeAfterEnd", elapsedTimeAfterEnd);
			activeSessionInfo.put("name", s.getStudent().getFullName());
			activeSessionInfo.put("studentId", s.getStudent().getStudentId());
			return activeSessionInfo;
		}).filter((item) -> item != null).collect(Collectors.toSet());
		return studentProgressInfo;
	}

	// return a list of sessions currently active
	@JsonView(Admin.class)
	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	// this is a hack to resolve a weird frontend issue
	// angular does not like passing testId in the url
	public @ResponseBody Set<Map<String, Object>> getSessions(@RequestParam("id") Long id) {
		Test test = get(id);
		return test.getTestSessions().stream().filter((t) -> !t.isTestSubmitted()).map((t) -> {
			Map<String, Object> map = new HashMap<>();
			com.renison.model.Student student = t.getStudent();
			map.put("name", student.getFullName());
			map.put("id", t.getId());
			return map;
		}).collect(Collectors.toSet());
	}

	protected Class<Test> getResourceType() {
		return Test.class;
	}
}