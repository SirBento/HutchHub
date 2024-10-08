package com.example.hutchhub.Models;

public class PregRabbitDetails {
    String name,dob,crossDate,palpating,kindling, breadingBox;

    public PregRabbitDetails() {
    }

    public PregRabbitDetails(String name, String dob, String crossDate, String palpating, String kindling, String breadingBox) {
        this.name = name;
        this.dob = dob;
        this.crossDate = crossDate;
        this.palpating = palpating;
        this.kindling = kindling;
        this.breadingBox = breadingBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCrossDate() {
        return crossDate;
    }

    public void setCrossDate(String crossDate) {
        this.crossDate = crossDate;
    }

    public String getPalpating() {
        return palpating;
    }

    public void setPalpating(String palpating) {
        this.palpating = palpating;
    }

    public String getKindling() {
        return kindling;
    }

    public void setKindling(String kindling) {
        this.kindling = kindling;
    }

    public String getBreadingBox() {
        return breadingBox;
    }

    public void setBreadingBox(String breadingBox) {
        this.breadingBox = breadingBox;
    }
}
