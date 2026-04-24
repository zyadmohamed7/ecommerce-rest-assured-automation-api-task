package org.example.testscases.auth;

import io.restassured.response.Response;
import org.example.datagenerators.AuthDataGenerator;
import org.example.dto.requests.LoginRequest;
import org.example.dto.responses.LoginResponse;
import org.example.framework.apis.AuthApi;
import org.example.framework.auth.AuthManager;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Authentication")
@Story("Tests for retrieving user profile with JWT")
public class GetProfile {

    @Test
    public void testGetProfile() {
        LoginRequest loginPayload = AuthDataGenerator.createValidLogin();
        Response loginResponse = AuthApi.login(loginPayload);
        loginResponse.then().statusCode(201);
        String token = loginResponse.as(LoginResponse.class).getAccess_token();

        AuthManager.setToken(token);

        AuthApi.getProfile()
                .then()
                .statusCode(200)
                .body("email", notNullValue())
                .log().ifValidationFails();
        AuthManager.clear();
    }

    @Test
    public void testGetProfileWithInvalidToken() {
        AuthManager.setToken("invalid-token-12345");

        AuthApi.getProfile()
                .then()
                .statusCode(401)
                .log().ifValidationFails();

        AuthManager.clear();
    }
}
