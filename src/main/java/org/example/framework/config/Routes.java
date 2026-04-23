package org.example.framework.config;

import lombok.Getter;

@Getter
public enum Routes {
    PRODUCTS("/api/v1/products"),
    CATEGORIES("/api/v1/categories");

    private final String path;

    Routes(String path) {
        this.path = path;
    }

}
