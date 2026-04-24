package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.List;
import java.util.Random;

import static org.example.framework.apis.CategoriesApi.getCategories;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for retrieving products by category slug")
public class RetriveProductWithSlug {
    @Test
    public void testRetrieveProductWithSlug() {
        List<String> categorySlugs = getCategories().jsonPath().getList("slug");
        String validSlug = categorySlugs.get(new Random().nextInt(categorySlugs.size()));

        Response response = ProductsApi.getProductsByCategorySlug(validSlug);

        response.then()
                .statusCode(200)
                .body("category.slug", everyItem(equalTo(validSlug)))
                .log().ifValidationFails();
    }

    @Test
    public void testRetrieveProductWithInvalidSlug(){
        String invalidSlug = "non-existent-slug-" + System.currentTimeMillis();

        Response response = ProductsApi.getProductsByCategorySlug(invalidSlug);

        response.then()
                .statusCode(200)
                .body("size()", is(0))
                .log().ifValidationFails();
    }
}
