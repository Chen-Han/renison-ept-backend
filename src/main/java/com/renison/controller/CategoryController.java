package com.renison.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.renison.jackson.View.Admin;
import com.renison.model.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category> {

	@Override
	protected Class<Category> getResourceType() {
		return Category.class;
	}

	@Override
	@JsonView(Admin.class)
	public List<Category> findAll() {
		return super.findAll();
	}
}
