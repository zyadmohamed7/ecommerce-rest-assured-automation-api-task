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
@Story("Tests for retrieving categories by slug")
public class GetCategoryBySlug {

    @Test
    public void testGetCategoryBySlug_Valid() {
        Response allCategories = CategoriesApi.getCategories();
        String slug = allCategories.jsonPath().getString("[0].slug");

        Response response = CategoriesApi.getCategoryBySlug(slug);

        response.then()
                .statusCode(200)
                .body("slug", equalTo(slug));
    }

    @Test
    public void testGetCategoryBySlug_Invalid() {
        Response response = CategoriesApi.getCategoryBySlug("non-existent-slug-" + System.currentTimeMillis());

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }
}
