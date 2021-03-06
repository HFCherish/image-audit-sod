package com.tw.imageaudit.sod.domain;

import com.tw.imageaudit.sod.domain.util.IdGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

/**
 * @author hf_cherish
 * @date 4/11/18
 */

@Entity
@Table(name = "approvals")
public class Approval {
    @Id
    private String id;
    @Column(name = "imageid")
    private String imageId;
    private ApprovalStatus status;
    private ApprovalType type;

    @Column(name = "create_at")
    private long createAt;
    @Column(name = "update_at")
    private long updateAt;

    private Approval() {}

    public String getId() {
        return id;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public Approval(String imageId, ApprovalType approvalType) {
        this.id = IdGenerator.next();
        this.imageId = imageId;
        this.status = ApprovalStatus.NONE;
        this.createAt = Instant.now().toEpochMilli();
        this.updateAt = Instant.now().toEpochMilli();
        this.type = approvalType;
    }

    public Approval updateStatus(ApprovalStatus status) {
        if (!this.status.equals(ApprovalStatus.NONE)) {
            throw new RuntimeException("conflict! already audited. status is: " + this.status);
        }

        this.status = status;
        return this;
    }
}
