package org.example.testscases.categories;

import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import io.restassured.response.Response;
import org.example.datagenerators.CategoryDataGenerator;
import org.example.framework.apis.CategoriesApi;
import org.example.dto.requests.CategoryRequest;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Categories")
@Story("Tests for creating categories")
public class CreateCategory {

    @Test
    public void userCanCreateANewCategory() {
        CategoryRequest category = CategoryDataGenerator.generateRandomCategory();

        Response response = CategoriesApi.createCategory(category);

        response.then()
                .statusCode(201)
                .body("name", equalTo(category.getName()))
                .body("image", equalTo(category.getImage()))
                .body(matchesJsonSchemaInClasspath("schemas/categorySchema.json"));
    }

    @Test
    public void userCannotCreateCategoryWithoutName() {
        CategoryRequest category = CategoryDataGenerator.invalidCategoryMissingName();

        Response response = CategoriesApi.createCategory(category);

        response.then()
                .statusCode(anyOf(is(400), is(500)));
    }
}
