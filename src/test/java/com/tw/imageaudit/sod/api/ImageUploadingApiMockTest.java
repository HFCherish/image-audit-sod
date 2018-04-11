package com.tw.imageaudit.sod.api;

import com.tw.imageaudit.sod.domain.Group;
import com.tw.imageaudit.sod.domain.Image;
import com.tw.imageaudit.sod.domain.ImageRepo;
import com.tw.imageaudit.sod.domain.ImageUploading;
import com.tw.imageaudit.sod.domain.util.IdGenerator;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public class ImageUploadingApiMockTest extends ApiTest {
    @MockBean
    ImageRepo imageRepo;

    @Test
    public void should_upload_right() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        given()
                .contentType(ContentType.JSON)
                .body(new ImageUploading(new Image("imagedata from sod", "imagename from sod")))

                .when()
                .post("/image-uploading")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^/images/.*$"))
                .body("links.image", is(imageId))
                .body("links.approving.size", is(1));
    }

    @Test
    public void should_upload_right_with_group() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        given()
                .contentType(ContentType.JSON)
                .body(new ImageUploading(new Image("imagedata from sod", "imagename from sod")).setGroup(new Group("groupname")))

                .when()
                .post("/image-uploading")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^/images/.*$"))
                .body("links.image", is(imageId))
                .body("links.approving.size", is(2));
    }
}