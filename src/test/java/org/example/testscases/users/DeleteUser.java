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
@Story("Tests for deleting users")
public class DeleteUser {

    @Test
    public void userCanDeleteTheirAccount() {
        int userId = createUserAndReturnId();

        Response response = UsersApi.deleteUser(String.valueOf(userId));

        response.then()
                .statusCode(200)
                .log().ifValidationFails();
    }

    @Test
    public void deletingNonExistentUserReturnsError() {
        Response response = UsersApi.deleteUser("999999");

        response.then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }

    private int createUserAndReturnId() {
        UserRequest user = createValidUser();
        Response response = UsersApi.createUser(user);
        response.then().statusCode(201);
        return response.jsonPath().getInt("id");
    }
}
