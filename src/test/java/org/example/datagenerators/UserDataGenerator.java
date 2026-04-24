package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.dto.requests.UserRequest;

public class UserDataGenerator {
    private static final Faker faker = new Faker();

    public static UserRequest createValidUser() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .avatar("https://picsum.photos/800")
                .build();
    }

    public static UserRequest createInvalidUser() {
        return UserRequest.builder()
                .name("") // Invalid: name is required
                .email("invalid-email") // Invalid email format
                .password("")
                .avatar("not-a-url")
                .build();
    }
}
