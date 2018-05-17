package com.example.nene.movie20.data;

public class Admin {
    private int logo_img;
    private String logo_text;

    public Admin(int logo_img, String logo_text){
        this.logo_img = logo_img;
        this.logo_text = logo_text;
    }

    public int getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(int logo_img) {
        this.logo_img = logo_img;
    }

    public String getLogo_text() {
        return logo_text;
    }

    public void setLogo_text(String logo_text) {
        this.logo_text = logo_text;
    }
}
