package org.example.testscases.auth;

import org.example.datagenerators.AuthDataGenerator;
import org.example.dto.requests.LoginRequest;
import org.example.framework.apis.AuthApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Authentication")
@Story("Tests for user login")
public class Login {

    @Test
    public void userCanLoginWithValidCredentials() {
        LoginRequest credentials = AuthDataGenerator.createValidLogin();

        AuthApi.login(credentials)
                .then()
                .statusCode(201)
                .body("access_token", notNullValue())
                .body("refresh_token", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/loginSchema.json"))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotLoginWithWrongPassword() {
        LoginRequest credentials = AuthDataGenerator.createInvalidLogin();

        AuthApi.login(credentials)
                .then()
                .statusCode(401)
                .log().ifValidationFails();
    }
}
