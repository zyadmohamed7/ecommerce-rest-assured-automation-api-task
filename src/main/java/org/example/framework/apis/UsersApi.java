package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.example.dto.requests.UserRequest;

public class UsersApi {

    public static Response getUsers() {
        return ApiClient.send("GET", Routes.USERS.getPath(), null);
    }

    public static Response createUser(UserRequest body) {
        return ApiClient.send("POST", Routes.USERS.getPath(), body);
    }

    public static Response getUser(String id) {
        return ApiClient.send("GET", Routes.USERS.getPath() + "/" + id, null);
    }

    public static Response updateUser(String id, UserRequest body) {
        return ApiClient.send("PUT", Routes.USERS.getPath() + "/" + id, body);
    }

    public static Response deleteUser(String id) {
        return ApiClient.send("DELETE", Routes.USERS.getPath() + "/" + id, null);
    }
}
