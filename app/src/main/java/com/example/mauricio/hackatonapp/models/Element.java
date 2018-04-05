package com.example.mauricio.hackatonapp.models;

public class Element {

    private String title;
    private String subtitle;
    private String description;
    private int image;

    public Element(String title, String subtitle, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    public Element(String title, String subtitle, String description, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.image = image;
    }

    public Element(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

}
