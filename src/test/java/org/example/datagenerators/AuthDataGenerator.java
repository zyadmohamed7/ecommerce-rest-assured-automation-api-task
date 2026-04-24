package org.example.datagenerators;

import org.example.dto.requests.LoginRequest;

public class AuthDataGenerator {

    public static LoginRequest createValidLogin() {
        return LoginRequest.builder()
                .email("john@mail.com")
                .password("changeme")
                .build();
    }

    public static LoginRequest createInvalidLogin() {
        return LoginRequest.builder()
                .email("invalid@mail.com")
                .password("wrongpassword")
                .build();
    }
}
