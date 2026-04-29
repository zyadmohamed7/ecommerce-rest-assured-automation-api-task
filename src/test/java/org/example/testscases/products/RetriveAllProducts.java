package org.example.testscases.products;

import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for retrieving all products")
public class RetriveAllProducts {

    @Test
    public void userCanRetrieveAllProducts() {
        ProductsApi.getProduct()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .log().ifValidationFails();
    }
}
