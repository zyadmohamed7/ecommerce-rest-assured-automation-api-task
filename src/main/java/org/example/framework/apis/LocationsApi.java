package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.example.dto.requests.LocationRequest;

public class LocationsApi {

    public static Response getLocations() {
        return ApiClient.send("GET", Routes.LOCATIONS.getPath(), null);
    }

    public static Response getLocation(String id) {
        return ApiClient.send("GET", Routes.LOCATIONS.getPath() + "/" + id, null);
    }

    public static Response createLocation(LocationRequest body) {
        return ApiClient.send("POST", Routes.LOCATIONS.getPath(), body);
    }

    public static Response updateLocation(String id, LocationRequest body) {
        return ApiClient.send("PUT", Routes.LOCATIONS.getPath() + "/" + id, body);
    }

    public static Response deleteLocation(String id) {
        return ApiClient.send("DELETE", Routes.LOCATIONS.getPath() + "/" + id, null);
    }
}
