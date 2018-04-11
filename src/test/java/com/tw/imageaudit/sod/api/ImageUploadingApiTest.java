package com.tw.imageaudit.sod.api;

import com.tw.imageaudit.sod.domain.Image;
import com.tw.imageaudit.sod.domain.ImageUploading;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public class ImageUploadingApiTest extends ApiTest {
    @Test
    public void should_upload_right() {
        given()
                .contentType(ContentType.JSON)
                .body(new ImageUploading(new Image("imagedata from sod", "imagename from sod")))

                .when()
                .post("/image-uploading")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^/images/.*$"));
    }
}