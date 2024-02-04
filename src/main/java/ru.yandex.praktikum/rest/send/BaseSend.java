package ru.yandex.praktikum.rest.send;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static ru.yandex.praktikum.rest.config.ApiConfig.BASE_URL;
import static io.restassured.RestAssured.given;

public class BaseSend {
    protected RequestSpecification getDefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }
}
