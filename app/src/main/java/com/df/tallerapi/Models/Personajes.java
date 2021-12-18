package com.df.tallerapi.Models;

public class Personajes {

    private String id;
    private String name;
    private String number;
    private String status;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        String[] imagePartes = image.split("/");
        return (imagePartes[imagePartes.length - 1]);
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

