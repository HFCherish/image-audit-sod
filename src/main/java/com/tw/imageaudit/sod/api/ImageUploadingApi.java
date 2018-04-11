package com.tw.imageaudit.sod.api;

import com.tw.imageaudit.sod.appservice.ImageUploadingService;
import com.tw.imageaudit.sod.domain.ImageUploading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@RestController
public class ImageUploadingApi {
    @Autowired
    ImageUploadingService imageUploadingService;

    @PostMapping("/image-uploading")
    public ResponseEntity<String> upload(@RequestBody ImageUploading imageUploading) {
        String imageUrl = imageUploadingService.upload(imageUploading);
        return ResponseEntity.created(URI.create(imageUrl)).build();
    }
}
