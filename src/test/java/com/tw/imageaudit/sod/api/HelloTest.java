package com.tw.imageaudit.sod.api;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public class HelloTest extends ApiTest {

    @Test
    public void should_echo() {
        given()
                .contentType(ContentType.JSON)

                .when()
                .get("/echo")

                .then()
                .statusCode(200)
                .body(is("hello, this is image sod"));
    }
}