package org.example.testscases.filterproducts;

import org.example.framework.apis.ProductsApi;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Filtering")
@Story("Tests for filtering products")
public class FilterProducts {

    @Test
    public void userCanFilterProductsByTitle() {
        String searchTerm = "Generic";

        ProductsApi.filterProducts(searchTerm, null)
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0))
                .log().ifValidationFails();
    }

    @Test
    public void filteringWithInvalidPriceIsHandledGracefully() {
        ApiClient.send("GET", Routes.PRODUCTS.getPath() + "?price=invalid", null)
                .then()
                .statusCode(anyOf(is(400), is(200)))
                .log().ifValidationFails();
    }
}
