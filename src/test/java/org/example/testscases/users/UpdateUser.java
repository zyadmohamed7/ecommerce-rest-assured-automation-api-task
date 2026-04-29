package org.example.testscases.users;

import io.restassured.response.Response;
import org.example.dto.requests.UserRequest;
import org.example.framework.apis.UsersApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.example.datagenerators.UserDataGenerator.createValidUser;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("User Management")
@Story("Tests for updating users")
public class UpdateUser {

    @Test
    public void userCanUpdateTheirName() {
        UserRequest originalUser = createValidUser();
        Response createResponse = UsersApi.createUser(originalUser);
        createResponse.then().statusCode(201);
        int userId = createResponse.jsonPath().getInt("id");

        UserRequest updatedPayload = UserRequest.builder()
                .name("Updated Name " + System.currentTimeMillis())
                .email(originalUser.getEmail())
                .password(originalUser.getPassword())
                .avatar(originalUser.getAvatar())
                .build();

        Response response = UsersApi.updateUser(String.valueOf(userId), updatedPayload);

        response.then()
                .statusCode(200)
                .body("name", equalTo(updatedPayload.getName()))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotUpdateNonExistentUser() {
        UserRequest payload = createValidUser();
        Response response = UsersApi.updateUser("999999", payload);

        response.then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }
}
