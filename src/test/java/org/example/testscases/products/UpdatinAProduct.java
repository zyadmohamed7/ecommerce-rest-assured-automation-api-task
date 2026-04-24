package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.datagenerators.ProductDataGenerator;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for updating products")
public class UpdatinAProduct {
    @Test
    public void testUpdateProduct() {
        // 1. Fetch a real product ID from the list
        Response allProducts = ProductsApi.getProduct();
        int productId = allProducts.jsonPath().getInt("[0].id");

        // 2. Update it
        ProductsApi.updateProduct(String.valueOf(productId), ProductDataGenerator.generateRandomProduct())
                .then()
                .statusCode(200)
                .body("id", is(productId))
                .log().ifValidationFails();
    }

    @Test
    public void testUpdateProductWithInvalidId() {
        ProductsApi.updateProduct("0", ProductDataGenerator.generateRandomProduct())
                .then()
                .statusCode(400)
                .log().ifValidationFails();
    }

}
