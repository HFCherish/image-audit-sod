package com.tw.imageaudit.sod.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ImageUploading implements Serializable {
    private Image image;
    private Group group;

    private ImageUploading() {
    }

    public ImageUploading(Image image) {
        this.image = image;
    }

    public ImageUploading setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Image getImage() {
        return image;
    }

    public Group getGroup() {
        return group;
    }

    public boolean hasGroup() {
        return group != null;
    }
}
