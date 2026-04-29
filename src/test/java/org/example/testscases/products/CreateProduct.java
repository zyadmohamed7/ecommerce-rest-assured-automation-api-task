package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.dto.requests.ProductRequest;
import org.example.dto.responses.ProductResponse;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.util.List;
import java.util.Random;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.example.datagenerators.ProductDataGenerator.aValidProduct;
import static org.example.datagenerators.ProductDataGenerator.aProductWithNegativePrice;
import static org.example.framework.apis.CategoriesApi.getCategories;
import static org.example.framework.apis.ProductsApi.createProduct;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("E-Commerce API")
@Feature("Product Management")
@Story("Tests for creating products")
public class CreateProduct {

    @Test
    public void userCanCreateANewProduct() {
        int categoryId = pickRandomCategoryId();
        ProductRequest product = aValidProduct();
        product.setCategoryId(categoryId);

        Response response = createProduct(product);

        verifyProductCreatedSuccessfully(response, product);
    }

    @Test
    public void userCannotCreateProductWithNegativePrice() {
        ProductRequest product = aProductWithNegativePrice();

        Response response = createProduct(product);

        response.then()
                .statusCode(400);
    }

    private int pickRandomCategoryId() {
        List<Integer> categoryIds = getCategories().jsonPath().getList("id");
        return categoryIds.get(new Random().nextInt(categoryIds.size()));
    }

    private void verifyProductCreatedSuccessfully(Response response, ProductRequest expected) {
        response.then()
                .statusCode(201)
                .body("title", equalTo(expected.getTitle()))
                .body("price", equalTo((float) expected.getPrice().doubleValue()))
                .body("description", equalTo(expected.getDescription()))
                .body("id", notNullValue())
                .body(matchesJsonSchemaInClasspath("schemas/productSchema.json"))
                .extract()
                .as(ProductResponse.class);
    }
}
