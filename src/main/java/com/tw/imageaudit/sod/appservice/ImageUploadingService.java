package com.tw.imageaudit.sod.appservice;

import com.tw.imageaudit.sod.domain.Approval;
import com.tw.imageaudit.sod.domain.ApprovalRepo;
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

    @Autowired
    ApprovalRepo approvalRepo;

    public String upload(ImageUploading imageUploading) throws Exception {
        String imageId = imageRepo.save(imageUploading.getImage());

        Approval approval = new Approval(imageId);
        approvalRepo.save(approval);
        return imageId;
    }
}
