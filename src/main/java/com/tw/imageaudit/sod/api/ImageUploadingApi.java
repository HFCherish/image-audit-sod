package com.tw.imageaudit.sod.api;

import com.alibaba.fastjson.JSONObject;
import com.tw.imageaudit.sod.appservice.ApprovalService;
import com.tw.imageaudit.sod.appservice.ImageUploadingService;
import com.tw.imageaudit.sod.domain.Approval;
import com.tw.imageaudit.sod.domain.ApprovalRepo;
import com.tw.imageaudit.sod.domain.ApprovalStatus;
import com.tw.imageaudit.sod.domain.ImageUploading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@RestController
public class ImageUploadingApi {
    @Autowired
    ImageUploadingService imageUploadingService;

    @Autowired
    ApprovalService approvalService;

    @Autowired
    Routes routes;

    @Autowired
    ApprovalRepo approvalRepo;

    @PostMapping("/image-uploading")
    public ResponseEntity<JSONObject> upload(@RequestBody ImageUploading imageUploading) throws Exception {
        String imageId = imageUploadingService.upload(imageUploading);
        List<Approval> approvals = approvalService.getToApprovals(imageId);

        return ResponseEntity.created(URI.create(routes.imageUrl(imageId))).body(
                new JSONObject().fluentPut("links",
                        new JSONObject().fluentPut("image", imageId)
                                .fluentPut("approving", approvals.stream().map(a -> a.getId()).collect(Collectors.toList()))
                )
        );
    }

    @PatchMapping("/images/{imageId}/approvals/{approvalId}")
    public ResponseEntity<JSONObject> approve(@RequestBody JSONObject body,
                                              @PathVariable String imageId,
                                              @PathVariable String approvalId) {
        approvalService.approve(imageId, approvalId, ApprovalStatus.valueOf(body.getString("status").toUpperCase()));

        List<Approval> toApprovals = approvalService.getToApprovals(imageId);
        return ResponseEntity.ok(new JSONObject().fluentPut("links",
                new JSONObject().fluentPut("image", imageId)
                        .fluentPut("approving", toApprovals.stream().map(a -> a.getId()).collect(Collectors.toList()))
                )
        );
    }
}
