package org.example.testscases.products;

import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

public class PaginationOfProdcuts {
    @Test
    public void testPagination() {
        ProductsApi.Pagination(0, 2)
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .log().ifValidationFails();
    }

    @Test
    public void testRetrieveProductWithInvalidPagination(){
        ProductsApi.getProductsWithPagination("0", "abc")
                .then()
                .statusCode(400)
                .body("message", hasItem(containsString("must be a number")))
                .log().ifValidationFails();
    }

}
