package com.renison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.renison.auth.JwtUtil;
import com.renison.auth.StudentTokenPayload;
import com.renison.model.Category;
import com.renison.model.Progress;
import com.renison.model.TestSession;

@RestController
@RequestMapping(value = "/proctor")
public class ProctorController {
    private static JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    TestSessionController testSessionController;

    @RequestMapping(value = "/currentCategory", method = RequestMethod.GET)
    //    @JsonView(View.Student.class)
    public Category currentCategory(@RequestHeader("ept-login-token") String eptLoginToken) {
        StudentTokenPayload payload = jwtUtil.verifyLoginToken(eptLoginToken, StudentTokenPayload.class);
        TestSession testSession = testSessionController.get(payload.getTestSessionId());
        Progress progress = testSession.getLatestProgress();
        Category category = progress.getCategory();
        category.setTestComponents(category.getTestComponents());
        return category;
    }
}
