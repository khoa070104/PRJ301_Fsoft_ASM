package com.se4f7.prj301.model.request;

import com.se4f7.prj301.enums.StatusEnum;

public class WebSettingRequest {
    private String content;
    private String type;
    private String image;
    private StatusEnum status;

    // Getters and Setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public WebSettingRequest(String content, String type, String image, StatusEnum status) {
        this.content = content;
        this.type = type;
        this.image = image;
        this.status = status;
    }

    public WebSettingRequest() {
    }
}
