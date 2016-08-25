package com.renison.controller;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Maps;
import com.renison.exception.NotFoundException;
import com.renison.jackson.View;
import com.renison.model.BaseModel;

@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
@Transactional
@CrossOrigin("*")
public abstract class BaseController<T extends BaseModel> {
	private Logger logger = Logger.getLogger(BaseController.class);

	protected SessionFactory sessionFactory;

	protected abstract Class<T> getResourceType();

	public BaseController() {
	}

	@RequestMapping(method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<T> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(getResourceType()).list();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody T create(@RequestBody T entity) {
		logger.debug(String.format("create() with body %s of type %s", entity, entity.getClass().getName()));
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@JsonView(View.Public.class)
	public @ResponseBody T get(@PathVariable Long id) {
		T entity = sessionFactory.getCurrentSession().get(getResourceType(), id);
		if (entity == null) {
			throw new NotFoundException(883002141l, "entity not found", "");
		}
		return entity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody T update(@PathVariable Long id, @RequestBody T json) {
		logger.debug(String.format("update() of id#%s with body %s", id, json));
		logger.debug(String.format("T json is of type %s", json.getClass()));

		T entity = get(id);
		// copy all non-null values in json to entity
		// TODO caution, if user's intention is to null out some values, this
		// would
		// fail
		BeanUtils.copyProperties(json, entity, getNullPropertyNames(json));
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		logger.debug(String.format("updated enitity: %s", entity));
		return entity;
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> delete(@PathVariable Long id) {
		sessionFactory.getCurrentSession().delete(this.get(id));
		Map<String, Object> m = Maps.newHashMap();
		m.put("success", true);
		return m;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> remove(@RequestParam("id") Long id) {
		return delete(id);
	}

	@CrossOrigin("*")
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public @ResponseBody Boolean options(@PathVariable Long id) {
		return true;
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
		return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
