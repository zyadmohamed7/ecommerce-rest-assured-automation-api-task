package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.datagenerators.ProductDataGenerator;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for deleting products")
public class DeleteAProduct {
    @Test
    public void testDeleteProduct() {
        // 1. Create a product to delete
        Response createResponse = ProductsApi.createProduct(ProductDataGenerator.generateRandomProduct());
        createResponse.then().statusCode(201); 
        int productId = createResponse.jsonPath().getInt("id");

        // 2. Delete it
        ProductsApi.deleteProduct(String.valueOf(productId)).
                then()
                .statusCode(200)
                .log().ifValidationFails();
    }

    @Test
    public void testDeleteProductWithInvalidId() {
        ProductsApi.deleteProduct("0").
                then()
                .statusCode(400)
                .log().ifValidationFails();
    }

}
