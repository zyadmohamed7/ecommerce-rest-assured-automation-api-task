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
    public void userCanDeleteAnExistingCategory() {
        int categoryId = createCategoryAndReturnId();

        CategoriesApi.deleteCategory(String.valueOf(categoryId))
                .then()
                .statusCode(200);

        CategoriesApi.getCategoryById(String.valueOf(categoryId))
                .then()
                .statusCode(anyOf(is(400), is(404)));
    }

    @Test
    public void deletingNonExistentCategoryReturnsError() {
        Response response = CategoriesApi.deleteCategory("999999");

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }

    private int createCategoryAndReturnId() {
        CategoryRequest category = CategoryDataGenerator.generateRandomCategory();
        Response response = CategoriesApi.createCategory(category);
        response.then().statusCode(201);
        return response.jsonPath().getInt("id");
    }
}
