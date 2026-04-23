package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.datagenerators.CategoryDataGenerator;
import org.example.framework.apis.CategoriesApi;
import org.example.dto.requests.CategoryRequest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UpdateCategory {

    @Test
    public void testUpdateCategory_Valid() {
        Response allCategories = CategoriesApi.getCategories();
        int categoryId = allCategories.jsonPath().getInt("[0].id");

        CategoryRequest updateBody = CategoryRequest.builder()
                .name("Updated Name " + System.currentTimeMillis())
                .image("https://placeimg.com/640/480/any")
                .build();

        Response response = CategoriesApi.updateCategory(String.valueOf(categoryId), updateBody);

        response.then()
                .statusCode(200)
                .body("name", equalTo(updateBody.getName()));
    }

    @Test
    public void testUpdateCategory_Invalid() {
        CategoryRequest updateBody = CategoryRequest.builder().name("New Name").build();
        Response response = CategoriesApi.updateCategory("999999", updateBody);

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }
}
