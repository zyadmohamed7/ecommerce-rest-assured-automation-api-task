package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for retrieving related products by slug")
public class RetrieveRelatedProductsBySlug {

    @Test
    public void userCanRetrieveRelatedProductsBySlug() {
        String productSlug = getFirstProductSlug();

        Response response = ProductsApi.getRelatedProductsBySlug(productSlug);

        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void relatedProductsForNonExistentSlugReturnsError() {
        String invalidSlug = "non-existent-slug-" + System.currentTimeMillis();
        Response response = ProductsApi.getRelatedProductsBySlug(invalidSlug);

        response.then()
                .statusCode(400)
                .body("name", equalTo("EntityNotFoundError"));
    }

    private String getFirstProductSlug() {
        return ProductsApi.getProduct().jsonPath().getString("[0].slug");
    }
}
