package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.datagenerators.CategoryDataGenerator;
import org.example.framework.apis.CategoriesApi;
import org.example.dto.requests.CategoryRequest;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Categories")
@Story("Tests for deleting categories")
public class DeleteCategory {

    @Test
    public void testDeleteCategory_Valid() {
        CategoryRequest category = CategoryDataGenerator.generateRandomCategory();
        Response createResponse = CategoriesApi.createCategory(category);
        createResponse.then().statusCode(201);
        int categoryId = createResponse.jsonPath().getInt("id");

        Response response = CategoriesApi.deleteCategory(String.valueOf(categoryId));

        response.then()
                .statusCode(200);

        CategoriesApi.getCategoryById(String.valueOf(categoryId))
                .then()
                .statusCode(anyOf(is(400), is(404)));
    }

    @Test
    public void testDeleteCategory_Invalid() {
        Response response = CategoriesApi.deleteCategory("999999");

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }
}
