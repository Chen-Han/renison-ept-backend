package com.renison.controller;

import java.io.UnsupportedEncodingException;
//import example.springdata.rest.security.Application;
//import example.springdata.rest.security.Employee;
//import example.springdata.rest.security.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test cases that verify the URL level of security by using the Spring MVC test
 * framework.
 *
 * @author Greg Turnquist
 * @author Oliver Gierke
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringApplicationConfiguration(classes = { ApplicationConfig.class })
public class LoginControllerTest {

    static final String PAYLOAD = "{\"firstName\": \"Saruman\", \"lastName\": \"the White\", " + "\"title\": \"Wizard\"}";

    @Autowired
    WebApplicationContext context;
    @Autowired
    FilterChainProxy filterChain;

    MockMvc mvc;

    @Before
    public void setUp() {
        //  TODO initialization error
        //        this.mvc = webAppContextSetup(context).addFilters(filterChain).build();
        //
        //        SecurityContextHolder.clearContext();
    }

    //TODO the test cannot be run
    @Test
    public void testLoginControllerWorks() throws UnsupportedEncodingException, Exception {
        //        ObjectMapper mapper = new ObjectMapper();
        //        Student student = new Student();
        //        student.setFirstName("test");
        //        student.setLastName("testLastName");
        //        String studentJson = mapper.writeValueAsString(student);
        //        String loginResponse = mvc.perform(get("/login").accept(MediaType.APPLICATION_JSON).content(studentJson))
        //                .andExpect(status().isOk())
        //                .andReturn().getResponse().getContentAsString();
        //        assertTrue(loginResponse.contains("jwt"));
    }

    // Examples
    //    @Test
    //    public void allowsAccessToRootResource() throws Exception {
    //
    //        mvc.perform(get("/").//
    //                accept(MediaTypes.HAL_JSON)).//
    //                andExpect(header().string("Content-Type", MediaTypes.HAL_JSON.toString())).//
    //                andExpect(status().isOk()).//
    //                andDo(print());
    //    }
    //
    //    @Test
    //    public void rejectsPostAccessToCollectionResource() throws Exception {
    //
    //        mvc.perform(post("/employees").//
    //                content(PAYLOAD).//
    //                accept(MediaTypes.HAL_JSON)).//
    //                andExpect(status().isUnauthorized()).//
    //                andDo(print());
    //    }
    //
    //    @Test
    //    public void allowsGetRequestsButRejectsPostForUser() throws Exception {
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.add(HttpHeaders.ACCEPT, MediaTypes.HAL_JSON.toString());
    //        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("greg:turnquist").getBytes())));
    //
    //        mvc.perform(get("/employees").//
    //                headers(headers)).//
    //                andExpect(content().contentType(MediaTypes.HAL_JSON)).//
    //                andExpect(status().isOk()).//
    //                andDo(print());
    //
    //        mvc.perform(post("/employees").//
    //                headers(headers)).//
    //                andExpect(status().isForbidden()).//
    //                andDo(print());
    //    }
    //
    //    @Test
    //    public void allowsPostRequestForAdmin() throws Exception {
    //
    //        HttpHeaders headers = new HttpHeaders();
    //        headers.set(HttpHeaders.ACCEPT, "application/hal+json");
    //        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + new String(Base64.encode(("ollie:gierke").getBytes())));
    //
    //        mvc.perform(get("/employees").//
    //                headers(headers)).//
    //                andExpect(content().contentType(MediaTypes.HAL_JSON)).//
    //                andExpect(status().isOk()).//
    //                andDo(print());
    //
    //        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    //
    //        String location = mvc.perform(post("/employees").//
    //                content(PAYLOAD).//
    //                headers(headers)).//
    //                andExpect(status().isCreated()).//
    //                andDo(print()).//
    //                andReturn().getResponse().getHeader(HttpHeaders.LOCATION);
    //
    //        ObjectMapper mapper = new ObjectMapper();
    //
    //        String content = mvc.perform(get(location)).//
    //                andReturn().getResponse().getContentAsString();
    //        Employee employee = mapper.readValue(content, Employee.class);
    //
    //        assertThat(employee.getFirstName(), is("Saruman"));
    //        assertThat(employee.getLastName(), is("the White"));
    //        assertThat(employee.getTitle(), is("Wizard"));
    //    }
}
