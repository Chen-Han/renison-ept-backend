package com.renison.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.exception.BadRequestException;
import com.renison.exception.InternalErrorException;
import com.renison.exception.ProctorException;
import com.renison.jackson.View.Admin;
import com.renison.model.Category;
import com.renison.model.TestComponent;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController extends BaseController<Category> {

	@Override
	protected Class<Category> getResourceType() {
		return Category.class;
	}

	@Override
	@JsonView(Admin.class)
	public List<Category> findAll() {
		throw new ProctorException(63022252874l, "Should not query categories here, use test resource", "");
	}

	@RequestMapping(value = "/{categoryId}/testComponents", method = RequestMethod.GET)
	public @ResponseBody List<TestComponent> getTestComponents(@PathVariable Long categoryId) {
		Category category = this.get(categoryId);
		return category.getTestComponents();
	}

	@RequestMapping(value = "/{categoryId}/testComponents", method = RequestMethod.POST)
	public @ResponseBody TestComponent createTestComponent(@PathVariable Long categoryId,
			@RequestBody TestComponent testComponent) {
		Category category = this.get(categoryId);
		testComponent.setCategory(category);
		List<TestComponent> testComponents = category.getTestComponents();
		if (testComponent.getOrdering() > testComponents.size()) {
			throw new ProctorException(963000123887l, "Cannot create testComponent at random order", "");
		}
		if (testComponent.getOrdering() == testComponents.size()) {
			testComponents.add(testComponent);
			sessionFactory.getCurrentSession().save(category);
			return testComponent;
		}
		testComponents.add(testComponent.getOrdering(), testComponent);
		// shifts the order
		testComponents.listIterator(testComponent.getOrdering() + 1).forEachRemaining((t) -> {
			t.setOrdering(t.getOrdering() + 1);
		});
		return testComponent;
	}

	// Sync the order of test component from client to server
	// accept an array like this:
	// [{
	// testComponentId: 1236,
	// ordering: 9
	// }, ...]
	@CrossOrigin(origins = "*", methods = RequestMethod.PUT)
	@RequestMapping(value = "/componentOrder", method = RequestMethod.PUT)
	public @ResponseBody List<Map<String, Long>> syncComponentOrder(@RequestBody List<Map<String, Long>> orders) {
		for (Map<String, Long> order : orders) {
			Long testComponentId = order.get("testComponentId");
			Long ordering = order.get("ordering");
			if (testComponentId == null || ordering == null) {
				throw new BadRequestException(826552l, "ordering or testComponentId invalid", "");
			}
			Session session = sessionFactory.getCurrentSession();
			int isSuccess = session.createQuery("UPDATE TestComponent SET ordering = ? WHERE id = ?")
					.setLong(0, ordering).setLong(1, testComponentId).executeUpdate();
			if (isSuccess == 0) {
				throw new InternalErrorException(123443113l, "Query failed", "");
			}
		}
		return orders;
	}
}
