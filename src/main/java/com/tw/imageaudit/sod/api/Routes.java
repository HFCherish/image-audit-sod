package com.tw.imageaudit.sod.api;

import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@Component
public class Routes {
    public String imageUrl(String id) {
        return "/images/" + id;
    }

    public String approvalUrl(String imageId, String approvalId) {
        return imageUrl(imageId) + "/" + approvalId;
    }
}
