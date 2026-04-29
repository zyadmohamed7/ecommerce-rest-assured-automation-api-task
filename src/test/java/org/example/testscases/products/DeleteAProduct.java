package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.example.datagenerators.ProductDataGenerator.aValidProduct;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for deleting products")
public class DeleteAProduct {

    @Test
    public void userCanDeleteAnExistingProduct() {
        int productId = createProductAndReturnId();

        Response response = ProductsApi.deleteProduct(String.valueOf(productId));

        response.then()
                .statusCode(200)
                .log().ifValidationFails();

        // confirm that the product has been deleted successfully
        ProductsApi.getProductWithId(String.valueOf(productId))
                .then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotDeleteProductWithInvalidId() {
        Response response = ProductsApi.deleteProduct("0");

        response.then()
                .statusCode(400)
                .log().ifValidationFails();
    }

    private int createProductAndReturnId() {
        Response response = ProductsApi.createProduct(aValidProduct());
        response.then().statusCode(201);
        return response.jsonPath().getInt("id");
    }
}
