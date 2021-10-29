package com.example.greentreeonline.Class;

public class SlidePhoto {

    private String linkanh;

    public SlidePhoto(String resourceId) {
        this.linkanh = resourceId;
    }

    public SlidePhoto() {

    }


    public String getResourceId() {
        return linkanh;
    }

    public void setResourceId(String resourceId) {
        this.linkanh = resourceId;
    }
}
