package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.dto.requests.CategoryRequest;

public class CategoryDataGenerator {
    private static final Faker faker = new Faker();

    public static CategoryRequest generateRandomCategory() {
        return CategoryRequest.builder()
                .name(faker.commerce().department() + " " + System.currentTimeMillis())
                .image(faker.internet().image())
                .build();
    }

    public static CategoryRequest generateCategoryWithName(String name) {
        return CategoryRequest.builder()
                .name(name)
                .image(faker.internet().image())
                .build();
    }

    public static CategoryRequest invalidCategoryMissingName() {
        return CategoryRequest.builder()
                .image(faker.internet().image())
                .build();
    }

    public static CategoryRequest invalidCategoryMissingImage() {
        return CategoryRequest.builder()
                .name(faker.commerce().department())
                .build();
    }
}
