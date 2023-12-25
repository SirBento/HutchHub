package com.example.hutchhub.Models;

public class BreedingAndCare {
    String Id,Doe_Name, Doe_Age,Doe_Breed,
            Buck_Name,Buck_Age,Buck_Breed,
            Falls,Cross_Date,Reco_food,
            Quantity,Pal_Date, PregDue_Date;
    public BreedingAndCare() {}

    public BreedingAndCare(String id, String doe_Name, String doe_Age, String doe_Breed, String buck_Name, String buck_Age, String buck_Breed, String falls, String cross_Date, String reco_food, String quantity, String pal_Date, String pregDue_Date) {
        this.Id = id;
        this.Buck_Name = buck_Name;
        this.Buck_Age = buck_Age;
        this.Buck_Breed = buck_Breed;
        this.Doe_Name = doe_Name;
        this.Doe_Age = doe_Age;
        this.Doe_Breed = doe_Breed;
        this.Falls = falls;
        this.Cross_Date = cross_Date;
        this.Reco_food = reco_food;
        this.Quantity = quantity;
        this.Pal_Date = pal_Date;
        this.PregDue_Date = pregDue_Date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDoe_Name() {
        return Doe_Name;
    }

    public void setDoe_Name(String doe_Name) {
        Doe_Name = doe_Name;
    }

    public String getDoe_Age() {
        return Doe_Age;
    }

    public void setDoe_Age(String doe_Age) {
        Doe_Age = doe_Age;
    }

    public String getDoe_Breed() {
        return Doe_Breed;
    }

    public void setDoe_Breed(String doe_Breed) {
        Doe_Breed = doe_Breed;
    }

    public String getBuck_Name() {
        return Buck_Name;
    }

    public void setBuck_Name(String buck_Name) {
        Buck_Name = buck_Name;
    }

    public String getBuck_Age() {
        return Buck_Age;
    }

    public void setBuck_Age(String buck_Age) {
        Buck_Age = buck_Age;
    }

    public String getBuck_Breed() {
        return Buck_Breed;
    }

    public void setBuck_Breed(String buck_Breed) {
        Buck_Breed = buck_Breed;
    }

    public String getFalls() {
        return Falls;
    }

    public void setFalls(String falls) {
        Falls = falls;
    }

    public String getCross_Date() {
        return Cross_Date;
    }

    public void setCross_Date(String cross_Date) {
        Cross_Date = cross_Date;
    }

    public String getReco_food() {
        return Reco_food;
    }

    public void setReco_food(String reco_food) {
        Reco_food = reco_food;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPal_Date() {
        return Pal_Date;
    }

    public void setPal_Date(String pal_Date) {
        Pal_Date = pal_Date;
    }

    public String getPregDue_Date() {
        return PregDue_Date;
    }

    public void setPregDue_Date(String pregDue_Date) {
        PregDue_Date = pregDue_Date;
    }
}
