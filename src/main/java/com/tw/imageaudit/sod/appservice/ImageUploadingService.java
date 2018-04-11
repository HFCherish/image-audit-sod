package com.tw.imageaudit.sod.appservice;

import com.tw.imageaudit.sod.domain.ImageRepo;
import com.tw.imageaudit.sod.domain.ImageUploading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@Service
public class ImageUploadingService {
    @Autowired
    ImageRepo imageRepo;

    public String upload(ImageUploading imageUploading) {
        return imageRepo.save(imageUploading.getImage());
    }
}
