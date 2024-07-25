package com.se4f7.prj301.model.response;

import com.se4f7.prj301.enums.StatusEnum;

public class WebSettingResponse {
    private Long id;
    private String content;
    private String type;

    public WebSettingResponse() {
    }

    public WebSettingResponse(Long id, String content, String type, String image, String createdDate, String updatedDate, String createdBy, String updatedBy, StatusEnum status) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.image = image;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    private String image;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
    private StatusEnum status;

    // Getters and Setters
}
