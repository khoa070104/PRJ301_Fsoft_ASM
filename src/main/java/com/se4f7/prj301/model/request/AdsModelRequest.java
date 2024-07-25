package com.se4f7.prj301.model.request;

public class AdsModelRequest {
    private String[] images;  // Change to array
    private String position;
    private String width;
    private String height;
    private String uri;

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

    @Override
    public String toString() {
        return "AdsModelRequest{" +
                "position='" + position + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
