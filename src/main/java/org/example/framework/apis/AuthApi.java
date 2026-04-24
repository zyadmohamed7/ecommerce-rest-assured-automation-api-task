package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.example.dto.requests.LoginRequest;

public class AuthApi {

    public static Response login(LoginRequest body) {
        return ApiClient.send("POST", Routes.AUTH_LOGIN.getPath(), body);
    }

    public static Response getProfile() {
        return ApiClient.send("GET", Routes.AUTH_PROFILE.getPath(), null);
    }
}
