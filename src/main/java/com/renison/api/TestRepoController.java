//package com.renison.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.hateoas.Resources;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.renison.model.Test;
//import com.renison.repository.TestRepository;
//
//@RepositoryRestController
//public class TestRepoController {
//
//    private final TestRepository repository;
//
//    @Autowired
//    public TestRepoController(TestRepository repo) {
//        repository = repo;
//    }
//
//    @RequestMapping("/tests")
//    public @ResponseBody ResponseEntity<?> list() {
//        Resources<Test> resources = new Resources<Test>(repository.findAll());
//        return ResponseEntity.ok(resources);
//    }
//
//}