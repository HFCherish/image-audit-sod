package com.tw.imageaudit.sod.appservice;

import com.tw.imageaudit.sod.domain.Approval;
import com.tw.imageaudit.sod.domain.ApprovalRepo;
import com.tw.imageaudit.sod.domain.ApprovalStatus;
import com.tw.imageaudit.sod.domain.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hf_cherish
 * @date 4/12/18
 */
@Service
public class ApprovalService {
    @Autowired
    ApprovalRepo approvalRepo;

    @Autowired
    ImageRepo imageRepo;

    public void approve(String imageId, String approvalId, ApprovalStatus status) {
        List<Approval> toApprovals = getToApprovals(imageId);


        Approval originalApproval = toApprovals.stream().filter(a -> a.getId().equals(approvalId)).findFirst().orElseThrow(() -> new RuntimeException("approval not exists or already audited"));

        Approval approval = originalApproval.updateStatus(status);

        if (toApprovals.size() == 1) {
            imageRepo.approve(imageId);
        }

        approvalRepo.save(approval);
    }

    public List<Approval> getToApprovals(String imageId) {
        return approvalRepo.findByImageIdAndStatus(imageId, ApprovalStatus.NONE);
    }
}
