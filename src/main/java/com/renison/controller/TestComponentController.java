package com.renison.controller;

import java.util.List;

import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.exception.ProctorException;
import com.renison.jackson.View.Admin;
import com.renison.model.Answer;
import com.renison.model.Question;
import com.renison.model.TestComponent;

@RestController
@RequestMapping("/testComponents")
@CrossOrigin("*")
public class TestComponentController extends BaseController<TestComponent> {

	@Override
	protected Class<TestComponent> getResourceType() {
		return TestComponent.class;
	}

	@Override
	@JsonView(Admin.class)
	public List<TestComponent> findAll() {
		throw new ProctorException(63888252874l, "Should not query testComponents here, use category resource", "");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody TestComponent update(@PathVariable Long id, @RequestBody TestComponent json) {
		// assume that json already has id and all other fields set, we replace
		// testcomponent in database with this.
		Session session = sessionFactory.getCurrentSession();
		TestComponent c = get(id); // id is
		json.setCategory(c.getCategory()); // link to category
		json.setId(id);
		json.setCreateTimestamp(c.getCreateTimestamp());
		// make sure all fields are overriden
		if (json instanceof Question && c instanceof Question) {
			Question questionJson = (Question) json;
			Question questionInDb = (Question) c;
			questionJson.setComponentType(questionInDb.getComponentType());
			questionJson.setQuestionResponses(questionInDb.getQuestionResponses());
			session.clear(); // this clears up all pending DB updates, in this
								// case
			// it is ok, we need this to avoid duplicate object
			// in same session error
			for (Answer answer : questionJson.getAnswers()) {
				session.saveOrUpdate(answer);
			}
			session.update(questionJson);
		} else {

			session.clear(); // this clears up all pending DB updates, in this
								// case
			// it is ok, we need this to avoid duplicate object
			// in same session error
			// json and testComponent have the same ID
			session.update(json);
		}
		return json;
	}

}
