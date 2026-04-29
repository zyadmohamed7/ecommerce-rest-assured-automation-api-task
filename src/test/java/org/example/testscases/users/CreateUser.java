package org.example.testscases.users;

import io.restassured.response.Response;
import org.example.dto.requests.UserRequest;
import org.example.dto.responses.UserResponse;
import org.example.framework.apis.UsersApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.example.datagenerators.UserDataGenerator.createValidUser;
import static org.example.datagenerators.UserDataGenerator.createInvalidUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("User Management")
@Story("Tests for creating users")
public class CreateUser {

    @Test
    public void userCanRegisterWithValidData() {
        UserRequest user = createValidUser();

        Response response = UsersApi.createUser(user);

        response.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("email", equalTo(user.getEmail()))
                .body("name", equalTo(user.getName()))
                .body(matchesJsonSchemaInClasspath("schemas/userSchema.json"))
                .log().ifValidationFails()
                .extract()
                .as(UserResponse.class);
    }

    @Test
    public void userCannotRegisterWithInvalidData() {
        UserRequest user = createInvalidUser();

        Response response = UsersApi.createUser(user);

        response.then()
                .statusCode(anyOf(is(400), is(500)))
                .log().ifValidationFails();
    }
}
