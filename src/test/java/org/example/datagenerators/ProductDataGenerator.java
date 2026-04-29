package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.dto.requests.ProductRequest;

import java.util.List;

public class ProductDataGenerator {

    private static final Faker faker = new Faker();


    public static ProductRequest aValidProduct() {
        return ProductRequest.builder()
                .title(faker.commerce().productName())
                .price(Double.parseDouble(faker.commerce().price(10.0, 1000.0)))
                .description(faker.lorem().sentence())
                .categoryId(1)
                .images(List.of(faker.internet().image()))
                .build();
    }


    public static ProductRequest generateProductWithTitle(String title) {
        return ProductRequest.builder()
                .title(title)
                .price(Double.parseDouble(faker.commerce().price(10.0, 1000.0)))
                .description(faker.lorem().sentence())
                .categoryId(faker.number().numberBetween(1, 10))
                .images(List.of(faker.internet().image()))
                .build();
    }

    public static ProductRequest generateProductWithPriceRange(double min, double max) {
        return ProductRequest.builder()
                .title(faker.commerce().productName())
                .price(Double.parseDouble(faker.commerce().price(min, max)))
                .description(faker.lorem().sentence())
                .categoryId(faker.number().numberBetween(1, 10))
                .images(List.of(faker.internet().image()))
                .build();
    }

    public static ProductRequest generateCustomProduct(String title, double price, int categoryId) {
        return ProductRequest.builder()
                .title(title)
                .price(price)
                .description(faker.lorem().sentence())
                .categoryId(categoryId)
                .images(List.of(faker.internet().image()))
                .build();
    }

    public static ProductRequest generateProductWithCustomLengths(int titleLen, int descLen) {
        return ProductRequest.builder()
                .title(faker.lorem().characters(titleLen))
                .price(100.0)
                .description(faker.lorem().characters(descLen))
                .categoryId(1)
                .images(List.of(faker.internet().image()))
                .build();
    }

    // invalid cases
    public static ProductRequest invalidProductWithEmptyTitle() {
        return ProductRequest.builder()
                .title("")
                .price(100.0)
                .description(faker.lorem().sentence())
                .categoryId(1)
                .images(List.of(faker.internet().image()))
                .build();
    }

    public static ProductRequest aProductWithNegativePrice() {
        return ProductRequest.builder()
                .title(faker.commerce().productName())
                .price(-50.0)
                .description(faker.lorem().sentence())
                .categoryId(1)
                .images(List.of(faker.internet().image()))
                .build();
    }


    public static ProductRequest invalidProductWithInvalidCategory() {
        return ProductRequest.builder()
                .title(faker.commerce().productName())
                .price(100.0)
                .description(faker.lorem().sentence())
                .categoryId(0)
                .images(List.of(faker.internet().image()))
                .build();
    }

    public static ProductRequest invalidProductWithEmptyImages() {
        return ProductRequest.builder()
                .title(faker.commerce().productName())
                .price(100.0)
                .description(faker.lorem().sentence())
                .categoryId(1)
                .images(List.of())
                .build();
    }

    // boundary
    public static ProductRequest generateProductWithLongTitle() {
        return ProductRequest.builder()
                .title(faker.lorem().characters(500))
                .price(100.0)
                .description(faker.lorem().sentence())
                .categoryId(1)
                .images(List.of(faker.internet().image()))
                .build();
    }
}