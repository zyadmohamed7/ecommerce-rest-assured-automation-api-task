package org.example.framework.client;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.example.framework.config.RequestSpecFactory;
import io.restassured.specification.RequestSpecification;
import org.example.framework.utils.RetryHandler;

import static io.restassured.RestAssured.given;

public class ApiClient {

    public static Response send(String method, String endpoint, Object body) {
        return RetryHandler.handle(() -> {
            RequestSpecification request = given()
                    .spec(RequestSpecFactory.getRequestSpec())
                    .filter(new AllureRestAssured())
                    .log().ifValidationFails();

            if (body != null) {
                request.body(body);
            }

            return request
                    .when()
                    .request(method, endpoint)
                    .then()
                    .log().ifValidationFails()
                    .spec(RequestSpecFactory.getResponseSpec())
                    .extract()
                    .response();
        }, 2, 3000);
    }

    public static Response sendMultipart(String method, String endpoint, java.io.File file) {
        return RetryHandler.handle(() -> {
            RequestSpecification request = given()
                    .spec(RequestSpecFactory.getRequestSpec())
                    .filter(new AllureRestAssured())
                    .contentType("multipart/form-data")
                    .log().ifValidationFails();

            if (file != null) {
                request.multiPart("file", file);
            }

            return request
                    .when()
                    .request(method, endpoint)
                    .then()
                    .log().ifValidationFails()
                    .spec(RequestSpecFactory.getResponseSpec())
                    .extract()
                    .response();
        }, 2, 3000);
    }
}