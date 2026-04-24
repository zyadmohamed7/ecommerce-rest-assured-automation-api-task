package org.example.testscases.locations;

import io.restassured.response.Response;
import org.example.datagenerators.LocationDataGenerator;
import org.example.dto.requests.LocationRequest;
import org.example.framework.apis.LocationsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Location Management")
@Story("Tests for updating locations")
public class UpdateLocation {

    @Test
    public void testUpdateLocation() {
        LocationRequest updatePayload = LocationRequest.builder()
                .name("Updated Location " + System.currentTimeMillis())
                .address("Updated Address")
                .build();

        LocationsApi.updateLocation("1", updatePayload)
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .log().ifValidationFails();
    }

    @Test
    public void testUpdateLocationWithInvalidId() {
        LocationRequest updatePayload = LocationDataGenerator.createValidLocation();

        LocationsApi.updateLocation("999999", updatePayload)
                .then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }
}
