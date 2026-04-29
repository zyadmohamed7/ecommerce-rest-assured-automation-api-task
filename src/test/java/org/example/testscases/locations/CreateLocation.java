package org.example.testscases.locations;

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
@Story("Tests for creating locations")
public class CreateLocation {

    @Test
    public void userCanCreateANewLocation() {
        LocationRequest location = LocationDataGenerator.createValidLocation();

        LocationsApi.createLocation(location)
                .then()
                .statusCode(anyOf(is(201), is(200), is(404)))
                .log().ifValidationFails();
    }

    @Test
    public void userCannotCreateLocationWithInvalidData() {
        LocationRequest location = LocationDataGenerator.createInvalidLocation();

        LocationsApi.createLocation(location)
                .then()
                .statusCode(anyOf(is(400), is(500), is(422), is(404)))
                .log().ifValidationFails();
    }
}
