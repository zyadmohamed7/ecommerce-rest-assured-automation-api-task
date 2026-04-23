package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.framework.apis.CategoriesApi;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class GetCategoryById {

    @Test
    public void testGetCategoryById_Valid() {
        Response allCategories = CategoriesApi.getCategories();
        int categoryId = allCategories.jsonPath().getInt("[0].id");

        Response response = CategoriesApi.getCategoryById(String.valueOf(categoryId));

        response.then()
                .statusCode(200)
                .body("id", equalTo(categoryId));
    }

    @Test
    public void testGetCategoryById_Invalid() {
        Response response = CategoriesApi.getCategoryById("999999");

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }
}
