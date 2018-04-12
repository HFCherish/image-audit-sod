package com.tw.imageaudit.sod.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
public interface ApprovalRepo extends JpaRepository<Approval, String>{
    List<Approval> findByImageIdAndStatus(String imageId, ApprovalStatus status);

    @Transactional
    void deleteByImageId(String imageId);

    Optional<Approval> findByIdAndImageId(String id, String imageId);
}
