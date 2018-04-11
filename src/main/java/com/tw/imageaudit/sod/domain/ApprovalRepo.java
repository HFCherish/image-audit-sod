package com.tw.imageaudit.sod.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public interface ApprovalRepo extends JpaRepository<Approval, String>{
    List<Approval> findByImageIdAndStatus(String imageId, ApprovalStatus status);
}
