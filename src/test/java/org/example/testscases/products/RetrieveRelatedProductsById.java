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
    public void userCanRetrieveRelatedProductsById() {
        int productId = getFirstProductId();

        Response response = ProductsApi.getRelatedProductsById(String.valueOf(productId));

        response.then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void relatedProductsForNonExistentIdReturnsError() {
        Response response = ProductsApi.getRelatedProductsById("999999");

        response.then()
                .statusCode(400)
                .body("name", equalTo("EntityNotFoundError"));
    }

    private int getFirstProductId() {
        return ProductsApi.getProduct().jsonPath().getInt("[0].id");
    }
}
