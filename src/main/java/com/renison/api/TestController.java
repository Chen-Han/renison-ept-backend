package com.renison.api;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.renison.model.Test;
import hibernate.util.HibernateUtil;

@RestController
public class TestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test_ctrl")
    public List<Test> test(@RequestParam(value = "name", defaultValue = "World") String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Test> tests = session.createCriteria(Test.class).list();
        session.getTransaction().commit();
        return tests;
    }
}