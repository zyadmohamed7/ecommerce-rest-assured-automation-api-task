package org.example.testscases.users;

import io.restassured.response.Response;
import org.example.datagenerators.UserDataGenerator;
import org.example.dto.requests.UserRequest;
import org.example.framework.apis.UsersApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("User Management")
@Story("Tests for updating users")
public class UpdateUser {

    @Test
    public void testUpdateUser() {
        UserRequest user = UserDataGenerator.createValidUser();
        Response createResponse = UsersApi.createUser(user);
        createResponse.then().statusCode(201);
        int userId = createResponse.jsonPath().getInt("id");

        UserRequest updatedPayload = UserRequest.builder()
                .name("Updated Name " + System.currentTimeMillis())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .build();

        UsersApi.updateUser(String.valueOf(userId), updatedPayload)
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedPayload.getName()))
                .log().ifValidationFails();
    }

    @Test
    public void testUpdateUserWithInvalidId() {
        UserRequest updatedPayload = UserDataGenerator.createValidUser();

        UsersApi.updateUser("999999", updatedPayload)
                .then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }
}
