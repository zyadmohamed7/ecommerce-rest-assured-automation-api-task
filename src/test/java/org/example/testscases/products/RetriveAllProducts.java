package org.example.testscases.products;

import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for retrieving all products")
public class RetriveAllProducts {
    @Test
    public void testRetrieveAllProducts() {
        ProductsApi.getProduct()
                .then()
                .statusCode(200)
                .log().ifValidationFails();
    }
}
