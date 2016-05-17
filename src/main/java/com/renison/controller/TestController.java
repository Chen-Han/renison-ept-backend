package com.renison.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.exception.NotFoundException;
import com.renison.jackson.View.Admin;
import com.renison.model.Category;
import com.renison.model.Test;

@RestController
@RequestMapping("/tests")
@CrossOrigin("*")
public class TestController extends BaseController<Test> {

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
	@RequestMapping(value = "/{testId}/categories", method = RequestMethod.GET)
	public List<Category> getCategories(@PathVariable Long testId) {
		return this.get(testId).getCategories();
	}

	protected Class<Test> getResourceType() {
		return Test.class;
	}
}