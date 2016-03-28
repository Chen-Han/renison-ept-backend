package com.renison.auth;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.JOSEException;

public class JwtUtilTest {
    public static JwtUtil jwtUtil;

    @BeforeClass
    public static void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testJwtCreation() throws JsonProcessingException, JOSEException {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode jsonObj = jsonNodeFactory.objectNode();
        jsonObj.put("exp", 1000);
        String token = jwtUtil.getToken(jsonObj);
        assertEquals(token, "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjEwMDB9.trB5WSwnQuLw5qdrlJa8WLimeBar-7ongd9pBJ220eA");
    }

    @Test
    public void testStudentTokenCreation() throws JsonProcessingException, JOSEException {
        String token = jwtUtil.getTestSessionJwt(1000l);
        System.out.println(token);
    }
}
