package com.renison.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseTokenPayload {
    long exp; // time by which token expires

    @JsonIgnore
    long expTime = 0; // expiration time

    public BaseTokenPayload() {
        exp = System.currentTimeMillis() / 1000;
    }

    public long getExp() {
        return exp + expTime;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getExpTime() {
        return expTime;
    }

    public void setExpTime(long expTime) {
        this.expTime = expTime;
    }

}
