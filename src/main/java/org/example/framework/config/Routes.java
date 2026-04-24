package org.example.framework.config;

import lombok.Getter;

@Getter
public enum Routes {
    PRODUCTS("/api/v1/products"),
    CATEGORIES("/api/v1/categories"),
    USERS("/api/v1/users"),
    AUTH_LOGIN("/api/v1/auth/login"),
    AUTH_PROFILE("/api/v1/auth/profile"),
    FILES_UPLOAD("/api/v1/files/upload"),
    LOCATIONS("/api/v1/locations");

    private final String path;

    Routes(String path) {
        this.path = path;
    }

}
