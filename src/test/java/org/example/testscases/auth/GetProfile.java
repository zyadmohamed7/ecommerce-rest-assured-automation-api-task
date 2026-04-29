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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Authentication")
@Story("Tests for retrieving user profile with JWT")
public class GetProfile {

    @Test
    public void authenticatedUserCanViewTheirProfile() {
        String token = loginAndGetToken();
        AuthManager.setToken(token);

        Response response = AuthApi.getProfile();

        response.then()
                .statusCode(200)
                .body("email", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/profileSchema.json"))
                .log().ifValidationFails();

        AuthManager.clear();
    }

    @Test
    public void unauthenticatedUserCannotViewProfile() {
        AuthManager.setToken("invalid-token-12345");

        Response response = AuthApi.getProfile();

        response.then()
                .statusCode(401)
                .log().ifValidationFails();

        AuthManager.clear();
    }

    private String loginAndGetToken() {
        LoginRequest credentials = AuthDataGenerator.createValidLogin();
        Response response = AuthApi.login(credentials);
        response.then().statusCode(201);
        return response.as(LoginResponse.class).getAccess_token();
    }
}
