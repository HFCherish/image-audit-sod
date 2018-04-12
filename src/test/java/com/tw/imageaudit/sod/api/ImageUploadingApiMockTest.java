package com.tw.imageaudit.sod.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tw.imageaudit.sod.domain.*;
import com.tw.imageaudit.sod.domain.util.IdGenerator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public class ImageUploadingApiMockTest extends ApiTest {
    @MockBean
    ImageRepo imageRepo;

    @Autowired
    ApprovalRepo approvalRepo;

    @Test
    public void should_upload_right() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        uploadImage(generalImageUploading())
                .body("links.image", is(imageId))
                .body("links.approving.size", is(1));

        approvalRepo.deleteByImageId(imageId);
    }

    private ValidatableResponse uploadImage(ImageUploading uploading) {
        return given()
                .contentType(ContentType.JSON)
                .body(uploading)

                .when()
                .post("/image-uploading")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^/images/.*$"));
    }

    private ImageUploading generalImageUploading() {
        return new ImageUploading(new Image("imagedata from sod", "imagename from sod"));
    }

    @Test
    public void should_upload_right_with_group() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        uploadImage(groupUploading())
                .body("links.image", is(imageId))
                .body("links.approving.size", is(2));

        approvalRepo.deleteByImageId(imageId);
    }

    private ImageUploading groupUploading() {
        return new ImageUploading(new Image("imagedata from sod", "imagename from sod")).setGroup(new Group("groupname"));
    }

    @Test
    public void should_general_approve() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        JSONObject uploadBody = uploadImage(generalImageUploading())
                .body("links.approving.size", is(1))

                .extract()
                .body()
                .as(JSONObject.class);

        String approvingId = uploadBody.getJSONObject("links").getJSONArray("approving").getString(0);

        approve(imageId, approvingId)
                .body("links.approving.size", is(0));
        verify(imageRepo).approve(imageId);

        approvalRepo.deleteByImageId(imageId);
    }

    @Test
    public void should_group_approve() throws Exception {
        String imageId = IdGenerator.next();
        when(imageRepo.save(any(Image.class))).thenReturn(imageId);
        JSONObject uploadBody = uploadImage(groupUploading())
                .body("links.approving.size", is(2))

                .extract()
                .body()
                .as(JSONObject.class);

        JSONArray approveIds = uploadBody.getJSONObject("links").getJSONArray("approving");

        approve(imageId, approveIds.get(0))
                .body("links.approving.size", is(1));

        approve(imageId, approveIds.get(1))
                .body("links.approving.size", is(0));
        verify(imageRepo).approve(imageId);

        approvalRepo.deleteByImageId(imageId);
    }

    private ValidatableResponse approve(String imageId, Object approvalId) {
        return given()
                .contentType(ContentType.JSON)
                .body(new HashMap() {{
                    put("status", ApprovalStatus.APPROVE.name());
                }})

                .when()
                .patch("/images/" + imageId + "/approvals/" + approvalId)

                .then()
                .statusCode(200)
                .body("links.image", is(imageId));
    }


}