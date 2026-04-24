package org.example.testscases.users;

import org.example.framework.apis.UsersApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("User Management")
@Story("Tests for retrieving all users")
public class RetrieveAllUsers {

    @Test
    public void testRetrieveAllUsers() {
        UsersApi.getUsers()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .log().ifValidationFails();
    }
}
