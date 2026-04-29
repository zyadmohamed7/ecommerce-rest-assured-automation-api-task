package org.example.testscases.categories;

import io.restassured.response.Response;
import org.example.framework.apis.CategoriesApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Categories")
@Story("Tests for retrieving products within a category")
public class GetProductsByCategory {

    @Test
    public void userCanViewProductsInACategory() {
        int categoryId = getFirstCategoryId();

        Response response = CategoriesApi.getProductsByCategoryId(String.valueOf(categoryId));

        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void viewingProductsInNonExistentCategoryReturnsEmpty() {
        Response response = CategoriesApi.getProductsByCategoryId("999999");

        response.then()
                .statusCode(200)
                .body("size()", is(0));
    }

    private int getFirstCategoryId() {
        return CategoriesApi.getCategories().jsonPath().getInt("[0].id");
    }
}
