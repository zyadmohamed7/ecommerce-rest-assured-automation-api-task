package org.example.testscases.products;

import io.restassured.response.Response;
import org.example.dto.requests.ProductRequest;
import org.example.dto.responses.ProductResponse;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.example.datagenerators.ProductDataGenerator.generateRandomProduct;
import static org.example.datagenerators.ProductDataGenerator.invalidProductWithNegativePrice;
import static org.example.framework.apis.CategoriesApi.getCategories;
import static org.example.framework.apis.ProductsApi.createProduct;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateProduct {
    @Test
    public void testCreateProduct() {
        List<Integer> categoryIds = getCategories().jsonPath().getList("id");
        int validCategoryId = categoryIds.get(new Random().nextInt(categoryIds.size()));

        ProductRequest product = generateRandomProduct();
        product.setCategoryId(validCategoryId);

        Response response = createProduct(product);

        response.then()
                .statusCode(201)
                .body("title", equalTo(product.getTitle()))
                .body("price", equalTo((float) product.getPrice().doubleValue()))
                .body("description", equalTo(product.getDescription()))
                .body("id", notNullValue())
                .body(matchesJsonSchemaInClasspath("productSchema.json"))
                .extract()
                .as(ProductResponse.class);
    }



    @Test
    public void testCreateProductWithNegativePrice() {
        ProductRequest product = invalidProductWithNegativePrice();

        Response response = createProduct(product);

        response.then()
                .statusCode(400);
    }
}
