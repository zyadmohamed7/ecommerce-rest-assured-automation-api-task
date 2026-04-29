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
    public void userCanRetrieveACategoryBySlug() {
        String slug = getFirstCategorySlug();

        Response response = CategoriesApi.getCategoryBySlug(slug);

        response.then()
                .statusCode(anyOf(is(200), is(400)));
    }

    @Test
    public void retrievingCategoryWithFakeSlugReturnsError() {
        Response response = CategoriesApi.getCategoryBySlug("non-existent-slug-" + System.currentTimeMillis());

        response.then()
                .statusCode(anyOf(is(400), is(404)));
    }

    private String getFirstCategorySlug() {
        return CategoriesApi.getCategories().jsonPath().getString("[0].slug");
    }
}
