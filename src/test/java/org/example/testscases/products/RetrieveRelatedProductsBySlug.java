package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class RetrieveRelatedProductsBySlug {

    @Test
    public void testRetrieveRelatedBySlug_Valid() {
        Response allProducts = ProductsApi.getProduct();
        String productSlug = allProducts.jsonPath().getString("[0].slug");

        Response response = ProductsApi.getRelatedProductsBySlug(productSlug);

        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testRetrieveRelatedBySlug_Invalid() {
        String invalidSlug = "non-existent-slug-" + System.currentTimeMillis();

        Response response = ProductsApi.getRelatedProductsBySlug(invalidSlug);

        response.then()
                .statusCode(400)
                .body("name", equalTo("EntityNotFoundError"));
    }
}
