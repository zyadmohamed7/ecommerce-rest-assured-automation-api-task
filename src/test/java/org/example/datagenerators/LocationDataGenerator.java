package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.dto.requests.LocationRequest;

public class LocationDataGenerator {
    private static final Faker faker = new Faker();

    public static LocationRequest createValidLocation() {
        return LocationRequest.builder()
                .name(faker.company().name())
                .address(faker.address().fullAddress())
                .build();
    }

    public static LocationRequest createInvalidLocation() {
        return LocationRequest.builder()
                .name("") // Invalid: name missing
                .address("")
                .build();
    }
}
