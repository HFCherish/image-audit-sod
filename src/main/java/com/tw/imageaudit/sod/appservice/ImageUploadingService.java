package com.tw.imageaudit.sod.appservice;

import com.tw.imageaudit.sod.domain.*;
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

        approvalRepo.save(new Approval(imageId, ApprovalType.GENERAL));

        if (imageUploading.hasGroup()) {
            approvalRepo.save(new Approval(imageId, ApprovalType.GROUP));
        }
        return imageId;
    }
}
