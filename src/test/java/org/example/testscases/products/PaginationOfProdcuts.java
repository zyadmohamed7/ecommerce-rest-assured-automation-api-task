package org.example.testscases.products;

import org.example.framework.apis.ProductsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for product pagination")
public class PaginationOfProdcuts {

    @Test
    public void userCanPaginateProductsWithLimit() {
        ProductsApi.Pagination(0, 2)
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .log().ifValidationFails();
    }

    @Test
    public void paginationWithInvalidLimitReturnsError() {
        ProductsApi.getProductsWithPagination("0", "abc")
                .then()
                .statusCode(400)
                .body("message", hasItem(containsString("must be a number")))
                .log().ifValidationFails();
    }
}
