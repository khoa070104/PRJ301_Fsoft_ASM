package com.se4f7.prj301.model.response;

import com.se4f7.prj301.model.BaseModel;

public class AdsModelResponse extends BaseModel {
    private String position;
    private String width;
    private String height;
    private String uri;
    private String[] images;

    // Getters and Setters
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
