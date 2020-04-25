package com.example.covid_19;

public class model {
    private int image;
    private String title;
    private String desk;

    public model(int image, String title, String desk) {
        this.image = image;
        this.title = title;
        this.desk = desk;
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

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }
}
