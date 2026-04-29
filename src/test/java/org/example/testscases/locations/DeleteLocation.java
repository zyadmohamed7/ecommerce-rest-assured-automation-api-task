package org.example.testscases.locations;

import org.example.framework.apis.LocationsApi;
import org.testng.annotations.Test;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import static org.hamcrest.Matchers.*;

@Epic("E-Commerce API")
@Feature("Location Management")
@Story("Tests for deleting locations")
public class DeleteLocation {

    @Test
    public void userCanDeleteALocation() {
        LocationsApi.deleteLocation("1")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .log().ifValidationFails();
    }

    @Test
    public void deletingNonExistentLocationReturnsError() {
        LocationsApi.deleteLocation("999999")
                .then()
                .statusCode(anyOf(is(400), is(404)))
                .log().ifValidationFails();
    }
}
