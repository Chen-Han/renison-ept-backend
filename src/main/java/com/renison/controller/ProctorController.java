package com.renison.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.renison.auth.JwtUtil;
import com.renison.auth.StudentTokenPayload;
import com.renison.exception.ProctorException;
import com.renison.jackson.View;
import com.renison.model.Category;
import com.renison.model.Progress;
import com.renison.model.Test;
import com.renison.model.TestSession;

@RestController
@RequestMapping(value = "/proctor")
public class ProctorController {
    private static JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    TestSessionController testSessionController;

    @RequestMapping(value = "/currentCategory", method = RequestMethod.GET)
    @JsonView(View.Student.class)
    public Category currentCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
        StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class); //TODO this needs to be refactored to auth middleware

        TestSession testSession = testSessionController.get(payload.getTestSessionId());

        Progress progress = testSession.getLatestProgress();

        Category category = progress.getCategory();
        category.setTestComponents(category.getTestComponents()); // hacky way to get around Jackson ignoring lazily fetched fields
        return category;
    }

    @RequestMapping(value = "/nextCategory", method = RequestMethod.POST)
    @JsonView(View.Student.class)
    public Category nextCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
        StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class); //TODO this needs to be refactored to auth middleware
        TestSession testSession = testSessionController.get(payload.getTestSessionId());
        Progress latestProgress = testSession.getLatestProgress();
        Test test = testSession.getTest();

        if (testSession.getTestSubmitted()) {
            throw new ProctorException(56983266541l, "test ended, cannot go to next category", "");
        }

        // no progress started yet, start the first one in tests
        if (latestProgress == null) {
            latestProgress = new Progress(testSession, test.getCategories().get(0));
            testSession.addProgress(latestProgress);
            testSessionController.saveOrUpdate(testSession);
            Category category = latestProgress.getCategory();
            return withAllFields(category);
        }

        // there is already a category in progress, ending it.
        latestProgress.setEndAt(new Date());
        // TODO what if time allowed has passed?
        Category currentCategory = latestProgress.getCategory();
        List<Category> categories = test.getCategories();
        int currIdx = categories.indexOf(currentCategory);
        if (currIdx == categories.size() - 1) {
            // finishing the exam
            testSessionController.saveOrUpdate(testSession);
            return null;
        }

        Category nextCategory = categories.get(currIdx + 1); // this would go to categories[(currIdx+1)%categories.size()]
        // end current, start new 
        Progress newProgress = new Progress(testSession, nextCategory);
        testSession.addProgress(newProgress);
        testSessionController.saveOrUpdate(testSession);
        return withAllFields(nextCategory);
    }

    private Progress toNextProgress(TestSession testSession) {
        // if test ended, throw exception

        // if no progress, start a new one

        // if there is progress, and time is within timeAllowed, then end current, go to the next category

        // if there is progress, and time is after timeAllowed, then end current one at startAt + timeAllowed, go to next category at current time

    }

    private Category withAllFields(Category category) {
        category.setTestComponents(category.getTestComponents()); // hacky way to get around Jackson ignoring lazily fetched fields
        return category;
    }
}
