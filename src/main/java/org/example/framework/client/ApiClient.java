package org.example.framework.client;

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
                    .filter(new io.qameta.allure.restassured.AllureRestAssured())
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
        }, 1, 3000);
    }
}