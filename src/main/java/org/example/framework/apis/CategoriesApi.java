package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;

import org.example.dto.requests.CategoryRequest;

public class CategoriesApi {
    public static Response getCategories() {
        return ApiClient.send("GET", Routes.CATEGORIES.getPath(), null);
    }

    public static Response createCategory(CategoryRequest body) {
        return ApiClient.send("POST", Routes.CATEGORIES.getPath(), body);
    }

    public static Response getCategoryById(String id) {
        return ApiClient.send("GET", Routes.CATEGORIES.getPath() + "/" + id, null);
    }

    public static Response getCategoryBySlug(String slug) {
        return ApiClient.send("GET", Routes.CATEGORIES.getPath() + "/slug/" + slug, null);
    }

    public static Response updateCategory(String id, CategoryRequest body) {
        return ApiClient.send("PUT", Routes.CATEGORIES.getPath() + "/" + id, body);
    }

    public static Response deleteCategory(String id) {
        return ApiClient.send("DELETE", Routes.CATEGORIES.getPath() + "/" + id, null);
    }

    public static Response getProductsByCategoryId(String id) {
        return ApiClient.send("GET", Routes.CATEGORIES.getPath() + "/" + id + "/products", null);
    }
}
