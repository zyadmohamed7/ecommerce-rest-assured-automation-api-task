package org.example.framework.apis;

import io.restassured.response.Response;
import org.example.framework.client.ApiClient;
import org.example.framework.config.Routes;
import org.example.dto.requests.ProductRequest;

public class ProductsApi {

    public static Response createProduct(ProductRequest body) {
        return ApiClient.send("POST", Routes.PRODUCTS.getPath(), body);
    }

    public static Response getProduct() {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath(), null);
    }

    public static Response getProductWithId(String id) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "/" + id, null);
    }

    public static Response getProductsByCategorySlug(String slug) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "?categorySlug=" + slug, null);
    }

    public static Response getProductsWithPagination(String offset, String limit) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "?offset=" + offset + "&limit=" + limit, null);
    }

    public static Response getRelatedProductsById(String id) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "/" + id + "/related", null);
    }

    public static Response getRelatedProductsBySlug(String slug) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "/slug/" + slug + "/related", null);
    }

    public static Response updateProduct(String id, ProductRequest body) {
        return ApiClient.send("PUT", Routes.PRODUCTS.getPath() + "/" + id, body);
    }

    public static Response deleteProduct(String id) {
        return ApiClient.send("DELETE", Routes.PRODUCTS.getPath() + "/" + id, null);
    }

    public static Response Pagination(int offset, int limit) {
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + "?offset=" + offset + "&limit=" + limit, null);
    }

    public static Response filterProducts(String title, Integer price) {
        String queryParams = "";
        if (title != null && !title.isEmpty()) {
            queryParams += "?title=" + title;
        }
        if (price != null) {
            queryParams += (queryParams.isEmpty() ? "?" : "&") + "price=" + price;
        }
        return ApiClient.send("GET", Routes.PRODUCTS.getPath() + queryParams, null);
    }
}