package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for retrieving products by ID")
public class RetrieveProductWithId {

    @Test
    public void userCanRetrieveAProductById() {
        int productId = getFirstProductId();

        Response response = ProductsApi.getProductWithId(String.valueOf(productId));

        response.then()
                .statusCode(200)
                .body("id", is(productId))
                .body(matchesJsonSchemaInClasspath("schemas/productSchema.json"))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotRetrieveProductWithInvalidId() {
        Response response = ProductsApi.getProductWithId("0");

        response.then()
                .statusCode(400)
                .log().ifValidationFails();
    }

    private int getFirstProductId() {
        return ProductsApi.getProduct().jsonPath().getInt("[0].id");
    }
}
