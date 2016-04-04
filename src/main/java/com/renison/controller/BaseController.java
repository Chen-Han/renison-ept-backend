package com.renison.controller;

import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
public abstract class BaseController<T> {
    private Logger logger = Logger.getLogger(BaseController.class);
    private CrudRepository<T, Long> repo;

    public BaseController(CrudRepository<T, Long> repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody Iterable<T> findAll() {
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody T create(@RequestBody T entity) {
        logger.debug(String.format("create() with body %s of type %s", entity, entity.getClass().getName()));

        T created = this.saveOrUpdate(entity);

        return created;
    }

    public T saveOrUpdate(T entity) {
        T newEntity = this.repo.save(entity);
        return newEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody T get(@PathVariable Long id) {
        return this.repo.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody T update(@PathVariable Long id, @RequestBody T json) {
        logger.debug(String.format("update() of id#%s with body %s", id, json));
        logger.debug(String.format("T json is of type %s", json.getClass()));

        T entity = this.repo.findOne(id);
        try {
            BeanUtils.copyProperties(entity, json);
        } catch (Exception e) {
            logger.warn("while copying properties", e);
            throw Throwables.propagate(e);
        }

        logger.debug(String.format("merged entity: %s", entity));

        T updated = this.saveOrUpdate(entity);
        logger.debug(String.format("updated enitity: %s", updated));
        return updated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Map<String, Object> delete(@PathVariable Long id) {
        this.repo.delete(id);
        Map<String, Object> m = Maps.newHashMap();
        m.put("success", true);
        return m;
    }
}
