package com.example.hutchhub.Models;

public class RabbitRecord {
    String Name,Sex, DOB,
            Fname,Mname,Origin,
            Color,Wdate,Breed,
            Purpose,Notes,Image;

    public RabbitRecord() {
    }

    public RabbitRecord(String name, String sex, String DOB,
                        String fname, String mname, String origin,
                        String color, String wdate, String breed,
                        String purpose, String notes, String image) {
        this.Name = name;
        this.Sex = sex;
        this.DOB = DOB;
        this.Fname = fname;
        this.Mname = mname;
        this.Origin = origin;
        this.Color = color;
        this.Wdate = wdate;
        this.Breed = breed;
        this.Purpose = purpose;
        this.Notes = notes;
        this.Image = image;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getWdate() {
        return Wdate;
    }

    public void setWdate(String wdate) {
        Wdate = wdate;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
