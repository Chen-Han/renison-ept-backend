package com.renison.controller;

import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renison.exception.NotFoundException;
import com.renison.model.Test;

@RestController
@RequestMapping("/tests")
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

	protected Class<Test> getResourceType() {
		return Test.class;
	}
}