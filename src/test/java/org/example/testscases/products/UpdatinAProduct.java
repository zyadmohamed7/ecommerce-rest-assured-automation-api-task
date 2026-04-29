package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.example.dto.requests.ProductRequest;
import static org.example.datagenerators.ProductDataGenerator.aValidProduct;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for updating products")
public class UpdatinAProduct {

    @Test
    public void userCanUpdateAnExistingProduct() {
        int productId = getFirstProductId();
        ProductRequest updateData = aValidProduct();

        Response response = ProductsApi.updateProduct(
                String.valueOf(productId), updateData);

        response.then()
                .statusCode(200)
                .body("id", is(productId))
                .log().ifValidationFails();

        // confirm that the product has been updated successfully
        ProductsApi.getProductWithId(String.valueOf(productId))
                .then()
                .statusCode(200)
                .body("title", equalTo(updateData.getTitle()))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotUpdateProductWithInvalidId() {
        Response response = ProductsApi.updateProduct("0", aValidProduct());

        response.then()
                .statusCode(400)
                .log().ifValidationFails();
    }

    private int getFirstProductId() {
        return ProductsApi.getProduct().jsonPath().getInt("[0].id");
    }
}
