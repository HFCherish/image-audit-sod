package com.tw.imageaudit.sod.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Group implements Serializable {
    private String name;

    private Group() {
    }

    public Group(String name) {
        this.name = name;
    }
}
