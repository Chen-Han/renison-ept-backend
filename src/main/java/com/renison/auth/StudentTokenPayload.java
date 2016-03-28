package com.renison.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentTokenPayload extends BaseTokenPayload {
    private long testSessionId;
    @JsonIgnore
    public static final long DEFAULT_EXP_TIME = 60 * 15; //default expiry time 15min

    public StudentTokenPayload(long testSessionId) {
        super();
        this.testSessionId = testSessionId;
        setExpTime(DEFAULT_EXP_TIME);
    }

    public StudentTokenPayload() {

    }

    public long getTestSessionId() {
        return testSessionId;
    }

    public void setTestSessionId(long testSessionId) {
        this.testSessionId = testSessionId;
    }
}
