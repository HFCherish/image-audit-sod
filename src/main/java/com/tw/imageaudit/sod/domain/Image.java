package com.tw.imageaudit.sod.domain;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author hf_cherish
 * @date 4/9/18
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Image {
    private String data;
    private String name;
    private String description;

    private Image() {
    }

    public Image(String data, String name) {
        this.data = data;
        this.name = name;
    }

    public Image setDescription(String description) {
        this.description = description;
        return this;
    }


    public static Image build(JSONObject image) {
        return new Image(image.getString("data"), image.getString("name"))
                .setDescription(image.getString("desc"));
    }
}
