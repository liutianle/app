package com.example.nene.movie20.data;

/**
 * Created by nene on 2018/4/16.
 */

public class Video {
    private String img;
    private String name;
    private String number;
    private int id;

    public Video(String img, String name, String number, int id) {
        this.img = img;
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImg() {
        return img;
    }

    public void setImg(){
        this.img = img;
    }

    public String getName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }

    public String getNumber(){
        return number;
    }
    public void setNumber(){
        this.number = number;
    }
}
