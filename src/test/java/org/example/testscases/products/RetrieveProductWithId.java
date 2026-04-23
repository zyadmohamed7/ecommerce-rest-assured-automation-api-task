package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class RetrieveProductWithId {
    @Test
    public void testRetrieveProductWithId() {
        // 1. Fetch a real product ID from the list
        Response allProducts = ProductsApi.getProduct();
        int productId = allProducts.jsonPath().getInt("[0].id");

        // 2. Retrieve it
        ProductsApi.getProductWithId(String.valueOf(productId))
                .then()
                .statusCode(200)
                .body("id", is(productId))
                .log().ifValidationFails();
    }

    @Test
    public void testRetrieveProductWithInvalidId() {

        ProductsApi.getProductWithId("0")
                .then()
                .statusCode(400)
                .log().ifValidationFails();
    }
}
