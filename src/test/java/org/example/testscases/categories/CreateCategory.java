package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.datagenerators.CategoryDataGenerator;
import org.example.framework.apis.CategoriesApi;
import org.example.dto.requests.CategoryRequest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CreateCategory {

    @Test
    public void testCreateCategory_Valid() {
        CategoryRequest category = CategoryDataGenerator.generateRandomCategory();
        Response response = CategoriesApi.createCategory(category);

        response.then()
                .statusCode(201)
                .body("name", equalTo(category.getName()))
                .body("image", equalTo(category.getImage()));
    }

    @Test
    public void testCreateCategory_Invalid_MissingName() {
        CategoryRequest category = CategoryDataGenerator.invalidCategoryMissingName();
        Response response = CategoriesApi.createCategory(category);

        response.then()
                .statusCode(anyOf(is(400), is(500)));
    }
}
