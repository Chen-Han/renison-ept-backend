package com.renison.auth;

import java.io.IOException;
import java.text.ParseException;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.renison.exception.LoginTokenException;
import com.renison.util.JsonUtil;

public class JwtUtil {
    public static final long EXPIRE_TIME_SECONDS = 600;
    private static Logger logger = Logger.getLogger(JwtUtil.class);
    private static final String JWT_SECRET = "Ren1son-ept-secret-that-must-be-at-least256-bits-long";
    public static final long LOGIN_TOKEN_EXCEPTION_NUMBER = 23056764608l;

    public String getToken(Object obj) throws JOSEException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        // Create JWS payload
        Payload payload = new Payload(json);

        // Create JWS header with HS256 algorithm
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // Create JWS object
        JWSObject jwsObject = new JWSObject(header, payload);

        JWSSigner signer = new MACSigner(JWT_SECRET);
        jwsObject.sign(signer);

        // Serialise JWS object to compact format
        String s = jwsObject.serialize();

        return s;
    }

    public String getTestSessionJwt(Long testSessionId) throws JsonProcessingException, JOSEException {
        StudentTokenPayload payload = new StudentTokenPayload(testSessionId);
        String jwt = this.getToken(payload);
        return jwt;
    }

    public <T extends BaseTokenPayload> T verifyLoginToken(String tokenString, Class<T> valueType) {
        try {
            JWSObject jwsObject = JWSObject.parse(tokenString);
            JWSVerifier verifier = new MACVerifier(JWT_SECRET);

            // check signature
            boolean isValid = jwsObject.verify(verifier);
            if (!isValid) {
                logger.debug("token is invalid");
                throw new LoginTokenException(LOGIN_TOKEN_EXCEPTION_NUMBER, "invalid signature", null);
            }

            // check expiration
            Payload pld = jwsObject.getPayload();

            T payloadObject = JsonUtil.parseJson(pld.toString(), valueType);

            if (payloadObject.getExp() < System.currentTimeMillis() / 1000) {
                logger.debug("token is expired");
                throw new LoginTokenException(LOGIN_TOKEN_EXCEPTION_NUMBER, "expired token", null);
            }

            return payloadObject;
        } catch (JOSEException | JsonSyntaxException | IOException | ParseException e) {
            logger.debug("parsing token exception");
            // TODO: the debugNumber should probably be an errorNumber - need to use builder pattern for APIException 
            // and also make sure json is using errorNumbers with a fallback to debugNumbers 
            throw new LoginTokenException(LOGIN_TOKEN_EXCEPTION_NUMBER, "token payload parsing error", null);
        }
    }
}
