package com.zl.bgec.basicapi.common.oauth2;

public interface AccessTokenManager {
    String getAccessToken(String sessionId);
    void setAccessToken(String sessionId, String token);
}
