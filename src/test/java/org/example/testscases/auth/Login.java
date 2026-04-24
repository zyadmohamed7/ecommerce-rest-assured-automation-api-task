package org.example.testscases.auth;

import io.restassured.response.Response;
import org.example.datagenerators.AuthDataGenerator;
import org.example.dto.requests.LoginRequest;
import org.example.framework.apis.AuthApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Authentication")
@Story("Tests for user login")
public class Login {

    @Test
    public void testLogin() {
        LoginRequest loginPayload = AuthDataGenerator.createValidLogin();

        AuthApi.login(loginPayload)
                .then()
                .statusCode(201)
                .body("access_token", notNullValue())
                .body("refresh_token", notNullValue())
                .log().ifValidationFails();
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        LoginRequest loginPayload = AuthDataGenerator.createInvalidLogin();

        AuthApi.login(loginPayload)
                .then()
                .statusCode(401)
                .log().ifValidationFails();
    }
}
