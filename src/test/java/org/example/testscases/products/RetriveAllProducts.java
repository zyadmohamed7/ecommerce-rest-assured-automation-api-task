package org.example.testscases.products;

import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;

public class RetriveAllProducts {
    @Test
    public void testRetrieveAllProducts() {
        ProductsApi.getProduct()
                .then()
                .statusCode(200)
                .log().ifValidationFails();
    }
}
