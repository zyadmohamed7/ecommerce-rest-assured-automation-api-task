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
@Story("Tests for deleting users")
public class DeleteUser {

    @Test
    public void testDeleteUser() {
        UserRequest user = UserDataGenerator.createValidUser();
        Response createResponse = UsersApi.createUser(user);
        createResponse.then().statusCode(201);
        int userId = createResponse.jsonPath().getInt("id");

        UsersApi.deleteUser(String.valueOf(userId))
                .then()
                .statusCode(200)
                .log().ifValidationFails();
    }

    @Test
    public void testDeleteUserWithInvalidId() {
        UsersApi.deleteUser("999999")
                .then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }
}
