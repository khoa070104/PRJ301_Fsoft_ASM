package com.se4f7.prj301.model.request;

public class AdsModelRequest {
    private String[] images;
    private String position;
    private String width;
    private String height;
    private String url;
    private String status;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {  // Getter và Setter cho status
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
