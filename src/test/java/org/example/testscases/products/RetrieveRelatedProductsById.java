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
@Story("Tests for retrieving related products by ID")
public class RetrieveRelatedProductsById {

    @Test
    public void testRetrieveRelatedById_Valid() {
        Response allProducts = ProductsApi.getProduct();
        int productId = allProducts.jsonPath().getInt("[0].id");

        Response response = ProductsApi.getRelatedProductsById(String.valueOf(productId));

        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void testRetrieveRelatedById_Invalid() {
        String invalidId = "999999";

        Response response = ProductsApi.getRelatedProductsById(invalidId);

        response.then()
                .statusCode(400)
                .body("name", equalTo("EntityNotFoundError"));
    }
}
