package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.framework.apis.CategoriesApi;
import org.example.dto.requests.CategoryRequest;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Categories")
@Story("Tests for updating categories")
public class UpdateCategory {

    @Test
    public void userCanUpdateAnExistingCategory() {
        int categoryId = getFirstCategoryId();
        CategoryRequest updatedCategory = CategoryRequest.builder()
                .name("Updated Name " + System.currentTimeMillis())
                .image("https://placeimg.com/640/480/any")
                .build();

        Response response = CategoriesApi.updateCategory(String.valueOf(categoryId), updatedCategory);

        response.then()
                .statusCode(200)
                .body("name", equalTo(updatedCategory.getName()));
    }

    @Test
    public void userCannotUpdateNonExistentCategory() {
        CategoryRequest updateBody = CategoryRequest.builder().name("New Name").build();

        Response response = CategoriesApi.updateCategory("999999", updateBody);

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }

    private int getFirstCategoryId() {
        return CategoriesApi.getCategories().jsonPath().getInt("[0].id");
    }
}
