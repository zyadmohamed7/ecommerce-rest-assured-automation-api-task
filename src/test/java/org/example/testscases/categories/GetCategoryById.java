package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.framework.apis.CategoriesApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Categories")
@Story("Tests for retrieving categories by ID")
public class GetCategoryById {

    @Test
    public void userCanRetrieveACategoryById() {
        int categoryId = getFirstCategoryId();

        Response response = CategoriesApi.getCategoryById(String.valueOf(categoryId));

        response.then()
                .statusCode(200)
                .body("id", equalTo(categoryId))
                .body(matchesJsonSchemaInClasspath("schemas/categorySchema.json"));
    }

    @Test
    public void retrievingNonExistentCategoryReturnsError() {
        Response response = CategoriesApi.getCategoryById("999999");

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }

    private int getFirstCategoryId() {
        return CategoriesApi.getCategories().jsonPath().getInt("[0].id");
    }
}
